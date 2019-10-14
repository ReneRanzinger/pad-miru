package com.github.reneranzinger.pad.miru.om;

import java.io.IOException;

public enum Attribute
{
    LIGHT("Light", "light"), DARK("Dark", "dark"), FIRE("Fire", "fire"), WATER("Water",
            "water"), WOOD("Wood", "wood");

    private String m_webname = null;

    private String m_internalName = null;

    private Attribute(String a_webname, String a_internalName)
    {
        this.m_webname = a_webname;
        this.m_internalName = a_internalName;
    }

    public String getWebName()
    {
        return this.m_webname;
    }

    public String getInternalName()
    {
        return this.m_internalName;
    }

    public static Attribute forWebName(String a_name) throws IOException
    {
        for (Attribute a : Attribute.values())
        {
            if (a_name.equals(a.m_webname))
            {
                return a;
            }
        }
        throw new IOException("Invalid value for web name.");
    }

    public static Attribute forInternalName(String a_name) throws IOException
    {
        for (Attribute a : Attribute.values())
        {
            if (a_name.equals(a.m_internalName))
            {
                return a;
            }
        }
        throw new IOException("Invalid value for anomer");
    }

}
