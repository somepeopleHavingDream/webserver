package org.yangxin.webserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author yangxin
 * 2020/10/02 17:08
 */
public class TestClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8888);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("GET /servlet/TimeServlet HTTP/1.1".getBytes());
//        outputStream.write("GET /index.html HTTP/1.1".getBytes());
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[2048];
        int length = inputStream.read(buffer);
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < length; i++) {
            response.append((char) buffer[i]);
        }
        System.out.println(response.toString());
        socket.shutdownInput();

        socket.close();
    }
}
