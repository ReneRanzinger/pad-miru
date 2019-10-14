package com.github.reneranzinger.pad.miru.om;

public class LeaderSkill
{
    private Integer m_id = null;
    private String m_name = null;
    private String m_description = null;

    public String getName()
    {
        return this.m_name;
    }

    public void setName(String a_name)
    {
        this.m_name = a_name;
    }

    public String getDescription()
    {
        return this.m_description;
    }

    public void setDescription(String a_description)
    {
        this.m_description = a_description;
    }

    public Integer getId()
    {
        return m_id;
    }

    public void setId(Integer a_id)
    {
        this.m_id = a_id;
    }
}
