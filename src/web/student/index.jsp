<%--
  Created by IntelliJ IDEA.
  User: lllllan
  Date: 2021/12/11
  Time: 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生端</title>

    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

    <!-- 3.4.1 Bootstrap.min.css -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
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

    <!-- 0.21.1 axios.js -->
    <script src="https://cdn.bootcdn.net/ajax/libs/axios/0.21.1/axios.min.js"></script>

    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>

</head>
<body>

<div id="app" class="container">
    <el-tabs type="border-card" style="margin-top: 100px; border-radius: 5px">
        <el-tab-pane label="个人信息">
            <jsp:include page="info.jsp"></jsp:include>
        </el-tab-pane>
        <el-tab-pane label="进行选课">
            <jsp:include page="course.jsp"></jsp:include>
        </el-tab-pane>
    </el-tabs>
</div>

<script>
    var vm = new Vue({
        el: '#app',
        data: {
            info: {},
        },
        methods: {
            selectInfo: function () {
                var account = sessionStorage.getItem("account");
                console.log(account);

                var info = {};
                $.ajax({
                    type: "POST",
                    url: "/src/SelectInfo",
                    async: false,//取消异步请求
                    data: {
                        account: account
                    },
                    success: function (data) {
                        var json = JSON.parse(data);
                        console.log(json);
                        info = json.code[0][0];
                    },
                    error: function (msg) {
                        console.log(msg);
                    }
                });

                this.info = info;
            }
        },
        beforeMount: function () {
            this.selectInfo();
        }
    })
</script>

</body>
</html>
