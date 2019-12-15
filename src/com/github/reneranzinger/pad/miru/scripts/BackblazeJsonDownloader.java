package com.github.reneranzinger.pad.miru.scripts;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

import com.github.reneranzinger.pad.miru.persist.DBInterface;
import com.github.reneranzinger.pad.miru.util.Downloader;

public class BackblazeJsonDownloader
{
    public static final String DATABASE_URL = "https://f002.backblazeb2.com/file/dadguide-data/db/dadguide.sqlite";

    public static void main(String[] args)
            throws ClientProtocolException, IOException, URISyntaxException
    {
        // target location the files will be downloaded too
        String t_downloadFolder = "./json/";
        BackblazeJsonDownloader.process(t_downloadFolder);
    }

    public static void process(String a_downloadFolder)
            throws ClientProtocolException, IOException, URISyntaxException
    {
        // check if the folder exists, otherwise create it
        File t_folder = new File(a_downloadFolder);
        if (!t_folder.exists())
        {
            t_folder.mkdirs();
        }
        Downloader t_downloader = new Downloader();
        // database file
        try
        {
            t_downloader.downloadFile(DATABASE_URL,
                    a_downloadFolder + DBInterface.DEFAULT_DATABASE);
        }
        catch (Exception e)
        {
            System.out.println("Failed to download database file: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

}
