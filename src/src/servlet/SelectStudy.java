package servlet;

import DB.DBConnection;
import bean.Study;
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
import java.util.List;
import java.util.Map;

@WebServlet("/SelectStudy")
public class SelectStudy extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter printWriter = response.getWriter();
        DBConnection dbConnection = new DBConnection();
        dbConnection.createConnection();

        String sql = "select * from study where finish=-1";
        ArrayList<Map<String, String>> maps = dbConnection.queryForList(sql);

        List<Object> list = new ArrayList<>();
        for (Map<String, String> map : maps) {
            String student = map.get("student");
            String course = map.get("course");
            list.add(new Study(student, course));
        }

        String json = new Gson().toJson(new Message("success", "查询成功", list));
        printWriter.println(json);

        dbConnection.close();
        printWriter.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
