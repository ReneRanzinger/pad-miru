package com.github.reneranzinger.pad.miru.scripts;

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
        String t_jsonFolder = "D:\\Java\\MiruFiles\\2019-10-26\\json/";
        String t_databaseTemplateFile = "./database/puzzledragon-template.sqlite3";
        MiruFileProcessor.process(t_targetFolder, t_jsonFolder, t_databaseTemplateFile, true);
    }

    public static void process(String a_databaseTargetFolder, String a_folderJson,
            String a_databaseTemplate, Boolean a_timeStampDatabase) throws IOException,
            ClassNotFoundException, SQLException, ParseException, MiruFileException
    {
        // create a new empty database from the template
        String t_fileName = DBInterface.copyDatabase(a_databaseTemplate, a_databaseTargetFolder,
                a_timeStampDatabase);
        // open new database
        DBInterface t_db = new DBInterface(a_databaseTargetFolder + t_fileName);
        // fill database
        MiruProcessor t_processor = new MiruProcessor();
        t_processor.process(a_folderJson, t_db);
    }

}
