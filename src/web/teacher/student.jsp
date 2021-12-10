<%--
  Created by IntelliJ IDEA.
  User: lllllan
  Date: 2021/12/10
  Time: 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<el-card shadow="hover" style="margin: 20px; border-radius: 10px" align="center">
    <el-form :inline="true" :model="studentForm" class="demo-form-inline" style="margin-bottom: 0">
        <el-form-item label="学生姓名" style="margin-bottom: 0">
            <el-input v-model="studentForm.name" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="所在学院" style="margin-bottom: 0">
            <el-input v-model="studentForm.college" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="所在专业" style="margin-bottom: 0">
            <el-input v-model="studentForm.profession" placeholder=""></el-input>
        </el-form-item>
        <el-form-item style="margin-bottom: 0">
            <el-button type="primary" @click="addStudent">添加学生</el-button>
        </el-form-item>
    </el-form>
</el-card>

<el-table :data="studentTable"
          style="width: 100%"
          align="center" stripe>
    <template v-for="item in studentCol">
        <el-table-column :prop="item.prop"
                         :label="item.label"
                         align="center"
                         width="180">
        </el-table-column>
    </template>
</el-table>