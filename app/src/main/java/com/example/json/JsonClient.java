package com.example.json;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// to connect the server and download the whole data
public class JsonClient {

    private static String dataURL = "https://itunes.apple.com/search?term=adele";

    //to download String Data
    public String getJsonData(){

        //to connect to web service .. init the values
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            URL url = new URL(dataURL);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            //for download
            httpURLConnection.setDoInput(true);
            //for upload
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            //the reading part
            StringBuffer stringBuffer = new StringBuffer();
            inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            //to read the data line by line ..
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line + "\n");
            }

            inputStream.close();
            httpURLConnection.disconnect();

            return stringBuffer.toString();
        }
        catch (Throwable e) {
            e.printStackTrace();
        }

        // to make sure that the below code gonna execute if any error happened
        finally {
            try {
                inputStream.close();
                httpURLConnection.disconnect();
            }catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //to download the images
    public Bitmap downloadBitmap(String imageURL){

        Bitmap bitmap = null;

        try {
            URL url = new URL(imageURL);
            InputStream inputStream = url.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return bitmap;
    }
}
