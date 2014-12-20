package com.forgeessentials.remote.client.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class GetPlayerHandler {

    public static class Request {

        public String username;

        public Set<String> flags = new HashSet<>();

        public Request(String username, String... flags)
        {
            this.username = username;
            for (int i = 0; i < flags.length; i++)
            {
                this.flags.add(flags[i]);
            }
        }

    }

    public static class Response {

        public String uuid;

        public String username;

        public Map<String, Object> data = new HashMap<>();

        public Response(String uuid, String username)
        {
            this.uuid = uuid;
            this.username = username;
        }

    }

}
