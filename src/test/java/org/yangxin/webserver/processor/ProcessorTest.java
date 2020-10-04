package org.yangxin.webserver.processor;

import org.junit.Assert;
import org.junit.Test;
import org.yangxin.webserver.connector.Request;
import org.yangxin.webserver.util.TestUtils;

import javax.servlet.Servlet;
import java.net.MalformedURLException;
import java.net.URLClassLoader;

/**
 * @author yangxin
 * 2020/10/04 22:07
 */
public class ProcessorTest {

    private static final String SERVLET_REQUEST = "GET /servlet/TimeServlet HTTP/1.1";

    @Test
    public void givenServletRequestThenLoadServlet() throws MalformedURLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Request request = TestUtils.createRequest(SERVLET_REQUEST);
        ServletProcessor processor = new ServletProcessor();
        URLClassLoader loader = processor.getServletLoader();
        Servlet servlet = processor.getServlet(loader, request);

        Assert.assertEquals("TimeServlet", servlet.getClass().getName());
    }
}
