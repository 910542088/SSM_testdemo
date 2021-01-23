<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/24
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()+"://"+request.getServerName() +":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <title>Title</title>
    <base href="<%=basePath%>">
</head>
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<style>
    #p {
        background-color: aquamarine;
        height: 500px;
        width: 600px;
        text-align: center;
        margin: auto
    }
</style>
<script>
    //function中写入注册事件&需要触发的函数
    $(function () {
        //页面加载刷新第一页
        fresh(1);
        //全选&全不选
        $("#one").click(function () {
            $("input[name=checkbox]").prop("checked", this.checked);
        });
        $("#tbody").on("click", $("input[name=checkbox]"), function () {
            $("#one").prop("checked",
                $("input[name=checkbox]").length === $("input[name=checkbox]:checked").length)
        })
        //删除数据按钮
        $("#button").click(function () {
            var $check = $("input[name=checkbox]:checked");
            if ($check.length === 0) {
                alert("wrong!!!!!!!")
            } else {
                var data = [];
                $.each($check, function (i, e) {
                    data[i] = e.value;
                })
                if (confirm("确定要删除吗？")) {
                    $.ajax({
                        type: "post",
                        url: "delete.do",
                        data: {"data": data},
                        //不适用传统请求发送,后台接收不到!!!
                        traditional: true,
                        success: function (resp) {
                            alert(resp)
                            fresh();
                        }
                    })
                }
            }
        });
        //条件查询按钮
        $("#btn").click(function () {
            fresh();
        })
    })
    //刷新页面
    function fresh(page) {
        $("#tbody").empty();
        $.ajax({
            type: "post",
            url: "limit.do",
            data: {
                "name": $("#name").val(),
                "age": $("#age").val(),
                "pageNumber": page,
                "pageSize": 5,
            },
            success: function (resp) {
                $("#totalPage").html(resp.totalPage)
                $("#currentPage").html(resp.currentPage)
                $("#total").html(resp.total)
                $.each(resp.list, function (i, e) {
                    $("#tbody").append("<tr>")
                        .append("<td><input type='checkbox' name='checkbox' value='" + e.id + "'></td>")
                        .append("<td>" + e.id + "</td>")
                        .append("<td>" + e.name + "</td>")
                        .append("<td>" + e.age + "</td>")
                        .append("<td colspan='2'><a href='/edit.do?id=" + e.id + "'>edit</a></td>")
                        .append("<tr>")
                })
            }
        })
    }
    //+-页面
    function add() {
        var $currentPage = parseInt($("#currentPage").html());
        var $total = parseInt($("#totalPage").html());
        if ($currentPage === $total) {
            alert("已经到底啦!")
        } else {
            fresh($currentPage + 1)
        }
    }
    function sub() {
        var $currentPage = parseInt($("#currentPage").html());
        if ($currentPage === 1) {
            alert("已经是第一页啦!");
        } else {
            fresh($currentPage - 1)
        }
    }

</script>
<body>
<div id="p">
    name:<input type="text" id="name">&nbsp;&nbsp;&nbsp;age:<input type="text" id="age">&nbsp;&nbsp;&nbsp;
    <input type="button" id="btn" value="搜索">
    <center>
        <div id="div"></div>
        <table border="2px">
            <thead>
            <td><input type="checkbox" id="one"></td>
            <td>id</td>
            <td>name</td>
            <td>age</td>
            <td><input type="button" value="delete" id="button"></td>
            </thead>
            <tbody id="tbody"></tbody>
        </table>
    </center>
    当前第<span id="currentPage">?</span>页/共<span id="totalPage">?</span>页&nbsp;&nbsp;
    共有<span id="total">?</span>条数据 <br>
    <button onclick="sub()" id="u">上一页</button>
    <button onclick="add()" id="d">下一页</button>

</div>
</form>
</body>
</html>
