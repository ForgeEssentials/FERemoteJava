package com.forgeessentials.remote.client.network.permission;

import java.util.ArrayList;

import com.forgeessentials.remote.client.RemoteMessageID;

public final class QueryRegisteredPermissionsHandler
{

    public static final String ID = RemoteMessageID.QUERY_PERMISSION_REGISTERED;

    public static class Response extends ArrayList<String>
    {
    }

}