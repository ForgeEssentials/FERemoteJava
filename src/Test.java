import java.io.IOException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.Map.Entry;

import com.forgeessentials.remote.client.RemoteClient;
import com.forgeessentials.remote.client.RemoteRequest;
import com.forgeessentials.remote.client.RemoteResponse;
import com.forgeessentials.remote.client.RequestAuth;
import com.forgeessentials.remote.client.data.PushChatHandler;
import com.forgeessentials.remote.client.data.QueryPlayerHandler;
import com.google.gson.JsonElement;

public class Test implements Runnable {

    private RemoteClient client;

    private RequestAuth auth;

    public Test() throws UnknownHostException, IOException, NoSuchAlgorithmException
    {
        // client = RemoteClient.createSslClient("localhost", 27020);
        client = new RemoteClient("localhost", 27020);
        new Thread(this).start();
        auth = new RequestAuth("ForgeDevName", "password");
    }

    @Override
    public void run()
    {
        while (!client.isClosed())
        {
            RemoteResponse<JsonElement> response = client.getNextResponse(0);
            if (response != null)
            {
                if (response.id == null)
                    handleUnknownMessage(response);
                else
                {
                    switch (response.id)
                    {
                    case PushChatHandler.ID:
                    {
                        RemoteResponse<PushChatHandler.Response> r = client.transformResponse(response, PushChatHandler.Response.class);
                        System.out.println(String.format("Chat (%s): %s", r.data.username, r.data.message));
                        break;
                    }
                    default:
                        handleUnknownMessage(response);
                        break;
                    }
                }
            }
        }
    }

    public void handleUnknownMessage(RemoteResponse<JsonElement> response)
    {
        if (response.id == null)
            response.id = "";
        if (response.success)
        {
            if (response.message == null)
                response.message = "success";
            if (response.data == null)
                System.out.println(String.format("EAT Response %s:#%d (%s)", response.id, response.rid, response.message));
            else
                System.out.println(String.format("EAT Response %s:#%d (%s): %s", response.id, response.rid, response.message, response.data.toString()));
        }
        else
        {
            if (response.message == null)
                response.message = "failure";
            if (response.data == null)
                System.out.println(String.format("EAT Response %s:#%d (%s)", response.id, response.rid, response.message));
            else
                System.out.println(String.format("EAT Response %s:#%d (%s): %s", response.id, response.rid, response.message, response.data.toString()));
        }
    }

    public void queryPlayer()
    {
        try
        {
            RemoteResponse<QueryPlayerHandler.Response> response = client.sendRequestAndWait(new RemoteRequest<>(QueryPlayerHandler.ID, auth,
                    new QueryPlayerHandler.Request("ForgeDevName", "location", "detail")), QueryPlayerHandler.Response.class, 60 * 1000);
            if (response == null || !response.success)
            {
                System.err.println("Error: " + (response == null ? "no response" : response.message));
            }
            else
            {
                System.out.println("Response:");
                System.out.println("Username = " + response.data.username);
                System.out.println("UUID     = " + response.data.uuid);
                for (Entry<String, Object> data : response.data.data.entrySet())
                {
                    System.out.println("> " + data.getKey() + ": " + data.getValue().toString());
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void pushChat(boolean enable)
    {
        try
        {
            RemoteResponse<?> response = client.sendRequestAndWait(new RemoteRequest<>(PushChatHandler.ID, auth, new PushChatHandler.Request(enable)),
                    PushChatHandler.Response.class, 60 * 1000);
            if (response.success)
                System.out.println(enable ? "Enabled chat monitoring" : "Disabled chat monitoring");
            else
                System.err.println(response.message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void close()
    {
        client.close();
    }

    public static void main(String[] args) throws UnknownHostException, IOException, NoSuchAlgorithmException
    {
        Test main = new Test();
        main.queryPlayer();
        main.pushChat(true);
        // main.close();
    }

}
