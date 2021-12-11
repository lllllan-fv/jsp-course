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
import java.util.*;

@WebServlet("/SelectCourse")
public class SelectCourse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String student = request.getParameter("student");
        if (student == null) student = "";

        PrintWriter printWriter = response.getWriter();
        DBConnection dbConnection = new DBConnection();
        dbConnection.createConnection();

        String sql = "select * from study where student=\"" + student + "\"";
        ArrayList<Map<String, String>> has = dbConnection.queryForList(sql);
        Set<String> set = new HashSet<>();
        for (Map<String, String> ha : has) {
            set.add(ha.get("course"));
        }

        sql = "select course, count(*) from study group by course";
        ArrayList<Map<String, String>> count = dbConnection.queryForList(sql);
        Map<String, Integer> num = new HashMap<>();
        for (Map<String, String> map : count) {
            num.put(map.get("course"), Integer.valueOf(map.get("count(*)")));
        }

        sql = "select * from course";
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

            if (set.contains(name)) continue;
            if (num.containsKey(name)) {
                number = String.valueOf(Integer.valueOf(number) - num.get(name));
            }

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
