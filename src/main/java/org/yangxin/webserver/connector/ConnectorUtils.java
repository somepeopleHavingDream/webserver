package org.yangxin.webserver.connector;

import java.io.File;

/**
 * @author yangxin
 * 2020/10/02 14:21
 */
public class ConnectorUtils {

    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator  + "webroot";

    public static final String PROTOCOL = "HTTP/1.1";

    public static final String CARRIAGE = "\r";

    public static final String NEWLINE = "\n";

    public static final String SPACE = " ";

    public static String renderStatus(HttpStatus status) {
        return PROTOCOL +
                SPACE +
                status.getStatusCode() +
                SPACE +
                status.getReason() +
                CARRIAGE + NEWLINE +
                CARRIAGE + NEWLINE;
    }

}
