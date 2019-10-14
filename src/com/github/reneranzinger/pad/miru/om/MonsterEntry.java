package com.github.reneranzinger.pad.miru.om;

import java.util.ArrayList;
import java.util.List;

public class MonsterEntry
{
    private Integer m_id = null;
    private String m_name = null;
    private Integer m_rarity = null;
    private Integer m_mPoints = null;
    private boolean m_limitBreaks = false;
    private Integer m_hp = null;
    private Integer m_atk = null;
    private Integer m_rcv = null;
    private Skill m_skill = null;
    private LeaderSkill m_leaderSkill = null;
    private List<AwokenSkill> m_awokenSkills = new ArrayList<>();
    private List<AwokenSkill> m_superAwokenSkill = new ArrayList<>();
    private boolean m_assist = false;
    private Attribute m_primaryAttribute = null;
    private Attribute m_secondaryAttribute = null;
    private List<MonsterType> m_type = new ArrayList<>();
    private Image m_imageSmall = null;
    private Image m_imageLarge = null;

    public Integer getId()
    {
        return this.m_id;
    }

    public void setId(Integer a_id)
    {
        this.m_id = a_id;
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

    public boolean isLimitBreaks()
    {
        return this.m_limitBreaks;
    }

    public void setLimitBreaks(boolean a_limitBreaks)
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

    public Skill getSkill()
    {
        return this.m_skill;
    }

    public void setSkill(Skill a_skill)
    {
        this.m_skill = a_skill;
    }

    public LeaderSkill getLeaderSkill()
    {
        return this.m_leaderSkill;
    }

    public void setLeaderSkill(LeaderSkill a_leaderSkill)
    {
        this.m_leaderSkill = a_leaderSkill;
    }

    public List<AwokenSkill> getAwokenSkills()
    {
        return this.m_awokenSkills;
    }

    public void setAwokenSkills(List<AwokenSkill> a_awokenSkills)
    {
        this.m_awokenSkills = a_awokenSkills;
    }

    public List<AwokenSkill> getSuperAwokenSkill()
    {
        return this.m_superAwokenSkill;
    }

    public void setSuperAwokenSkill(List<AwokenSkill> a_superAwokenSkill)
    {
        this.m_superAwokenSkill = a_superAwokenSkill;
    }

    public boolean isAssist()
    {
        return this.m_assist;
    }

    public void setAssist(boolean a_assist)
    {
        this.m_assist = a_assist;
    }

    public String getName()
    {
        return m_name;
    }

    public void setName(String a_name)
    {
        this.m_name = a_name;
    }

    public Attribute getPrimaryAttribute()
    {
        return this.m_primaryAttribute;
    }

    public Attribute getSecondaryAttribute()
    {
        return this.m_secondaryAttribute;
    }

    public List<MonsterType> getType()
    {
        return this.m_type;
    }

    public Image getImageSmall()
    {
        return this.m_imageSmall;
    }

    public void setImageSmall(Image a_imageSmall)
    {
        this.m_imageSmall = a_imageSmall;
    }

    public Image getImageLarge()
    {
        return this.m_imageLarge;
    }

    public void setImageLarge(Image a_imageLarge)
    {
        this.m_imageLarge = a_imageLarge;
    }

    public void setPrimaryAttribute(Attribute a_primaryAttribute)
    {
        this.m_primaryAttribute = a_primaryAttribute;
    }

    public void setSecondaryAttribute(Attribute a_secondaryAttribute)
    {
        this.m_secondaryAttribute = a_secondaryAttribute;
    }

    public void setType(List<MonsterType> a_type)
    {
        this.m_type = a_type;
    }

}
