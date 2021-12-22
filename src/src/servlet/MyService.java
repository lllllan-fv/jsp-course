package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

@WebServlet("/MyService")
public class MyService extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter printWriter = response.getWriter();

        if (isPortUsing("127.0.0.1", 8848)) {
        } else {
            ServerSocket serverSocket = new ServerSocket(8848);
            while (true) {
                Socket socket = serverSocket.accept(); //这里来一个连接请求就让deal去处理，自己在这里等下一个连接请求
                deal(socket);
            }
        }

        response.sendRedirect("service.jsp");
    }

    public static boolean isPortUsing(String host, int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        try {
            Socket socket = new Socket(theAddress, port);
            flag = true;
        } catch (IOException e) {
        }
        return flag;
    }

    public static void deal(Socket socket) { //这里创建了一个新的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] buf = new byte[1024];
                int readLen = 0;
                try {
                    InputStream inputStream = socket.getInputStream();
                    if ((readLen = inputStream.read(buf)) == -1) {
                        System.out.println(new String(buf, 0, readLen));
                    }
                    inputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
