<%--
  Created by IntelliJ IDEA.
  User: lllllan
  Date: 2021/12/9
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选课系统</title>

    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
            integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
            crossorigin="anonymous"></script>

    <!-- bootstrap-icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.0/font/bootstrap-icons.css">

    <!-- 4.1.1 animate.min.css -->
    <link href="https://cdn.bootcdn.net/ajax/libs/animate.css/4.1.1/animate.min.css" rel="stylesheet">

    <!-- 1.1.2 wow.min.js -->
    <script src="https://cdn.bootcdn.net/ajax/libs/wow/1.1.2/wow.min.js"></script>

    <!-- 2.6.9 vue.min.js -->
    <script src="https://cdn.bootcdn.net/ajax/libs/vue/2.6.9/vue.min.js"></script>

    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>

</head>
<body>


<div id="app" class="container">

    <el-card style="margin: 100px; padding: 20px 250px 20px 200px; border-radius: 5px">

        <el-form :model="form" label-width="120px">
            <el-form-item label="学号/工号">
                <el-input v-model="form.account"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input type="password" v-model="form.pwd"></el-input>
            </el-form-item>
            <el-form-item>
                <el-checkbox v-model="form.teacher" label="管理"></el-checkbox>
            </el-form-item>
            <el-form-item>
                <el-button @click="formSubmit()">登录</el-button>
            </el-form-item>
        </el-form>

    </el-card>

</div>

<script>
    var vm = new Vue({
        el: '#app',
        data: {
            form: {
                account: '',
                pwd: '',
                teacher: ''
            },
        },
        methods: {
            formSubmit: function () {
                var data = this.form;
                var status = "";
                var message = "";

                if (data.pwd === 'close') {
                    message = "服务器已关闭";
                } else {
                    $.ajax({
                        type: "POST",
                        url: "/src/Login",
                        async: false,//取消异步请求
                        data: data,
                        success: function (data) {
                            console.log(data);
                            var json = JSON.parse(data);
                            status = json.status;
                            message = json.message;
                        },
                        error: function (msg) {
                            console.log(msg);
                        }
                    });
                }

                if (status === 'success') {
                    sessionStorage.setItem("account", data.account);

                    var tar = "student";
                    if (this.form.teacher === true && this.form.account.indexOf('T') === 0) {
                        tar = "teacher";

                        var item = sessionStorage.getItem("teacher");
                        if (item == null) item = 0;
                        item = parseInt(item) + 1;
                        sessionStorage.setItem("teacher", item);
                    } else {
                        var item = sessionStorage.getItem("student");
                        if (item == null) item = 0;
                        item = parseInt(item) + 1;
                        sessionStorage.setItem("student", item);
                    }

                    window.location.href = tar;
                }
                this.$message(message);
            }
        },
    })
</script>

</body>
</html>