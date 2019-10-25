package com.github.reneranzinger.pad.miru.om;

import java.util.ArrayList;
import java.util.List;

public class MonsterEntry
{
    private Integer m_id = null;
    private String m_name = null;
    private Integer m_rarity = null;
    private Integer m_mPoints = null;
    private Integer m_limitBreaks = null;
    private Integer m_hp = null;
    private Integer m_atk = null;
    private Integer m_rcv = null;
    private Integer m_skill = null;
    private Integer m_ancestor = null;
    private Integer m_leaderSkill = null;
    private List<Integer> m_awokenSkills = new ArrayList<>();
    private List<Integer> m_superAwokenSkill = new ArrayList<>();
    private Integer m_primaryAttribute = null;
    private Integer m_secondaryAttribute = null;
    private List<Integer> m_type = new ArrayList<>();
    private Integer m_evoMaterial1 = null;
    private Integer m_evoMaterial2 = null;
    private Integer m_evoMaterial3 = null;
    private Integer m_evoMaterial4 = null;
    private Integer m_evoMaterial5 = null;
    private Integer m_maxLevel = null;

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

    public Integer getRarity()
    {
        return this.m_rarity;
    }

    public void setRarity(Integer a_rarity)
    {
        this.m_rarity = a_rarity;
    }

    public Integer getmPoints()
    {
        return this.m_mPoints;
    }

    public void setmPoints(Integer a_mPoints)
    {
        this.m_mPoints = a_mPoints;
    }

    public Integer getLimitBreaks()
    {
        return this.m_limitBreaks;
    }

    public void setLimitBreaks(Integer a_limitBreaks)
    {
        this.m_limitBreaks = a_limitBreaks;
    }

    public Integer getHp()
    {
        return this.m_hp;
    }

    public void setHp(Integer a_hp)
    {
        this.m_hp = a_hp;
    }

    public Integer getAtk()
    {
        return this.m_atk;
    }

    public void setAtk(Integer a_atk)
    {
        this.m_atk = a_atk;
    }

    public Integer getRcv()
    {
        return this.m_rcv;
    }

    public void setRcv(Integer a_rcv)
    {
        this.m_rcv = a_rcv;
    }

    public Integer getSkill()
    {
        return this.m_skill;
    }

    public void setSkill(Integer a_skill)
    {
        this.m_skill = a_skill;
    }

    public Integer getAncestor()
    {
        return this.m_ancestor;
    }

    public void setAncestor(Integer a_ancestor)
    {
        this.m_ancestor = a_ancestor;
    }

    public Integer getLeaderSkill()
    {
        return this.m_leaderSkill;
    }

    public void setLeaderSkill(Integer a_leaderSkill)
    {
        this.m_leaderSkill = a_leaderSkill;
    }

    public List<Integer> getAwokenSkills()
    {
        return this.m_awokenSkills;
    }

    public void setAwokenSkills(List<Integer> a_awokenSkills)
    {
        this.m_awokenSkills = a_awokenSkills;
    }

    public List<Integer> getSuperAwokenSkill()
    {
        return this.m_superAwokenSkill;
    }

    public void setSuperAwokenSkill(List<Integer> a_superAwokenSkill)
    {
        this.m_superAwokenSkill = a_superAwokenSkill;
    }

    public Integer getPrimaryAttribute()
    {
        return this.m_primaryAttribute;
    }

    public void setPrimaryAttribute(Integer a_primaryAttribute)
    {
        this.m_primaryAttribute = a_primaryAttribute;
    }

    public Integer getSecondaryAttribute()
    {
        return this.m_secondaryAttribute;
    }

    public void setSecondaryAttribute(Integer a_secondaryAttribute)
    {
        this.m_secondaryAttribute = a_secondaryAttribute;
    }

    public List<Integer> getType()
    {
        return this.m_type;
    }

    public void setType(List<Integer> a_type)
    {
        this.m_type = a_type;
    }

    public Integer getEvoMaterial1()
    {
        return this.m_evoMaterial1;
    }

    public void setEvoMaterial1(Integer a_evoMaterial1)
    {
        this.m_evoMaterial1 = a_evoMaterial1;
    }

    public Integer getEvoMaterial2()
    {
        return this.m_evoMaterial2;
    }

    public void setEvoMaterial2(Integer a_evoMaterial2)
    {
        this.m_evoMaterial2 = a_evoMaterial2;
    }

    public Integer getEvoMaterial3()
    {
        return this.m_evoMaterial3;
    }

    public void setEvoMaterial3(Integer a_evoMaterial3)
    {
        this.m_evoMaterial3 = a_evoMaterial3;
    }

    public Integer getEvoMaterial4()
    {
        return this.m_evoMaterial4;
    }

    public void setEvoMaterial4(Integer a_evoMaterial4)
    {
        this.m_evoMaterial4 = a_evoMaterial4;
    }

    public Integer getEvoMaterial5()
    {
        return this.m_evoMaterial5;
    }

    public void setEvoMaterial5(Integer a_evoMaterial5)
    {
        this.m_evoMaterial5 = a_evoMaterial5;
    }

    public Integer getMaxLevel()
    {
        return this.m_maxLevel;
    }

    public void setMaxLevel(Integer a_maxLevel)
    {
        this.m_maxLevel = a_maxLevel;
    }

}
