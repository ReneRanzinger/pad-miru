package com.github.reneranzinger.pad.miru.om;

import java.util.ArrayList;
import java.util.List;

public class Floor
{
    private String m_name = null;
    private List<Integer> m_drops = new ArrayList<>();
    private Integer m_number = null;
    private Integer m_stamina = null;
    private Integer m_waves = null;

    public String getName()
    {
        return this.m_name;
    }

    public void setName(String a_name)
    {
        this.m_name = a_name;
    }

    public List<Integer> getDrops()
    {
        return this.m_drops;
    }

    public void setDrops(List<Integer> a_drops)
    {
        this.m_drops = a_drops;
    }

    public Integer getNumber()
    {
        return this.m_number;
    }

    public void setNumber(Integer a_number)
    {
        this.m_number = a_number;
    }

    public Integer getStamina()
    {
        return this.m_stamina;
    }

    public void setStamina(Integer a_stamina)
    {
        this.m_stamina = a_stamina;
    }

    public Integer getWaves()
    {
        return this.m_waves;
    }

    public void setWaves(Integer a_waves)
    {
        this.m_waves = a_waves;
    }
}
