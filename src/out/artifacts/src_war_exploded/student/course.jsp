<%--
  Created by IntelliJ IDEA.
  User: lllllan
  Date: 2021/12/11
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<el-table :data="courseTable"
          style="width: 100%"
          align="center" stripe>
    <template v-for="item in courseCol">
        <el-table-column :prop="item.prop"
                         :label="item.label"
                         align="center"
                         width="150">
        </el-table-column>
    </template>
    <el-table-column align="center"
                     label="操作"
                     width="100">
        <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text">确认选课</el-button>
        </template>
    </el-table-column>
</el-table>