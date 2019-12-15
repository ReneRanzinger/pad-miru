package com.github.reneranzinger.pad.miru;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;

import com.github.reneranzinger.pad.miru.scripts.BackblazeJsonDownloader;
import com.github.reneranzinger.pad.miru.util.Downloader;

public class MiruLastModified
{

    public static void main(String[] args)
            throws ClientProtocolException, IOException, URISyntaxException
    {
        Downloader t_downloader = new Downloader();
        Header[] t_headers = t_downloader.getLastModified(BackblazeJsonDownloader.DATABASE_URL);
        for (Header t_header : t_headers)
        {
            String t_timeStampString = t_header.getValue();
            // String t_timestamp = t_timeStampString.substring(0,
            // t_timeStampString.length() - 3);
            Long t_timeStamp = Long.parseLong(t_timeStampString);
            Date t_date = new Date(t_timeStamp);
            DateFormat t_dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm z");
            System.out.println(t_dateFormat.format(t_date));
        }
    }

}
// 2019-11-29 11:26 EST