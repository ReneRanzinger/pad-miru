package com.github.reneranzinger.pad.miru.om;

public class Image
{
    private byte[] m_image = null;
    private String m_fileExtension = null;
    private Integer m_id = null;

    public Image()
    {
        super();
    }

    public byte[] getImage()
    {
        return this.m_image;
    }

    public void setImage(byte[] a_image)
    {
        this.m_image = a_image;
    }

    public Integer getId()
    {
        return this.m_id;
    }

    public void setId(Integer a_id)
    {
        this.m_id = a_id;
    }

    public String getFileExtension()
    {
        return m_fileExtension;
    }

    public void setFileExtension(String a_fileExtension)
    {
        this.m_fileExtension = a_fileExtension;
    }
}
