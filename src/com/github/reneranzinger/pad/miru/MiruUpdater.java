package com.github.reneranzinger.pad.miru;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.parser.ParseException;

import com.github.reneranzinger.pad.miru.persist.DBInterface;
import com.github.reneranzinger.pad.miru.scripts.BackblazeJsonDownloader;
import com.github.reneranzinger.pad.miru.scripts.BackblazePortraitDownloader;
import com.github.reneranzinger.pad.miru.scripts.PortraitArchiver;

public class MiruUpdater
{
    public static final String BASE_FOLDER = "D:/Java/MiruFiles/";

    public static void main(String[] args) throws ClientProtocolException, IOException,
            URISyntaxException, ClassNotFoundException, SQLException, ParseException
    {
        String t_baseFolder = MiruUpdater.BASE_FOLDER;
        System.out.println("Download SQLite database ...");
        // create the folder with a timestamp
        Date t_date = Calendar.getInstance().getTime();
        DateFormat t_dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String t_folderName = t_dateFormat.format(t_date);
        String t_folderSqlite = t_baseFolder + t_folderName + "/";
        File t_folder = new File(t_folderSqlite);
        t_folder.mkdirs();
        // download json files into the folder
        BackblazeJsonDownloader.process(t_folderSqlite);
        System.out.println("Download SQLite database finished");

        System.out.println("Create portrait archive ...");
        // update image folder
        BackblazePortraitDownloader.process(
                t_baseFolder + t_folderName + "/" + DBInterface.DEFAULT_DATABASE,
                t_baseFolder + "portrait/");
        // create archive
        PortraitArchiver.process(t_baseFolder + "portrait/",
                t_baseFolder + t_folderName + "/portrait.zip");
        System.out.println("Create portrait archive finished");
    }

}
