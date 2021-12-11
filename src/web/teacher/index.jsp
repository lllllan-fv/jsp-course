<%--
  Created by IntelliJ IDEA.
  User: lllllan
  Date: 2021/12/10
  Time: 8:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师端</title>

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
    <el-tabs type="border-card" style="margin-top: 100px; border-radius: 5px">
        <el-tab-pane label="学生管理">
            <jsp:include page="student.jsp"></jsp:include>
        </el-tab-pane>
        <el-tab-pane label="课程管理">
            <jsp:include page="course.jsp"></jsp:include>
        </el-tab-pane>
        <el-tab-pane label="选课管理">
            <jsp:include page="study.jsp"></jsp:include>
        </el-tab-pane>
    </el-tabs>
</div>

<script>
    var vm = new Vue({
        el: '#app',
        data: {
            studentForm: {name: '', college: '', profession: '',},
            studentCol: [
                {prop: 'id', label: '学号'},
                {prop: 'name', label: '姓名'},
                {prop: 'college', label: '学院'},
                {prop: 'profession', label: '专业'},
                {prop: 'contact', label: '联系方式'},
            ],
            studentTable: [],
            courseForm: {name: '', semester: '2021-2022-1', number: ''},
            courseCol: [
                {prop: 'code', label: '课程代码'},
                {prop: 'name', label: '课程名称'},
                {prop: 'semester', label: '开设学期'},
                {prop: 'number', label: '课程余量'},
                {prop: 'summary', label: '课程简介'},
                {prop: 'profession', label: '适用专业'},
                {prop: 'qualifications', label: '选课资格'},
            ],
            courseTable: [],
            studyCol: [
                {prop: 'id', label: '学号'},
            ],
            studyTable: [],
        },
        methods: {
            selectStudent: function () {
                var students = [];
                var status = '';
                var message = '';

                $.ajax({
                    type: "POST",
                    url: "/src/SelectStudent",
                    async: false,//取消异步请求
                    data: {},
                    success: function (data) {
                        var json = JSON.parse(data);
                        console.log(json);
                        students = json.code[0];
                        status = json.status;
                        message = json.message;
                    },
                    error: function (msg) {
                        console.log(msg);
                    }
                });

                this.studentTable = students;
                console.log(this.studentTable);
            },
            addStudent: function () {
                var data = this.studentForm;
                var status = '';
                var message = '';

                $.ajax({
                    type: "POST",
                    url: "/src/AddStudent",
                    async: false,//取消异步请求
                    data: data,
                    success: function (data) {
                        var json = JSON.parse(data);
                        status = json.status;
                        message = json.message;
                    },
                    error: function (msg) {
                        console.log(msg);
                    }
                });
                this.$message(message);

                this.selectStudent();
            },
            selectCourse: function () {
                var course = [];
                var status = '';
                var message = '';

                $.ajax({
                    type: "POST",
                    url: "/src/SelectCourse",
                    async: false,//取消异步请求
                    data: {},
                    success: function (data) {
                        var json = JSON.parse(data);
                        console.log(json);
                        course = json.code[0];
                        status = json.status;
                        message = json.message;
                    },
                    error: function (msg) {
                        console.log(msg);
                    }
                });
                this.courseTable = course;
                console.log(this.courseTable);
            },
            addCourse: function () {
                var data = this.courseForm;
                var status = '';
                var message = '';

                $.ajax({
                    type: "POST",
                    url: "/src/AddCourse",
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
                this.$message(message);
                this.selectCourse();
            }
        },
        beforeMount: function () {
            this.selectStudent();
            this.selectCourse();
        }
    })
</script>

</body>
</html>
