package com.github.reneranzinger.pad.miru.util;

public class MiruFileException extends Exception
{
    private static final long serialVersionUID = 1L;

    public MiruFileException(String a_message)
    {
        super(a_message);
    }

    public MiruFileException(String a_message, Exception a_exception)
    {
        super(a_message, a_exception);
    }
}
