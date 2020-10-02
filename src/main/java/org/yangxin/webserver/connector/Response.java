package org.yangxin.webserver.connector;

import java.io.*;

/**
 * HTTP/1.1 200 OK
 *
 * @author yangxin
 * 2020/10/22 14:12
 */
public class Response {

    private static final int BUFFER_SIZE = 1024;

    private Request request;
    private OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sentStaticResource() throws IOException {
        // todo ConnectorUtils的代码可能需要改写
        File file = new File(ConnectorUtils.WEB_ROOT, request.getRequestURI());
        try {
            write(file, HttpStatus.SC_OK);
        } catch (IOException e) {
            write(new File(ConnectorUtils.WEB_ROOT, "404.html"), HttpStatus.SC_NOT_FOUND);
        }
    }

    private void write(File resource, HttpStatus status) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(resource)) {
            outputStream.write(ConnectorUtils.renderStatus(status).getBytes());

            byte[] buffer = new byte[BUFFER_SIZE];
            int length;
            while ((length = fileInputStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
                outputStream.write(buffer, 0, length);
            }
        }
    }
}
