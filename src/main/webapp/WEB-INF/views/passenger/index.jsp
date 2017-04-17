<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<script src="<%=root %>/resources/js/sweetalert.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=root %>/resources/css/sweetalert.css" rel="stylesheet">
    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="<%=root %>/resources/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=root %>/resources/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <!-- Morris -->
    <link href="<%=root %>/resources/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
    <link href="<%=root %>/resources/css/animate.css" rel="stylesheet">
    <link href="<%=root %>/resources/css/style.css?v=4.1.0" rel="stylesheet">
<script type="text/javascript">
	$(document).ready(function() {
		ajax();
		/* if ("${addSuccess}" == 1) { 
			toastr.success("添加旅客成功!");
		} else if ("${addSuccess}" == 2) {
			toastr.success("修改用户成功!");
		}  */
	});
	function ajax() {
		$.ajax({
			url: "<%=root%>/system/staff/list.do",
			type: "post",
			data: {
				searchData:$("#search").val(),
				page:$("#page").val(),
				pageCount:$("#pageCount").val()
				},
			dataType: "json",
			success:function(result) {
				$("#dyntable>tbody>*").remove();
				var page = $("#page").val();
				$("#dyntable_previous,#dyntable_next").unbind();
				$("#dyntable_next").bind("click", function() {
					if (page < result.paging.sumPage) {
						$("#page").val(parseInt(page) + 1);
						ajax();
					}
				});
				$("#dyntable_previous").bind("click", function() {
					if (page > 1) {
						$("#page").val(parseInt(page) - 1);
						ajax();
					}
				});
				var str = "总共" + result.paging.count + "条数据，共" + result.paging.sumPage + "页，当前第" + result.paging.page + "页";
				$("#tip").html(str);
				var data = new Array();
				$(result.paging.list).each(function(i, o) {
					data.push("<tr>");
					data.push("<td>");
					data.push(o.realName);
					data.push("</td>");
					data.push("<td>");
					data.push(o.loginName);
					data.push("</td>");
					data.push("<td>");
					if (o.positionId == 1) data.push("管理员");
					else if (o.positionId == 2) data.push("员工");
					else data.push("经理");
					data.push("</td>");
					data.push("<td>");
					if (o.flag == 1) data.push("正常");
					else data.push("已冻结");
					data.push("</td>");
					data.push("<td>");
					data.push(o.remark);
					data.push("</td>");
					data.push("<td>");
					if (o.positionId == 1) data.push("无法操作");
					else {
						data.push('<a class="btn btn-info btn-rounded" href="<%=root%>/system/staff/toUpdate.do?id='+o.id+'">编辑</a>');
						if (o.flag == 1) data.push('<a class="btn btn-warning btn-rounded" onclick="changeState(\''+o.id+'\', '+o.flag+');" href="javascript:;">冻结</a>');
						else data.push('<a class="btn btn-warning btn-rounded" onclick="changeState(\''+o.id+'\', '+o.flag+');" href="javascript:;">解冻</a>');
						data.push('<a class="btn btn-danger btn-rounded" onclick="deleteStaff(\''+o.id+'\');" href="javascript:;">删除</a>');
					}
					data.push("</td>");
					data.push("</tr>");
				});
				$("#dyntable>tbody").append(data.join(""));
			}
		});
	}
	function changeState(id, state) {
		var str = '';
		if (state == 1) str = '冻结';
		else str = '解冻';
		swal({
			  title: "确认"+str+"么？",
			  text: "要慎重啊！",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#AEDEF4",
			  confirmButtonText: "确认",
			  cancelButtonText: "取消",
			  closeOnConfirm: false,
			  closeOnCancel: false,
			  showLoaderOnConfirm: true
			},
			function(isConfirm){
			  if (isConfirm) {
				  $.ajax({
						url: "<%=root%>/system/staff/changeFlag.do",
						type: "post",
						data: "id="+id,
						success:function(result){
							ajax();
						    swal("成功!", "您"+str+"了这个账户", "success");
						}
				  })
			  } else {
			    swal("取消", "谢谢您的考虑:)", "error");
			  }
			});
	}
	function deleteStaff(id) {
		swal({
			  title: "确认删除么？",
			  text: "要慎重啊！这个操作可是不能反悔的",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "是的，我要删除",
			  cancelButtonText: "取消",
			  closeOnConfirm: false,
			  closeOnCancel: false,
			  showLoaderOnConfirm: true
			},
			function(isConfirm){
			  if (isConfirm) {
				  $.ajax({
						url: "<%=root%>/system/staff/deleteStaff.do",
						type: "post",
						data: "id="+id,
						success:function(result){
							ajax();
						    swal("成功!", "您删除了这个账户", "success");
						}
				  })
			  } else {
			    swal("取消", "谢谢您的考虑:)", "error");
			  }
			});
	} 
</script>
<style type="text/css">
</style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<a href="<%=root %>/system/staff/toAdd.do"><button type="button" class="btn btn-primary btn-sm">添加</button></a>
			</div>
            <div style="margin-top:20px;" class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>用户</h5>
                        <div id="tip" class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                    	<input type="hidden" id="page" value="1" name="page" />
                        <div class="row">
                        	<div class="col-sm-5 m-b-xs">
                                <select onchange="ajax();" id="pageCount" class="input-sm form-control input-s-sm inline">
                                    <option value="5">5</option>
                                    <option selected="selected" value="10">10</option>
                                    <option value="30">30</option>
                                </select>
                            </div>
                            <div class="col-sm-4 m-b-xs">
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="text" id="search" placeholder="请输入关键词" class="input-sm form-control" onkeyup="ajax();"> <span class="input-group-btn">
                                        <button type="button" id="search" onclick="ajax();" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
                        <div class="table-responsive">
                            <table id="dyntable" class="table table-striped">
                                <thead>
                                    <tr>
                                        <th width="15%">真实姓名</th>
                                        <th width="15%">登录名</th>
                                        <th width="10%">职位</th>
                                        <th width="10%">状态</th>
                                        <th width="30%">备注</th>
                                        <th width="20%">操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                                <tfoot>
                                	<tr>
                                		<td align="right" colspan="6">
                                			<button type="button" id="dyntable_previous" class="btn btn-white"><i class="fa fa-chevron-left"></i></button>
                                			<button type="button" id="dyntable_next" class="btn btn-white"><i class="fa fa-chevron-right"></i></button>
                                		</td>
                                	</tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	</div>
</body>
</html>