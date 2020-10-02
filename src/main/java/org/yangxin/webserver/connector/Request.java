package org.yangxin.webserver.connector;

import java.io.IOException;
import java.io.InputStream;

/**
 * GET /index.html HTTP/1.1
 *      Host: localhost:8888
 *      Connection: keep-alive
 *      Cache-Control: max-age=0
 *      Upgrade-Insecure-Requests: 1
 *      User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS)
 *
 * @author yangxin
 * 2020/10/02 12:11
 */
public class Request {

    private static final int BUFFER_SIZE = 1024;

    private final InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getRequestURI() {
        return uri;
    }

    public void parse() {
        int length = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            length = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder request = new StringBuilder();
        for (int i = 0; i < length; i++) {
            request.append((char) buffer[i]);
        }
        uri = parseUri(request.toString());
    }

    private String parseUri(String s) {
        int index1, index2;
        index1 = s.indexOf(' ');
        if (index1 != -1) {
            index2 = s.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return s.substring(index1 + 1, index2);
            }
        }
        return "";
    }
}
