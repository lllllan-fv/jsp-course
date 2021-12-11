package servlet;

import DB.DBConnection;
import bean.Account;
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

@WebServlet("/SelectInfo")
public class SelectInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String account = request.getParameter("account");

        PrintWriter printWriter = response.getWriter();
        DBConnection dbConnection = new DBConnection();
        dbConnection.createConnection();

        String sql = "select * from account where id=\"" + account + "\"";
        ArrayList<Map<String, String>> maps = dbConnection.queryForList(sql);

        Map<String, String> map = maps.get(0);
        String id = map.get("id");
        String pwd = map.get("password");
        String name = map.get("name");
        String college = map.get("college");
        String profession = map.get("profession");
        String contact = map.get("contact");
        Account account1 = new Account(id, pwd, name, college, profession, contact);

        List<Account> list = new ArrayList<>();
        list.add(account1);
        String json = new Gson().toJson(new Message("success", "查询成功", Collections.singletonList(list)));
        printWriter.println(json);

        dbConnection.close();
        printWriter.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
