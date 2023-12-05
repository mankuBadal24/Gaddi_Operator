package com.example.gadiremote;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

public class test extends Thread {
    public  String myurl;
    @Override
    public void run() {
        super.run();

        try{
            String urls=myurl;
            URL url=new URL(urls);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responsecode=connection.getResponseCode();
            if(responsecode== HttpsURLConnection.HTTP_OK){
               // return "URL sent succesfully";
                Log.d(TAG, "run:success fully sent ................................................................................... ");

            }
            else{
                // return "Failed to connect to node mcu Response code: "+responsecode;
                Log.d(TAG, "run:failed ................................................................................... ");

            }
        }


        catch(IOException e){
            e.printStackTrace();
            // return "Error:" +e.getMessage();
            Log.d(TAG, "exception .."+e);

        }




    }

    public void geturl(String url){
        myurl=url;
        //return url;
    }
}

