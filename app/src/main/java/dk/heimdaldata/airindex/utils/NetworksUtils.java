package dk.heimdaldata.airindex.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NetworksUtils {
    public static String getResponse(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }

    }
    public static String getResponseWithHeader(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        // add header by using Map object
        Map<String, String> headers = new HashMap<>();
        // u can add more set of key:value like "content-type","application/json"
        headers.put("x-api-key", "eJtubUGuUa1sWhXaODJScg==WEsZqJobpApoeTH5");
        // header.keySet() return array of String
        // for every headerKey in that array, do add property to urlConnection
        for (String headerKey : headers.keySet()) {
            urlConnection.setRequestProperty(headerKey, headers.get(headerKey));
        }
        // complete set header to urkConnection
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
