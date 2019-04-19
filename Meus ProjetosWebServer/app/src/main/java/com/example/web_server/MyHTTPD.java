package com.example.web_server;
import fi.iki.elonen.NanoHTTPD;
import java.io.IOException;

public class MyHTTPD extends NanoHTTPD {
    public static final int PORT = 8765;

    public MyHTTPD() throws IOException {
        super(PORT);
    }

    @Override
    public Response serve(IHTTPSession session) {                       //cria uma "pagina" com JSON

        Testing test = new Testing();                                   //valores recebidos da main e que serão parceados para JSON

        String msg2 ="{" +
                "\"username\":" + "\""+ test.getUsername() +"\"," +
                "\"password\":" + "\""+ test.getPassword() +"\"" +
                "}";
        return newFixedLengthResponse(msg2);
    }
}


