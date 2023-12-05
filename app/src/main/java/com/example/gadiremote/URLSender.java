package com.example.gadiremote;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.IOException;

public class URLSender extends Thread {
public String url_txt;
    public  void sendURL(String urlString) {
        url_txt = urlString;
    }

        @Override
   public void run() {
            try {
                URL url = new URL(url_txt);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Set up the connection properties
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                // If you have parameters to send along with the URL, set them here
                String urlParameters = "param1=value1&param2=value2"; // Replace with your parameters

                // Send data
                try (OutputStream outputStream = connection.getOutputStream()) {
                    outputStream.write(urlParameters.getBytes());
                    outputStream.flush();
                }

                // Get the response from the server
                int responseCode = connection.getResponseCode();

                BufferedReader reader;
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                } else {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                }

                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();

                // Display the response from the server
                //System.out.println("Response from server: " + response.toString());
                Log.d(TAG, "Response from server: \" + response.toString() ");

                connection.disconnect(); // Close the connection

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//    public static void main(String[] args) {
//        String targetURL = "https://your-server-endpoint-url.com"; // Replace with your server URL
//        sendURL(targetURL);
//    }
}







