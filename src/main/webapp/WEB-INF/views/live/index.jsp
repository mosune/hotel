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
    function gotoLiving() {
    	if ("${livingNum}" > 0) $('#living').attr('href','<%=root%>/living/live/chooseType.do');
    	else toastr.error("很抱歉，没有房间了!");
    }
    function gotoAccounting() {
    	if ("${livedNum}" > 0) $('#lived').attr('href','<%=root%>/living/live/listsLiving.do?type=1');
    	else toastr.error("很抱歉，没有房间可以结账了!");
    }
    function gotoSchedule() {
    	if ("${scheduleNum}" > 0) $('#order').attr('href','<%=root%>/living/live/listsLiving.do?type=2');
    	else toastr.error("很抱歉，没有预定了的房间!");
    }
</script>
<style type="text/css">
</style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-6">
                <div class="contact-box">
                    <a id="living" onclick="gotoLiving();">
                        <div class="col-sm-12">
                            <center><h1><strong>住宿</strong></h1></center>
                            <center><h2>还剩${livingNum}间^-^</h2></center>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="contact-box">
                    <a id="lived" onclick="gotoAccounting();">
                        <div class="col-sm-12">
                            <center><h1><strong>结账</strong></h1></center>
                            <center><h2>共有${livedNum}间^-^</h2></center>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="contact-box">
                    <a id="order" onclick="gotoSchedule();">
                        <div class="col-sm-12">
                            <center><h1><strong>预定房</strong></h1></center>
                            <center><h2>共有${scheduleNum}间^-^</h2></center>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
         </div>
    </div>
</body>
</html>