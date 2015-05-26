package com.forgeessentials.remote.client.data;

import java.util.HashMap;
import java.util.Map;

public class Zone
{

    public int id;

    public Map<UserIdent, PermissionList> playerPermissions = new HashMap<UserIdent, PermissionList>();

    public Map<String, PermissionList> groupPermissions = new HashMap<String, PermissionList>();

}
