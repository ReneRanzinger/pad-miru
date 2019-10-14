package com.github.reneranzinger.pad.miru.om;

import java.io.IOException;

public enum ImageType
{
    AWOKEN("awoken"), SUPER_AWOKEN("super awoken"), MONSTER_SMALL("monster small"), MONSTER_BIG(
            "monster big");

    private String m_type = null;

    private ImageType(String a_type)
    {
        this.m_type = a_type;
    }

    public String getType()
    {
        return this.m_type;
    }

    public static ImageType forType(String a_type) throws IOException
    {
        for (ImageType a : ImageType.values())
        {
            if (a_type.equals(a.m_type))
            {
                return a;
            }
        }
        throw new IOException("Invalid value for type.");
    }

}
