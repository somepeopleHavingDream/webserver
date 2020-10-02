package org.yangxin.webserver.connector;

import javax.servlet.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

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
public class Request implements ServletRequest {

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

    public Object getAttribute(String name) {
        return null;
    }

    public Enumeration<String> getAttributeNames() {
        return null;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public void setCharacterEncoding(String env) {

    }

    public int getContentLength() {
        return 0;
    }

    public long getContentLengthLong() {
        return 0;
    }

    public String getContentType() {
        return null;
    }

    public ServletInputStream getInputStream() {
        return null;
    }

    public String getParameter(String name) {
        return null;
    }

    public Enumeration<String> getParameterNames() {
        return null;
    }

    public String[] getParameterValues(String name) {
        return new String[0];
    }

    public Map<String, String[]> getParameterMap() {
        return null;
    }

    public String getProtocol() {
        return null;
    }

    public String getScheme() {
        return null;
    }

    public String getServerName() {
        return null;
    }

    public int getServerPort() {
        return 0;
    }

    public BufferedReader getReader() {
        return null;
    }

    public String getRemoteAddr() {
        return null;
    }

    public String getRemoteHost() {
        return null;
    }

    public void setAttribute(String name, Object o) {

    }

    public void removeAttribute(String name) {

    }

    public Locale getLocale() {
        return null;
    }

    public Enumeration<Locale> getLocales() {
        return null;
    }

    public boolean isSecure() {
        return false;
    }

    public RequestDispatcher getRequestDispatcher(String path) {
        return null;
    }

    public String getRealPath(String path) {
        return null;
    }

    public int getRemotePort() {
        return 0;
    }

    public String getLocalName() {
        return null;
    }

    public String getLocalAddr() {
        return null;
    }

    public int getLocalPort() {
        return 0;
    }

    public ServletContext getServletContext() {
        return null;
    }

    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }

    public boolean isAsyncStarted() {
        return false;
    }

    public boolean isAsyncSupported() {
        return false;
    }

    public AsyncContext getAsyncContext() {
        return null;
    }

    public DispatcherType getDispatcherType() {
        return null;
    }
}
