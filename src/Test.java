import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map.Entry;

import com.forgeessentials.remote.client.RemoteClient;
import com.forgeessentials.remote.client.RemoteRequest;
import com.forgeessentials.remote.client.RemoteResponse;
import com.forgeessentials.remote.client.RequestAuth;
import com.forgeessentials.remote.client.data.GetPlayerHandler;
import com.google.gson.JsonElement;

public class Test implements Runnable {

    private RemoteClient client;

    private RequestAuth auth;

    public Test() throws UnknownHostException, IOException
    {
        client = new RemoteClient("localhost", 27020);
        new Thread(this).start();
        auth = new RequestAuth("ForgeDevName", "password");
    }

    public void test()
    {
        try
        {
            RemoteResponse<GetPlayerHandler.Response> response = client.sendRequestAndWait(new RemoteRequest<>("get_player", auth,
                    new GetPlayerHandler.Request("ForgeDevName", "location", "detail")), GetPlayerHandler.Response.class, 60 * 1000);
            if (response == null || !response.success)
            {
                System.err.println("Error: " + (response == null ? "no response" : response.error));
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

    @Override
    public void run()
    {
        while (!client.isClosed())
        {
            RemoteResponse<JsonElement> response = client.getNextResponse(0);
            if (response != null)
            {
                System.out.println(String.format("Response (no-wait) #%d: %s", response.rid, response.data.toString()));
            }
        }
    }

    public void close()
    {
        client.close();
    }

    public static void main(String[] args) throws UnknownHostException, IOException
    {
        Test main = new Test();
        main.test();
        main.close();
    }
}
