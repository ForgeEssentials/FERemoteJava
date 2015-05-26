package com.forgeessentials.remote.client.network.chat;

import java.util.Date;

import com.forgeessentials.remote.client.RemoteMessageID;

public final class PushChatHandler {
    
    public static final String ID = RemoteMessageID.PUSH_CHAT;

    public static class Request {

        public boolean enable;

        public Request(boolean enable)
        {
            this.enable = enable;
        }
    }

    public static class Response {

        public String username;

        public String message;

        public Date timestamp;

        public Response(String username, String message)
        {
            this.username = username;
            this.message = message;
            this.timestamp = new Date();
        }

    }

}