package org.yangxin.webserver.connector;

import org.junit.Assert;
import org.junit.Test;
import org.yangxin.webserver.util.TestUtils;

/**
 * @author yangxin
 * 2020/10/02 12:33
 */
public class RequestTest {

    private static final String VALID_REQUEST = "GET /index.html HTTP/1.1";

    @Test
    public void givenValidRequestThenExtractUri() {
        Request request = TestUtils.createRequest(VALID_REQUEST);
        Assert.assertEquals("/index.html", request.getRequestUri());
    }
}