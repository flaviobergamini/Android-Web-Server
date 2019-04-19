package com.example.web_server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    private static MyHTTPD server;
    @BindView(R.id.txtIpAddress) TextView txtIpAddress;
    @BindView(R.id.btnStart) Button btnStart;
    @BindView(R.id.btnStop) Button btnStop;                             //instancia os objetos do xml para o java

    private EditText editText;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        image = (ImageView) findViewById(R.id.logo);                    //instanciando imagem no app
        image.setImageResource(R.drawable.logo_iot);                    //desenhando imagem na tela
        try {                                                           //verificando se o servidor funciona
            server = new MyHTTPD();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {                         //botão para iniciar o servidor
                try {                                                  //verificando se o servidor funciona
                    server.start();                                    //inicia o servidor
                    initIPAddress();                                   //gera o IP
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {                          //botão para finalixzar servidor
                server.stop();                                         //Finaliza servidor
                txtIpAddress.setText("");                              //limpa textview do IP
            }
        });
    }

    public void onClickBtnEnviar(View view){                           //envia os dados dos editText
        Testing testing = new Testing();                               //instancia para os dados dos editText migrarem pars activity

        EditText mPassword;                                            //declarando variaveis
        EditText mUsername;

        mPassword   = (EditText)findViewById(R.id.edtPassword);        //recebendo dados do editText
        mUsername   = (EditText)findViewById(R.id.edtUsername);

        testing.setPassword(mPassword.getText().toString());           //enviando os dados para a classe de transição entre as activity
        testing.setUsername(mUsername.getText().toString());
        mUsername.getText().clear();                                   //limpando editText
        mPassword.getText().clear();
        Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_SHORT).show();  //exibe mensagem para orientar o usuario

    }


    private void initIPAddress() {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        txtIpAddress.setText("Server running at: " + ip + ":" + MyHTTPD.PORT);
        Log.i("TAG", "onCreate: " + ip);
    }
}


