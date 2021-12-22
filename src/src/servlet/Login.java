package servlet;

import DB.DBConnection;
import com.google.gson.Gson;
import json.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        boolean flag = false;

        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");

        PrintWriter printWriter = response.getWriter();
        DBConnection dbConnection = new DBConnection();
        dbConnection.createConnection();

        String sql = "select * from account where (id=\"" + account + "\" and password=\"" + pwd + "\")";
        ArrayList<Map<String, String>> maps = dbConnection.queryForList(sql);

        String json;
        if (maps.isEmpty()) {
            json = new Gson().toJson(new Message("error", "账号或密码错误", null));
        } else {
            json = new Gson().toJson(new Message("success", "登录成功", null));

            if (flag) {
                Socket socket = new Socket("127.0.0.1", 8848);
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write("hello,server!".getBytes());
                outputStream.close();
                socket.close();
            }
        }
        printWriter.println(json);

        printWriter.close();
        dbConnection.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
