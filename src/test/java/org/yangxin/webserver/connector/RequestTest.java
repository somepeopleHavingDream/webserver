package org.yangxin.webserver.connector;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author yangxin
 * 2020/10/02 12:33
 */
public class RequestTest {

    private static final String VALID_REQUEST = "GET /index.html HTTP/1.1";

    @Test
    public void parse() {
        InputStream inputStream = new ByteArrayInputStream(VALID_REQUEST.getBytes());
        Request request = new Request(inputStream);
        request.parse();

        Assert.assertEquals("/index.html", request.getRequestURI());
    }
}