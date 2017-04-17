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
	function checkForm() {
		if ($("#roomNum").val() == '') {
			toastr.error("房间编号不能为空!");
			return false;
		}
		if ($("#price").val() == '') {
			toastr.error("价格不能为空!");
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
                        <h5>添加货物</h5>
                    </div>
                    <div class="ibox-content">
                        <form onsubmit="return checkForm();" method="post" class="form-horizontal" action="<%=root%>/hotel/goods/addOrUpdate.do?flag=${flag}">
                        	<input type="hidden" name="id" value="${goods.id}" />
                            <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>货物名称：</label>
                                <div class="col-sm-10">
                                    <input type="text" id="goodsName" name="goodsName" value="${goods.goodsName}" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注：</label>
                                <div class="col-sm-10">
                                    <textarea name="remark" class="form-control">${goods.remark}</textarea>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">保存内容</button>
                                    <a href="<%=root %>/hotel/goods/index.do"><button class="btn btn-white" type="button">取消</button></a>
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