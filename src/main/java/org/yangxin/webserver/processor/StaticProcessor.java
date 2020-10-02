package org.yangxin.webserver.processor;

import org.yangxin.webserver.connector.Request;
import org.yangxin.webserver.connector.Response;

import java.io.IOException;

/**
 * @author yangxin
 * 2020/10/02 16:46
 */
public class StaticProcessor {

    public void process(Request request, Response response) {
        try {
            response.sentStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
