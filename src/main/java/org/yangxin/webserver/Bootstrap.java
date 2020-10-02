package org.yangxin.webserver;

import org.yangxin.webserver.connector.Connector;

/**
 * @author yangxin
 * 2020/10/02 17:05
 */
public final class Bootstrap {

    public static void main(String[] args) {
        Connector connector = new Connector();
        connector.start();
    }
}
