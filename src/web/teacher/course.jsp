<%--
  Created by IntelliJ IDEA.
  User: lllllan
  Date: 2021/12/10
  Time: 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<el-card shadow="hover" style="margin: 20px; border-radius: 10px" align="center">
    <el-form :inline="true" :model="courseForm" class="demo-form-inline" style="margin-bottom: 0">
        <el-form-item label="课程名称" style="margin-bottom: 0">
            <el-input v-model="courseForm.name" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="开设学期" style="margin-bottom: 0">
            <el-input v-model="courseForm.semester"></el-input>
        </el-form-item>
        <el-form-item label="人数上限" style="margin-bottom: 0">
            <el-input v-model="courseForm.number" placeholder=""></el-input>
        </el-form-item>
        <el-form-item style="margin-bottom: 0">
            <el-button type="primary" @click="addCourse">添加课程</el-button>
        </el-form-item>
    </el-form>
</el-card>

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
</el-table>