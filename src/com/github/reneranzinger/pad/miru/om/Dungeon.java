package com.github.reneranzinger.pad.miru.om;

import java.util.ArrayList;
import java.util.List;

public class Dungeon
{
    private String m_altType = null;
    private String m_name = null;
    private String m_comment = null;
    private Integer m_commentValue = null;
    private Integer m_id = null;
    private String m_type = null;
    private Boolean m_oneTime = true;
    private String m_repeatDay = null;
    private List<Floor> m_floors = new ArrayList<>();

    public String getAltType()
    {
        return this.m_altType;
    }

    public void setAltType(String a_altType)
    {
        this.m_altType = a_altType;
    }

    public String getName()
    {
        return this.m_name;
    }

    public void setName(String a_name)
    {
        this.m_name = a_name;
    }

    public String getComment()
    {
        return this.m_comment;
    }

    public void setComment(String a_comment)
    {
        this.m_comment = a_comment;
    }

    public Integer getCommentValue()
    {
        return this.m_commentValue;
    }

    public void setCommentValue(Integer a_commentValue)
    {
        this.m_commentValue = a_commentValue;
    }

    public Integer getId()
    {
        return this.m_id;
    }

    public void setId(Integer a_id)
    {
        this.m_id = a_id;
    }

    public String getType()
    {
        return this.m_type;
    }

    public void setType(String a_type)
    {
        this.m_type = a_type;
    }

    public Boolean getOneTime()
    {
        return this.m_oneTime;
    }

    public void setOneTime(Boolean a_oneTime)
    {
        this.m_oneTime = a_oneTime;
    }

    public String getRepeatDay()
    {
        return this.m_repeatDay;
    }

    public void setRepeatDay(String a_repeatDay)
    {
        this.m_repeatDay = a_repeatDay;
    }

    public List<Floor> getFloors()
    {
        return this.m_floors;
    }

    public void setFloors(List<Floor> a_floors)
    {
        this.m_floors = a_floors;
    }
}
