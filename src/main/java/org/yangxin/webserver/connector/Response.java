package org.yangxin.webserver.connector;

import java.io.*;
import java.util.Arrays;

/**
 * HTTP/1.1 200 OK
 *
 * @author yangxin
 * 2020/10/22 14:12
 */
public class Response {

    private static final int BUFFER_SIZE = 1024;

    private Request request;
    private final OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sentStaticResource() throws IOException {
        File file = new File(ConnectorUtils.WEB_ROOT, request.getRequestURI());
        try {
            write(file, HttpStatus.SC_OK);
        } catch (IOException e) {
            write(new File(ConnectorUtils.WEB_ROOT, "404.html"), HttpStatus.SC_NOT_FOUND);
        }
    }

    private void write(File resource, HttpStatus status) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(resource)) {
            // 先写入响应状态
            // 这个地方有些问题，outputStream会因为服务端系统或者程序的情况，可能会提前将缓冲区的数据发送到客户端，
            // 特别是服务端第一次启动的时候。考虑到更妥当的做法是由客户端来做，客户端来负责接收的数据是否完整，
            // 如果数据不完整，则客户端再次向服务端发送请求，服务端响应。
            // 上述所说的情况在服务端启动后很少出现这种情况，但这种情况服务端应该也有更好的处理，在此先忽略，采用客户端再次请求的方式来处理。
            outputStream.write(ConnectorUtils.renderStatus(status).getBytes());

            byte[] buffer = new byte[BUFFER_SIZE];
//            byte[] bytes = ConnectorUtils.renderStatus(status).getBytes();
//            System.arraycopy(bytes, 0, buffer, 0, bytes.length);
            int length;
            while ((length = fileInputStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
                outputStream.write(buffer, 0, length);
            }
        }
    }
}
