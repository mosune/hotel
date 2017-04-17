<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<script src="<%=root %>/resources/js/sweetalert.min.js"></script>
	<script src="<%=root %>/resources/js/content.js?v=1.0.0"></script>
	<link rel="stylesheet" type="text/css" href="<%=root %>/resources/css/sweetalert.css" rel="stylesheet">
    <link href="<%=root %>/resources/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=root %>/resources/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <!-- Morris -->
    <link href="<%=root %>/resources/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
    <link href="<%=root %>/resources/css/animate.css" rel="stylesheet">
    <link href="<%=root %>/resources/css/style.css?v=4.1.0" rel="stylesheet">
<script type="text/javascript">
    $(document).ready(function () {
        $('.contact-box').each(function () {
            animationHover(this, 'pulse');
        });
    });
</script>
<style type="text/css">
</style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
        	<c:forEach items="${roomtypes}" var="roomtype">
	            <div class="col-sm-12">
	                <div class="contact-box">
	                    <a href="<%=root%>/living/live/toChooseRoom.do?id=${roomtype.id}">
	                        <div class="col-sm-12">
	                            <center><h1><strong>${roomtype.name}</strong></h1></center>
	                            <center><h2>还剩${roomtype.count}间^-^</h2></center>
	                            <center><h4>${roomtype.remark}</h4></center>
	                        </div>
	                        <div class="clearfix"></div>
	                    </a>
	                </div>
	            </div>
            </c:forEach>
         </div>
    </div>
</body>
</html>