package com.github.reneranzinger.pad.miru.om;

public class AwokenSkill
{
    private Integer m_id = null;
    private String m_name = null;
    private String m_description = null;
    private Image m_image = null;

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

    public Image getImage()
    {
        return m_image;
    }

    public void setImage(Image a_image)
    {
        this.m_image = a_image;
    }

}
