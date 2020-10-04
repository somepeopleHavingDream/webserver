package org.yangxin.webserver.connector;

import org.yangxin.webserver.processor.ServletProcessor;
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
                // 获取客户端输入输出流
                Socket socket = server.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                // 通过客户端输入流构建请求
                Request request = new Request(inputStream);
                request.parse();

                // 通过客户端输出流和请求构建响应
                Response response = new Response(outputStream);
                response.setRequest(request);

                // 根据请求的分类（动态请求、静态请求），交由不同的处理器类来处理
                if (request.getRequestURI().startsWith("/servlet/")) {
                    ServletProcessor processor = new ServletProcessor();
                    processor.process(request, response);
                } else {
                    StaticProcessor processor = new StaticProcessor();
                    processor.process(request, response);
                }

                // 关闭客户端套接字
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
