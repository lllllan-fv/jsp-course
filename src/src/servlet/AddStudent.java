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
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String college = request.getParameter("college");
        String profession = request.getParameter("profession");

        PrintWriter printWriter = response.getWriter();
        DBConnection dbConnection = new DBConnection();
        dbConnection.createConnection();

        String sql = "select * from account where id like \"%S%\" order by id desc limit 1";
        ArrayList<Map<String, String>> maps = dbConnection.queryForList(sql);
        String id = maps.get(0).get("id");
        String account = "S" + (Integer.parseInt(id.substring(1)) + 1);

        sql = " insert account values(\"" + account + "\", \"student\", \"" + name + "\", \"" + college + "\", \"" + profession + "\", null)";
        dbConnection.update(sql);

        printWriter.println(new Gson().toJson(new Message("success", "添加成功", null)));

        dbConnection.close();
        printWriter.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
