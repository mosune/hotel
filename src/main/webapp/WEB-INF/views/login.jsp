<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <title> - 登录</title>
    <link href="<%=root %>/resources/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=root %>/resources/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="<%=root %>/resources/css/animate.css" rel="stylesheet">
    <link href="<%=root %>/resources/css/style.css?v=4.1.0" rel="stylesheet">
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">BG</h1>

            </div>
            <h3>欢迎使用宾馆管理系统</h3>

            <form class="m-t" role="form" method="post" action="<%=root %>/login.do">
                <div class="form-group">
                    <input class="form-control" name="loginName" placeholder="用户名" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" placeholder="密码" required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>


                <p class="text-muted text-center" style="color:red;" >${tip}</p>

            </form>
        </div>
    </div>

    <!-- 全局js -->
    <script src="<%=root %>/resources/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=root %>/resources/js/bootstrap.min.js?v=3.3.6"></script>

    
    

</body>

</html>
