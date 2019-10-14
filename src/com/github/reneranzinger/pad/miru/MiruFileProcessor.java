package com.github.reneranzinger.pad.miru;

import java.io.IOException;
import java.sql.SQLException;

import com.github.reneranzinger.pad.miru.persist.DBInterface;

public class MiruFileProcessor
{

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException
    {
        String t_targetFolder = "./database/";
        // create a new empty database from the template
        String t_fileName = DBInterface.copyDatabase("./database/puzzledragon-template.sqlite3",
                t_targetFolder);
        // open new database
        DBInterface t_db = new DBInterface(t_targetFolder + t_fileName);
        // fill database

        // get images

    }

}
