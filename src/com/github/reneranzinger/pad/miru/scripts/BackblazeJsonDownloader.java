package com.github.reneranzinger.pad.miru.scripts;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

import com.github.reneranzinger.pad.miru.util.Downloader;

public class BackblazeJsonDownloader
{
    public static final String BASE_URL = "https://f002.backblazeb2.com/file/miru-data/paddata/processed/";

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
        // boni
        try
        {
            t_downloader.downloadFile(BASE_URL + "na_bonuses.json",
                    a_downloadFolder + "na_bonuses.json");
        }
        catch (Exception e)
        {
            System.out.println("Failed to download Bonus file: " + e.getMessage());
            e.printStackTrace(System.out);
        }
        // cards
        try
        {
            t_downloader.downloadFile(BASE_URL + "na_cards.json",
                    a_downloadFolder + "na_cards.json");
        }
        catch (Exception e)
        {
            System.out.println("Failed to download card file: " + e.getMessage());
            e.printStackTrace(System.out);
        }
        // dungeons
        try
        {
            t_downloader.downloadFile(BASE_URL + "na_dungeons.json",
                    a_downloadFolder + "na_dungeons.json");
        }
        catch (Exception e)
        {
            System.out.println("Failed to download dungeon file: " + e.getMessage());
            e.printStackTrace(System.out);
        }
        // enemies
        try
        {
            t_downloader.downloadFile(BASE_URL + "na_enemies.json",
                    a_downloadFolder + "na_enemies.json");
        }
        catch (Exception e)
        {
            System.out.println("Failed to download enemy file: " + e.getMessage());
            e.printStackTrace(System.out);
        }
        // enemy skills
        try
        {
            t_downloader.downloadFile(BASE_URL + "na_enemy_skills.json",
                    a_downloadFolder + "na_enemy_skills.json");
        }
        catch (Exception e)
        {
            System.out.println("Failed to download enemy skill file: " + e.getMessage());
            e.printStackTrace(System.out);
        }
        // exchange
        try
        {
            t_downloader.downloadFile(BASE_URL + "na_exchange.json",
                    a_downloadFolder + "na_exchange.json");
        }
        catch (Exception e)
        {
            System.out.println("Failed to download exchange file: " + e.getMessage());
            e.printStackTrace(System.out);
        }
        // raw cards
        try
        {
            t_downloader.downloadFile(BASE_URL + "na_raw_cards.json",
                    a_downloadFolder + "na_raw_cards.json");
        }
        catch (Exception e)
        {
            System.out.println("Failed to download raw cards file: " + e.getMessage());
            e.printStackTrace(System.out);
        }
        // skills
        try
        {
            t_downloader.downloadFile(BASE_URL + "na_skills.json",
                    a_downloadFolder + "na_skills.json");
        }
        catch (Exception e)
        {
            System.out.println("Failed to download Bonus file: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

}
