package com.github.reneranzinger.pad.miru.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.StandardCopyOption;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Downloader
{
    private BasicCookieStore m_cookieStore = null;
    private CloseableHttpClient m_httpclient = null;

    public Downloader() throws ClientProtocolException, IOException, URISyntaxException
    {
        this.connect();
    }

    private void connect() throws ClientProtocolException, IOException, URISyntaxException
    {
        // configure timeouts
        int timeout = 5;
        RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000).setSocketTimeout(timeout * 1000)
                .build();
        // create cookie store and HTTP client
        this.m_cookieStore = new BasicCookieStore();
        this.m_httpclient = HttpClients.custom().setDefaultCookieStore(this.m_cookieStore)
                .setDefaultRequestConfig(config).build();
    }

    public void close() throws IOException
    {
        this.m_httpclient.close();
    }

    public boolean downloadFile(String a_url, String a_filename)
            throws UnsupportedOperationException, IOException, URISyntaxException,
            InterruptedException
    {
        try
        {
            HttpGet t_httpGet = new HttpGet(a_url);
            CloseableHttpResponse t_response = this.m_httpclient.execute(t_httpGet);
            HttpEntity entity = t_response.getEntity();
            InputStream t_stream = entity.getContent();
            File targetFile = new File(a_filename);
            java.nio.file.Files.copy(t_stream, targetFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            EntityUtils.consume(entity);
            t_response.close();
            return true;
        }
        catch (Exception e)
        {
            this.log("Error:Unable to download file: " + a_url + " (" + e.getMessage() + ")");
            this.reconnect();
            return false;
        }
    }

    private void log(String a_message)
    {
        System.out.println(a_message);
    }

    private void reconnect() throws IOException, URISyntaxException
    {
        this.close();
        this.connect();
    }
}
