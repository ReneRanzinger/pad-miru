package com.github.reneranzinger.pad.miru.om;

import java.util.Date;

public class Bonus
{
    private Integer m_bonusTypeId = null;
    private Date m_startTime = null;
    private Date m_endTime = null;
    private String m_group = null;
    private Boolean m_starter = true;
    private String m_name = null;
    private String m_value = null;
    private String m_message = null;
    private Integer m_eggMachineId = null;
    private Integer m_dungeonId = null;
    private Integer m_floorNumber = null;

    public Integer getBonusTypeId()
    {
        return this.m_bonusTypeId;
    }

    public void setBonusTypeId(Integer a_bonusTypeId)
    {
        this.m_bonusTypeId = a_bonusTypeId;
    }

    public Date getStartTime()
    {
        return this.m_startTime;
    }

    public void setStartTime(Date a_startTime)
    {
        this.m_startTime = a_startTime;
    }

    public Date getEndTime()
    {
        return this.m_endTime;
    }

    public void setEndTime(Date a_endTime)
    {
        this.m_endTime = a_endTime;
    }

    public String getGroup()
    {
        return this.m_group;
    }

    public void setGroup(String a_group)
    {
        this.m_group = a_group;
    }

    public Boolean getStarter()
    {
        return this.m_starter;
    }

    public void setStarter(Boolean a_starter)
    {
        this.m_starter = a_starter;
    }

    public String getName()
    {
        return this.m_name;
    }

    public void setName(String a_name)
    {
        this.m_name = a_name;
    }

    public String getValue()
    {
        return this.m_value;
    }

    public void setValue(String a_value)
    {
        this.m_value = a_value;
    }

    public String getMessage()
    {
        return this.m_message;
    }

    public void setMessage(String a_message)
    {
        this.m_message = a_message;
    }

    public Integer getEggMachineId()
    {
        return this.m_eggMachineId;
    }

    public void setEggMachineId(Integer a_eggMachineId)
    {
        this.m_eggMachineId = a_eggMachineId;
    }

    public Integer getDungeonId()
    {
        return this.m_dungeonId;
    }

    public void setDungeonId(Integer a_dungeonId)
    {
        this.m_dungeonId = a_dungeonId;
    }

    public Integer getFloorNumber()
    {
        return m_floorNumber;
    }

    public void setFloorNumber(Integer a_floorNumber)
    {
        this.m_floorNumber = a_floorNumber;
    }

}
