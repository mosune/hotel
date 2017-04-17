<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <title>宾馆管理系统</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="<%=root %>/resources/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=root %>/resources/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=root %>/resources/css/animate.css" rel="stylesheet">
    <link href="<%=root %>/resources/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <i class="fa fa-area-chart"></i>
                                        <strong class="font-bold">宾馆管理系统</strong>
                                    </span>
                                </span>
                            </a>
                        </div>
                        <div class="logo-element">hotel
                        </div>
                    </li>
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">gcg</span>
                    </li>
                    <li>
                        <a class="J_menuItem" href="<%=root %>/toHome.do">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">主页</span>
                        </a>
                    </li>
                    <c:forEach items="${user.menus}" var="allMenu">
                    	<li>
                    		<c:if test="${allMenu.pid == 0}">
	                    		<a href="#">
		                            <span class="nav-label">${allMenu.menuName}</span>
		                            <span class="fa arrow"></span>
		                        </a>
	                        </c:if>
	                        <ul class="nav nav-second-level">
	                        	<c:forEach items="${user.menus}" var="menu">
	                        		<c:if test="${allMenu.id == menu.pid && menu.pid != 0}">
		                        		<li>
			                                <a class="J_menuItem" href="<%=root %>${menu.path}">${menu.menuName}</a>
			                            </li>
		                            </c:if>
	                            </c:forEach>
	                        </ul>
                    	</li>
                    </c:forEach>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i class="fa fa-bars"></i> </a>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown">
                            你好,${user.realName}
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" onclick="showModal();" href="javascript:;">
                           	     修改密码
                            </a>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" href="<%=root %>/loginOut.do">
                           	     退出
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe id="J_iframe" width="100%" height="100%" frameborder="0" src="<%=root %>/toHome.do"></iframe>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>
    
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div style="width:900px;" class="modal-dialog model-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">修改密码</h4>
			</div>
			<div class="modal-body">
				<div class="row">
		            <div class="col-sm-12">
		                 <form class="form-horizontal">
		                     <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>原密码：</label>
                                <div class="col-sm-10">
                                    <input id="oldPassword" type="password" name="oldPassword" value="" class="form-control">
                                </div>
                            </div>
		                     <div class="hr-line-dashed"></div>
		                     <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>新密码：</label>
                                <div class="col-sm-10">
                                    <input id="newPassword" type="password" name="newPassword" value="" class="form-control">
                                </div>
                            </div>
		                     <div class="hr-line-dashed"></div>
		                     <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>确认密码：</label>
                                <div class="col-sm-10">
                                    <input id="confirmPassword" type="password" name="confirmPassword" value="" class="form-control">
                                </div>
                            </div>
		                 </form>
		            </div>
		        </div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" onclick="changePwd();" class="btn btn-primary">确认</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

    <!-- 全局js -->
    <script src="<%=root %>/resources/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="<%=root %>/resources/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="<%=root %>/resources/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- 自定义js -->
    <script src="<%=root %>/resources/js/hAdmin.js?v=4.1.0"></script>
    <script type="text/javascript" src="<%=root %>/resources/js/index.js"></script>

    <!-- 第三方插件 -->
    <script src="<%=root %>/resources/js/plugins/pace/pace.min.js"></script>
	<div style="text-align:center;">
	<p>来源:<a href="javascript:;" target="_blank">葛晨光</a></p>
</div>
<script type="text/javascript">
	function showModal() {
		$("#oldPassword").val("");
		$("#newPassword").val("");
		$("#confirmPassword").val("");
		$("#myModal").modal("show");
	}
	function changePwd() {
		if ($("#oldPassword").val() == "") {
			toastr.error("原密码不能为空!");
			return;
		}
		if ($("#newPassword").val() == "") {
			toastr.error("新密码不能为空!");
			return;
		}
		if ($("#newPassword").val() != '') {
			if ($("#newPassword").val().length < 6) {
				toastr.error("密码不能少于6位!");
				return false;
			}
		}
		if ($("#confirmPassword").val() == "") {
			toastr.error("确认密码不能为空!");
			return;
		}
		$.ajax({
			url: "<%=root%>/updatePwd.do",
			type: "post",
			data: {
				oldPassword:$("#oldPassword").val(),
				newPassword:$("#newPassword").val()
				},
			dataType: "json",
			success:function(result) {
				if(result.flag == 0) toastr.error("原密码错误!");
				else {
					toastr.success("密码修改成功!");
					$("#myModal").modal("hide");
				}
			}
		}); 
	}
</script>
</body>

</html>

