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
import java.io.PrintWriter;

@WebServlet("/AddCourse")
public class AddCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String code = String.valueOf(System.currentTimeMillis());
        String name = request.getParameter("name");
        String semester = request.getParameter("semester");
        String number = request.getParameter("number");

        PrintWriter printWriter = response.getWriter();
        DBConnection dbConnection = new DBConnection();
        dbConnection.createConnection();

        String sql = "insert course values(\"" + code + "\", \"" + name + "\", null, null, \"" + semester + "\", \"" + number + "\", null)";
        dbConnection.update(sql);

        printWriter.println(new Gson().toJson(new Message("success", "添加成功", null)));

        dbConnection.close();
        printWriter.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
