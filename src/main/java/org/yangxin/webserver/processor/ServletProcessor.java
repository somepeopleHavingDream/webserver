package org.yangxin.webserver.processor;

import org.yangxin.webserver.connector.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author yangxin
 * 2020/10/03 15:01
 */
public class ServletProcessor {

    public URLClassLoader getServletLoader() throws MalformedURLException {
        File webRoot = new File(ConnectorUtils.WEB_ROOT);
        URL webRootUrl = webRoot.toURI().toURL();
        return new URLClassLoader(new URL[]{webRootUrl});
    }

    public Servlet getServlet(URLClassLoader loader, Request request) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        /*
            /servlet/TimeServlet
         */
        String uri = request.getRequestUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);

        Class<?> servletClass = loader.loadClass(servletName);
        return (Servlet) servletClass.newInstance();
    }

    public void process(Request request, Response response) throws MalformedURLException {
        URLClassLoader loader = getServletLoader();
        try {
            Servlet servlet = getServlet(loader, request);
            RequestFacade requestFacade = new RequestFacade(request);
            ResponseFacade responseFacade = new ResponseFacade(response);
            servlet.service(requestFacade, responseFacade);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
