package org.yangxin.webserver.connector;

import org.yangxin.webserver.processor.StaticProcessor;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yangxin
 * 2020/10/02 16:49
 */
public class Connector implements Runnable {

    private static final int DEFAULT_PORT = 8888;

    private ServerSocket server;
    private final int port;

    public Connector() {
        this(DEFAULT_PORT);
    }

    public Connector(int port) {
        this.port = port;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            System.out.println("启动服务器，监听端口：" + port);

            while (true) {
                Socket socket = server.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                Request request = new Request(inputStream);
                request.parse();

                Response response = new Response(outputStream);
                response.setRequest(request);

                StaticProcessor processor = new StaticProcessor();
                processor.process(request, response);

                close(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(server);
        }
    }

    private void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }

        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
