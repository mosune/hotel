<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();   
	String root = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>宾馆管理系统 - 500错误</title>
    <link href="<%=root %>/resources/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=root %>/resources/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="<%=root %>/resources/css/animate.css" rel="stylesheet">
    <link href="<%=root %>/resources/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="middle-box text-center animated fadeInDown">
        <h1>500</h1>
        <h3 class="font-bold">服务器内部错误</h3>
        <div class="error-desc">
            服务器好像出错了...
            <br/>您可以返回主页看看
            <br/><a href="<%=root %>/toIndex.do" class="btn btn-primary m-t">主页</a>
        </div>
    </div>
    <!-- 全局js -->
    <script src="<%=root %>/resources/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=root %>/resources/js/bootstrap.min.js?v=3.3.6"></script>
</body>
</html>