package com.forgeessentials.remote.client.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ServerZone extends Zone
{

    public Map<Integer, WorldZone> worldZones = new HashMap<Integer, WorldZone>();

    public Map<UserIdent, Set<String>> playerGroups = new HashMap<UserIdent, Set<String>>();

    @Override
    public String toString()
    {
        return "Server zone";
    }

}
