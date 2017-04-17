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
		if ("${addSuccess}" == 1) { 
			toastr.success("添加货物成功!");
		} else if ("${addSuccess}" == 2) {
			toastr.success("修改货物成功!");
		}  
	});
	function ajax() {
		$.ajax({
			url: "<%=root%>/hotel/goods/list.do",
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
					data.push(o.goodsName);
					data.push("</td>");
					data.push("<td>");
					data.push(o.amount);
					data.push("</td>");
					data.push("<td>");
					data.push(o.remark);
					data.push("</td>");
					data.push("<td>");
					data.push('<a class="btn btn-primary btn-rounded" onclick="showModel(\''+o.id+'\','+o.amount+');" href="javascript:;">出入库</a>');
					data.push('<a class="btn btn-info btn-rounded" href="<%=root%>/hotel/goods/toAddOrUpdate.do?id='+o.id+'">编辑</a>');
					if (o.amount == 0) data.push('<a class="btn btn-danger btn-rounded" onclick="deleteGoods(\''+o.id+'\','+o.amount+');" href="javascript:;">删除</a>');
					data.push("</td>");
					data.push("</tr>");
				});
				$("#dyntable>tbody").append(data.join(""));
			}
		});
	}
	function showModel(id, amount) {
		$("#goodsId").val(id);
		$("#goodsAmount").val(amount);
		$("#addGoods").attr("checked", true);
		$("#subductionGoods").attr("checked", false);
		$("#number").val("");
		$('#myModal').modal('show');
	}
	function changeGoods() {
		if ($("#number").val() == '') {
			toastr.error("数量不能为空!");
			return;
		}
		if ($("input[name='addFlag']:checked").val() == 0) {
			var number = parseInt($("#number").val());
			var oldNumber = $("#goodsAmount").val();
			if (oldNumber - number < 0) {
				toastr.error("出库数量不能超过库存数量!");
				return;
			}
		}
		$.ajax({
			url: "<%=root%>/hotel/goods/changeAmount.do",
			type: "post",
			data: "id="+$("#goodsId").val()+"&amount="+$("#number").val()+"&flag="+$("input[name='addFlag']:checked").val(),
			success:function(result){
				ajax();
				toastr.success("出入库录入成功!");
				$('#myModal').modal('hide');
			}
		});
	}
	function deleteGoods(id) {
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
						url: "<%=root%>/hotel/goods/deleteGoods.do",
						type: "post",
						data: "id="+id,
						success:function(result){
							ajax();
						    swal("成功!", "您删除了这个货物", "success");
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
				<a href="<%=root %>/hotel/goods/toAddOrUpdate.do"><button type="button" class="btn btn-primary btn-sm">添加</button></a>
			</div>
            <div style="margin-top:20px;" class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>房间</h5>
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
                                        <th width="20%">货物名</th>
                                        <th width="20%">数量</th>
                                        <th width="40%">备注</th>
                                        <th width="20%">操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                                <tfoot>
                                	<tr>
                                		<td align="right" colspan="4">
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
	<input type="hidden" name="id" id="goodsId" />
	<input type="hidden" name="amount" id="goodsAmount" />
	
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog model-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">出入库</h4>
			</div>
			<div class="modal-body">
				<div class="row">
		            <div class="col-sm-12">
		                 <form class="form-horizontal">
		                     <div class="form-group">
		                         <label class="col-sm-2 control-label"><span style="color:red">*</span>出入库：</label>
                                <div class="col-sm-10">
                                    <div class="radio">
                                        <label><input type="radio" checked="checked" value="1" id="addGoods" name="addFlag">入库</label>
                                    </div>
                                    <div class="radio">
                                        <label><input type="radio" value="0" id="subductionGoods" name="addFlag">出库</label>
                                    </div>
                                </div>
		                     </div>
		                     <div class="hr-line-dashed"></div>
		                     <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>数量：</label>
                                <div class="col-sm-10">
                                    <input type="text" id="number" name="number" class="form-control">
                                </div>
                            </div>
		                 </form>
		            </div>
		        </div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" onclick="changeGoods();" class="btn btn-primary">确认</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</body>
</html>