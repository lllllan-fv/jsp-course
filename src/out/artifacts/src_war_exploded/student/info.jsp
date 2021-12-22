<%--
  Created by IntelliJ IDEA.
  User: lllllan
  Date: 2021/12/11
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<el-card shadow="hover" style="margin: 20px; border-radius: 10px" align="center">
    <h2>姓名：{{ info.name }} </h2>
    <h4>学号：{{ info.id }} </h4>
    <h4>学院： {{ info.college }} </h4>
    <h4>专业：{{ info.profession }} </h4>
</el-card>


<el-card shadow="hover" style="margin: 20px; border-radius: 10px" align="center">
    <el-table :data="studyTable"
              style="width: 100%"
              align="center" stripe>
        <template v-for="item in studyCol">
            <el-table-column :prop="item.prop"
                             :label="item.label"
                             align="center"
                             width="400">
            </el-table-column>
        </template>
        <%--        <el-table-column align="center"--%>
        <%--                         label="操作"--%>
        <%--                         width="300">--%>
        <%--            <template slot-scope="scope">--%>
        <%--                <el-button @click="deleteStudy(scope.row)" type="text">退选</el-button>--%>
        <%--            </template>--%>
        <%--        </el-table-column>--%>
    </el-table>
</el-card>