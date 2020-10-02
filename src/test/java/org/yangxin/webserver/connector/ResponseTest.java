package org.yangxin.webserver.connector;

import org.junit.Assert;
import org.junit.Test;
import org.yangxin.webserver.util.TestUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author yangxin
 * 2020/10/02 14:42
 */
public class ResponseTest {

    private static final String VALID_REQUEST = "GET /index.html HTTP/1.1";
    private static final String INVALID_REQUEST = "GET /notfound.html HTTP/1.1";

    private static final String STATUS_200 = "HTTP/1.1 200 OK\r\n\r\n";
    private static final String STATUS_404 = "HTTP/1.1 404 File Not Found\r\n\r\n";

    @Test
    public void givenValidRequestThenReturnStaticResource() throws IOException {
        Request request = TestUtils.createRequest(VALID_REQUEST);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Response response = new Response(out);
        response.setRequest(request);
        response.sentStaticResource();

        String resource = TestUtils.readFileToString(ConnectorUtils.WEB_ROOT + request.getRequestURI());
        Assert.assertEquals(STATUS_200 + resource, out.toString());
    }

    @Test
    public void givenInvalidRequestThenReturnError() throws IOException {
        Request request = TestUtils.createRequest(INVALID_REQUEST);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Response response = new Response(out);
        response.setRequest(request);
        response.sentStaticResource();

        String resource = TestUtils.readFileToString(ConnectorUtils.WEB_ROOT + File.separator + "404.html");
        Assert.assertEquals(STATUS_404 + resource, out.toString());
    }
}