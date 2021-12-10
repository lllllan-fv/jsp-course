package servlet;

import DB.DBConnection;
import bean.Course;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;

@WebServlet("/SelectCourse")
public class SelectCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter printWriter = response.getWriter();
        DBConnection dbConnection = new DBConnection();
        dbConnection.createConnection();

        String sql = "select * from course";
        ArrayList<Map<String, String>> maps = dbConnection.queryForList(sql);

        List<Course> list = new ArrayList<>();
        for (Map<String, String> map : maps) {
            String code = map.get("code");
            String name = map.get("name");
            String summary = map.get("summary");
            String profession = map.get("profession");
            String semester = map.get("semester");
            String number = map.get("number");
            String qualifications = map.get("qualifications");

            Course course = new Course(code, name, summary, profession, semester, number, qualifications);
            list.add(course);
        }
        String json = new Gson().toJson(new Message("success", "查询成功", Collections.singletonList(list)));
        printWriter.println(json);

        dbConnection.close();
        printWriter.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
