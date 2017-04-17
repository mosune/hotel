<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=root %>/resources/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="<%=root %>/resources/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="<%=root %>/resources/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="<%=root %>/resources/css/animate.css" rel="stylesheet">
<link href="<%=root %>/resources/css/style.css?v=4.1.0" rel="stylesheet">
<!-- 全局js -->
<script src="<%=root %>/resources/js/bootstrap.min.js?v=3.3.6"></script>

<!-- 自定义js -->
<script src="<%=root %>/resources/js/content.js?v=1.0.0"></script>

<!-- iCheck -->
<script src="<%=root %>/resources/js/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if ("${staff}" != '') {
			$("#passwordStaff").attr("disabled", true);
			if ("${staff.positionId}" == 3) {
				$("#optionsRadios1").attr("checked", true);
				$("#optionsRadios2").attr("checked", false);
			} else {
				$("#optionsRadios2").attr("checked", true);
				$("#optionsRadios1").attr("checked", false);
			}
		}
	});
	function checkForm() {
		if ($("#loginName").val() == '') {
			toastr.error("登录名不能为空!");
			return false;
		}
		if ($("#passwordStaff").val() != '') {
			if ($("#passwordStaff").val().length < 6) {
				toastr.error("密码不能少于6位!");
				return false;
			}
		}
		if ($("#realName").val() == '') {
			toastr.error("真实名不能为空!");
			return false;
		}
		return true;
	} 
</script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>添加员工</h5>
                    </div>
                    <div class="ibox-content">
                        <form onsubmit="return checkForm();" method="post" class="form-horizontal" action="<%=root%>/system/staff/addOrUpdate.do?flag=${flag}">
                        	<input type="hidden" name="id" value="${staff.id}" />
                            <div class="form-group">
                                <label class="col-sm-2 control-label">登录名：</label>
                                <div class="col-sm-10">
                                    <input type="text" id="loginName" name="loginName" value="${staff.loginName}" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">密码：</label>
                                <div class="col-sm-10">
                                    <input id="passwordStaff" type="password" name="password" value="${staff.password}" class="form-control"> <span class="help-block m-b-none">如果不填写此项，默认密码为123456</span>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">真实姓名：</label>
                                <div class="col-sm-10">
                                    <input type="text" id="realName" name="realName" value="${staff.realName}" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">职位</label>
                                <div class="col-sm-10">
                                    <div class="radio">
                                        <label><input type="radio" value="3" id="optionsRadios1" name="positionId">经理</label>
                                    </div>
                                    <div class="radio">
                                        <label><input type="radio" checked="checked" value="2" id="optionsRadios2" name="positionId">员工</label>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注：</label>
                                <div class="col-sm-10">
                                    <textarea name="remark" class="form-control">${staff.remark}</textarea>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">保存内容</button>
                                    <a href="<%=root %>/system/staff/index.do"><button class="btn btn-white" type="button">取消</button></a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
	</div>
</body>
</html>