package com.example.gadiremote;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    Button btnforward, btnbackward, btnrightturn, btnleftturn, btnbreaks;
    String txtipaddress, forward_btn_url = "/move_forward", backward_btn_url = "/move_backward", right_btn_url = "/turn_right", left_btn_url = "/turn_left";
    String break_btn_url = "/brakes";
    TextView goneurl;
    EditText ipadr, txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnforward = findViewById(R.id.btnforward);
        btnbackward = findViewById(R.id.btnbackward);
        btnleftturn = findViewById(R.id.btnleftturn);
        btnrightturn = findViewById(R.id.btnrightturn);
        btnbreaks = findViewById(R.id.btnbreaks);
        ipadr = findViewById(R.id.ipaddr);
        goneurl = findViewById(R.id.goneurl);






        btnforward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               implement_url(forward_btn_url);

            }
        });


        btnbackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                implement_url(backward_btn_url);
            }
        });


        btnrightturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                implement_url(right_btn_url);
            }
        });


        btnleftturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                implement_url(left_btn_url);
            }
        });
        btnbreaks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                implement_url(break_btn_url);

            }
        });



    }


   public void implement_url(String btn_txt) {
        txtipaddress = ipadr.getText().toString();
        String url = "http://" + txtipaddress + btn_txt;
        test t = new test();
        t.geturl(url);
        t.start();
//       sendurl p=new sendurl();
//       p.doInBackground(url);
      Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        goneurl.setText(url);
    }



}