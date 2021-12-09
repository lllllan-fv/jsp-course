package DB;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBConnection {
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://1.116.114.142:3306/course";
    private String username = "root";
    private String password = "Lllllan@0424";

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public DBConnection() {
        Properties prop = new Properties();
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("db.properties");
            prop.load(is);

            this.setDriver(prop.getProperty("driver"));
            this.setUrl(prop.getProperty("url"));
            this.setUsername(prop.getProperty("username"));
            this.setPassword(prop.getProperty("password"));

            if (is != null) is.close();
        } catch (Exception e) {
            System.out.println(e + " file db.properties not found");
        }
    }

    //创建连接
    public void createConnection() {
        try {
            Class.forName(driver);
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询，返回原格式
    public ResultSet queryForRS(String sql) {
        ResultSet rs = null;
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(sql);

            System.out.println("查询操作执行成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // 执行查询，返回键值对
    public ArrayList<Map<String, String>> queryForList(String sql) {
        ArrayList<Map<String, String>> results = null;
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(sql);
            if (rs != null) {
                results = new ArrayList<>();
                while (rs.next()) {
                    Map<String, String> result = new HashMap<>();
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        String fieldName = metaData.getColumnName(i).toLowerCase();
                        String fieldValue = rs.getString(i);
                        result.put(fieldName, fieldValue);
                    }
                    results.add(result);
                }
            }

            System.out.println("查询操作执行成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }


    // 执行更新
    public int update(String sql) {
        int count = 0;
        try {
            stmt = con.createStatement();
            count = stmt.executeUpdate(sql);

            System.out.println("更新操作执行成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    // 关闭相关对象
    public void close() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();

            System.out.println("数据库关闭成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setDriver(String driver) {
        this.driver = driver;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setPassword(String password) {
        this.password = password;
    }

}

