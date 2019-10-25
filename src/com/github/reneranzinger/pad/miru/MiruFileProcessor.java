package com.github.reneranzinger.pad.miru;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import org.json.simple.parser.ParseException;

import com.github.reneranzinger.pad.miru.persist.DBInterface;
import com.github.reneranzinger.pad.miru.util.MiruFileException;
import com.github.reneranzinger.pad.miru.util.MiruProcessor;

public class MiruFileProcessor
{

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException,
            ParseException, MiruFileException, URISyntaxException
    {
        String t_targetFolder = "./database/";
        String t_jsonFolder = "./json/";
        // create a new empty database from the template
        String t_fileName = DBInterface.copyDatabase("./database/puzzledragon-template.sqlite3",
                t_targetFolder);
        // open new database
        DBInterface t_db = new DBInterface(t_targetFolder + t_fileName);
        // fill database
        MiruProcessor t_processor = new MiruProcessor();
        t_processor.process(t_jsonFolder, t_db);
    }

}
