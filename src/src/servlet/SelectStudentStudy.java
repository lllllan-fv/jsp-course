package servlet;

import DB.DBConnection;
import bean.StudentStudy;
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

@WebServlet("/SelectStudentStudy")
public class SelectStudentStudy extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String student = request.getParameter("student");

        PrintWriter printWriter = response.getWriter();
        DBConnection dbConnection = new DBConnection();
        dbConnection.createConnection();

        String sql = "select * from study where student = \"" + student + "\"";
        ArrayList<Map<String, String>> maps = dbConnection.queryForList(sql);

        List<Object> list = new ArrayList<>();
        for (Map<String, String> map : maps) {
            String course = map.get("course");
            String finish = map.get("finish");

            StudentStudy studentStudy = new StudentStudy(student, course, finish);
            list.add(studentStudy);
        }

        String json = new Gson().toJson(new Message("success", "查询成功", list));
        printWriter.println(json);

        printWriter.close();
        dbConnection.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
