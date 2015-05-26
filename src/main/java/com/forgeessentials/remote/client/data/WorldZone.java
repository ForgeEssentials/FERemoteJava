package com.forgeessentials.remote.client.data;

import java.util.ArrayList;
import java.util.List;

public class WorldZone extends Zone
{

    public int dimensionID;

    public List<AreaZone> areaZones = new ArrayList<AreaZone>();

    @Override
    public String toString()
    {
        return "Dimension " + dimensionID;
    }

}
