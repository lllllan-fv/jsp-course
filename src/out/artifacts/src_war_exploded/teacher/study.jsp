<%--
  Created by IntelliJ IDEA.
  User: lllllan
  Date: 2021/12/10
  Time: 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--<el-card shadow="hover" style="margin: 20px; border-radius: 10px" align="center">--%>
<%--    <el-button type="primary" @click=" ">开放选课</el-button>--%>
<%--    <el-button type="warning" @click=" ">关闭选课</el-button>--%>
<%--</el-card>--%>

<el-table :data="studyTable"
          style="width: 100%"
          align="center" stripe>
    <template v-for="item in studyCol">
        <el-table-column :prop="item.prop"
                         :label="item.label"
                         align="center"
                         width="180">
        </el-table-column>
    </template>
    <el-table-column align="center"
                     label="操作"
                     width="100">
        <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text">通过选课</el-button>
        </template>
    </el-table-column>
</el-table>