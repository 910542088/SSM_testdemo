<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/24
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div  style="height: 200px;width:300px;background-color: aqua;text-align: center;border: 2px solid #f00
                ;margin: auto">
        <form action="/add.do" method="post">
            <br/><br/>
            name:<input type="text" name="name"><br/><br/>
            age:<input type="text" name="age"><br/><br/>
            <input type="submit">&nbsp;&nbsp;
            <input type="reset">
        </form>
    </div>
</body>
</html>
