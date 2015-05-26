package com.forgeessentials.remote.client.data;

public class AreaZone extends Zone
{

    public String name;

    public AreaBase area;

    public AreaShape shape = AreaShape.BOX;

    public int priority;

    @Override
    public String toString()
    {
        return name;
    }

}