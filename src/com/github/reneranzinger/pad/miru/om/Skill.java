package com.github.reneranzinger.pad.miru.om;

public class Skill
{
    private Integer m_id = null;
    private String m_name = null;
    private String m_description = null;
    private Integer m_maxTurns = null;
    private Integer m_minTurns = null;
    private Integer m_maxLevel = null;
    private Integer m_part1 = null;
    private Integer m_part2 = null;
    private Integer m_part3 = null;
    private Integer m_type = null;

    public Integer getId()
    {
        return this.m_id;
    }

    public void setId(Integer a_id)
    {
        this.m_id = a_id;
    }

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

    public Integer getMaxTurns()
    {
        return this.m_maxTurns;
    }

    public void setMaxTurns(Integer a_maxTurns)
    {
        this.m_maxTurns = a_maxTurns;
    }

    public Integer getMinTurns()
    {
        return this.m_minTurns;
    }

    public void setMinTurns(Integer a_minTurns)
    {
        this.m_minTurns = a_minTurns;
    }

    public Integer getMaxLevel()
    {
        return m_maxLevel;
    }

    public void setMaxLevel(Integer a_maxLevel)
    {
        this.m_maxLevel = a_maxLevel;
    }

    public Integer getPart1()
    {
        return this.m_part1;
    }

    public void setPart1(Integer a_part1)
    {
        this.m_part1 = a_part1;
    }

    public Integer getPart2()
    {
        return this.m_part2;
    }

    public void setPart2(Integer a_part2)
    {
        this.m_part2 = a_part2;
    }

    public Integer getPart3()
    {
        return this.m_part3;
    }

    public void setPart3(Integer a_part3)
    {
        this.m_part3 = a_part3;
    }

    public Integer getType()
    {
        return this.m_type;
    }

    public void setType(Integer a_type)
    {
        this.m_type = a_type;
    }

}
