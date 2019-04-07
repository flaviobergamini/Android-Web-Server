package com.example.web_server;

import android.os.Environment;
import android.util.Log;
import fi.iki.elonen.NanoHTTPD;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * Created by jens on 25.03.17.
 */
public class MyHTTPD extends NanoHTTPD {
    public static final int PORT = 8765;

    public MyHTTPD() throws IOException {
        super(PORT);
    }

    @Override
    public Response serve(IHTTPSession session) {
        String msg = "<html><body><h1>Android Web Server</h1>\n";
        msg += "<h3>IoT Research Group - INATEL</h3>\n";
        Map<String, String> parms = session.getParms();
        if (parms.get("comando") == null) {
            msg += "<form action='?' method='get'>\n  <p>Insira chave: <input type='text' name='comando'></p>\n" + "</form>\n";
        } else {
            msg += "<p>Hello, " + parms.get("comando") + "!</p>";
            msg = parms.get("comando");
        }
        return newFixedLengthResponse(msg + "</body></html>\n");
    }
}


