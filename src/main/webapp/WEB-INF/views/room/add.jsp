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
		if ("${room}" != '') {
			if ("${room.tvFlag}" == 1) {
				$("#tvFlagTrue").attr("checked", true);
				$("#tvFlagFalse").attr("checked", false);
			} else {
				$("#tvFlagTrue").attr("checked", false);
				$("#tvFlagFalse").attr("checked", true);
			}
			if ("${room.airconFlag}" == 1) {
				$("#airconFlagTrue").attr("checked", true);
				$("#airconFlagFalse").attr("checked", false);
			} else {
				$("#airconFlagTrue").attr("checked", false);
				$("#airconFlagFalse").attr("checked", true);
			}
			if ("${room.showerFlag}" == 1) {
				$("#showerFlagTrue").attr("checked", true);
				$("#showerFlagFalse").attr("checked", false);
			} else {
				$("#showerFlagTrue").attr("checked", false);
				$("#showerFlagFalse").attr("checked", true);
			}
			if ("${room.windowFlag}" == 1) {
				$("#windowFlagTrue").attr("checked", true);
				$("#windowFlagFalse").attr("checked", false);
			} else {
				$("#windowFlagTrue").attr("checked", false);
				$("#windowFlagFalse").attr("checked", true);
			}
		}
	});
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
                        <h5>添加房间</h5>
                    </div>
                    <div class="ibox-content">
                        <form onsubmit="return checkForm();" method="post" class="form-horizontal" action="<%=root%>/hotel/room/addOrUpdate.do?flag=${flag}">
                        	<input type="hidden" name="id" value="${room.id}" />
                            <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>房间编号：</label>
                                <div class="col-sm-10">
                                    <input type="text" id="roomNum" name="roomNum" value="${room.roomNum}" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>房间类型：</label>
                                <div class="col-sm-10">
                                    <select class="form-control m-b" name="typeId">
                                    	<c:if test="${room == null }">
	                                        <c:forEach items="${roomTypes}" var="roomtype">
	                                        	<option value="${roomtype.id }">${roomtype.name }</option>
	                                        </c:forEach>
                                        </c:if>
                                    	<c:if test="${room != null }">
	                                        <c:forEach items="${roomTypes}" var="roomtype">
	                                        	<c:if test="${room.typeId == roomtype.id}">
	                                        		<option selected="selected" value="${roomtype.id }">${roomtype.name }</option>
	                                        	</c:if>
	                                        	<c:if test="${room.typeId != roomtype.id}">
	                                        		<option value="${roomtype.id }">${roomtype.name }</option>
	                                        	</c:if>
	                                        </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>价格（元/日）：</label>
                                <div class="col-sm-10">
                                    <input id="price" type="text" name="priceStr" value="${room.price}" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>电视：</label>
                                <div class="col-sm-10">
                                    <div class="radio">
                                        <label><input type="radio" checked="checked" value="1" id="tvFlagTrue" name="tvFlag">有</label>
                                    </div>
                                    <div class="radio">
                                        <label><input type="radio" value="0" id="tvFlagFalse" name="tvFlag">无</label>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>空调：</label>
                                <div class="col-sm-10">
                                    <div class="radio">
                                        <label><input type="radio" checked="checked" value="1" id="airconFlagTrue" name="airconFlag">有</label>
                                    </div>
                                    <div class="radio">
                                        <label><input type="radio" value="0" id="airconFlagFalse" name="airconFlag">无</label>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>浴室：</label>
                                <div class="col-sm-10">
                                    <div class="radio">
                                        <label><input type="radio" checked="checked" value="1" id="showerFlagTrue" name="showerFlag">有</label>
                                    </div>
                                    <div class="radio">
                                        <label><input type="radio" value="0" id="showerFlagFalse" name="showerFlag">无</label>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>窗户：</label>
                                <div class="col-sm-10">
                                    <div class="radio">
                                        <label><input type="radio" checked="checked" value="1" id="windowFlagTrue" name="windowFlag">有</label>
                                    </div>
                                    <div class="radio">
                                        <label><input type="radio" value="0" id="windowFlagFalse" name="windowFlag">无</label>
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注：</label>
                                <div class="col-sm-10">
                                    <textarea name="remark" class="form-control">${room.remark}</textarea>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">保存内容</button>
                                    <a href="<%=root %>/hotel/room/index.do"><button class="btn btn-white" type="button">取消</button></a>
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