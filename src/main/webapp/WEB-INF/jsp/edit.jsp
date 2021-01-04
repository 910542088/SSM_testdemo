<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/25
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    #one{
        height: 200px;
        width:300px;
        background-color: greenyellow;
        text-align: center;
        border: 2px solid #f00;
        margin: auto
    }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script>
    $(function (){


        $("#button").click(function (){
            //注意使用EL表达式的时候在js中需要加上""!!!!!!!!!!!!
            $.ajax({
                url:"/update.do",
                data:{"id":"${stu.id}", "name":$("#name").val(),"age":$("#age").val()},
                success:function (resp){
                    if (resp==="1"){
                        alert("修改成功！")
                        window.location.href = "/index.jsp"
                    }else{
                        alert("修改失败！")
                    }
                }
            })
        })
    })

</script>
<body>
<div id="one">
    <form>
        <br/><br/>
        id:<input type="text" name="id" value="${stu.id}"+ disabled><br/><br/>
        name:<input type="text" id="name" value="${stu.name}"><br/><br/>
        age:<input type="text" id="age" value="${stu.age}"><br/><br/>
        <input type="button" id="button">&nbsp;&nbsp;
        <input type="reset">
    </form>
</div>

</body>
</html>
