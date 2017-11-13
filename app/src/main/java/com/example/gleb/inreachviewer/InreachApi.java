package com.example.gleb.inreachviewer;


import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Gleb on 18.10.2017.
 */

public class InreachApi {
    public static final String INREACH_API_URL = "https://inreach.garmin.com/feed/Share/";
    public static final String MAPSHARE_NAME = "PeterBaur";
    private static final String TAG = InreachApi.class.getName();




    public byte[] getUrlBytes(String urlSpec) throws IOException {
        final String basicAuth = "Basic " + Base64.encodeToString("PeterBaur:ProPan".getBytes(), Base64.NO_WRAP);
        URL url = new URL(urlSpec);
        HttpsURLConnection connection = (HttpsURLConnection) url
                .openConnection();
//        connection.setRequestProperty("Authorization", basicAuth);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpsURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with" + urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();

        } finally {
            connection.disconnect();
        }
    }


    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }


    public String fetchPoints(Date startDate, Date endDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.US);
        Uri.Builder uriBuilder = Uri.parse(INREACH_API_URL)
                .buildUpon()
                .appendPath(MAPSHARE_NAME);
        if (startDate != null) {
            uriBuilder.appendQueryParameter("d1", dateFormat.format(startDate));
            if (endDate != null) {
                uriBuilder.appendQueryParameter("d2", dateFormat.format(endDate));
            }
        }
        String url = uriBuilder.build().toString();
        Log.i(TAG, "url=" + url);
        String xmlString = "";
        try {
            xmlString = getUrlString(url);

        } catch (IOException e) {
            Log.e(TAG, "failed ", e);
        }
        return xmlString;
    }


}
