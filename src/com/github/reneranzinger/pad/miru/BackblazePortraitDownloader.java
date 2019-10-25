package com.github.reneranzinger.pad.miru;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.github.reneranzinger.pad.miru.persist.DBInterface;
import com.github.reneranzinger.pad.miru.util.Downloader;

public class BackblazePortraitDownloader
{
    public static final String BASE_URL = "https://f002.backblazeb2.com/file/miru-data/padimages/na/portrait/";

    public static void main(String[] args) throws ClientProtocolException, IOException,
            URISyntaxException, ClassNotFoundException, SQLException
    {
        String t_databaseFile = "./database/pad.2019-10-23.sqlite3";
        // target location the files will be downloaded too
        String t_downloadFolder = "./portrait/";
        // check if the folder exists, otherwise create it
        File t_folder = new File(t_downloadFolder);
        if (!t_folder.exists())
        {
            t_folder.mkdirs();
        }
        Downloader t_downloader = new Downloader();
        DBInterface t_db = new DBInterface(t_databaseFile);
        List<Integer> t_listCard = t_db.getCardIds();
        for (Integer t_id : t_listCard)
        {
            String t_fileName = t_downloadFolder + t_id.toString() + ".png";
            File t_fileImage = new File(t_fileName);
            if (!t_fileImage.exists())
            {
                try
                {
                    t_downloader.downloadFile(BASE_URL + t_id.toString() + ".png", t_fileName);
                }
                catch (Exception e)
                {
                    System.out.println(
                            "Error downloading " + t_id.toString() + ": " + e.getMessage());
                }
            }
        }
    }

}
