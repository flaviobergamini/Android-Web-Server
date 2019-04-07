package com.example.web_server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import fi.iki.elonen.NanoHTTPD;
import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static MyHTTPD server;
    @BindView(R.id.txtIpAddress) TextView txtIpAddress;
    @BindView(R.id.btnStart) Button btnStart;
    @BindView(R.id.btnStop) Button btnStop;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        try {
            server = new MyHTTPD();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                try {
                    server.start();
                    initIPAddress();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                server.stop();
                txtIpAddress.setText("");
            }
        });

        //EditText leitura;
        //leitura = (EditText)findViewById(R.id.enviar);
        //leitura.getText().
    }


    private void initIPAddress() {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        txtIpAddress.setText("Server running at: " + ip + ":" + MyHTTPD.PORT);
        Log.i("TAG", "onCreate: " + ip);
    }
}

