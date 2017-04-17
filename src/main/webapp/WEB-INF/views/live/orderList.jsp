<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<script src="<%=root %>/resources/js/sweetalert.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=root %>/resources/css/sweetalert.css" rel="stylesheet">
    <link href="<%=root %>/resources/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=root %>/resources/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <!-- Morris -->
    <link href="<%=root %>/resources/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
    <link href="<%=root %>/resources/css/animate.css" rel="stylesheet">
    <link href="<%=root %>/resources/css/style.css?v=4.1.0" rel="stylesheet">
    <script src="<%=root %>/resources/js/plugins/layer/laydate/laydate.js"></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
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
                                        <th width="20%">房间编号</th>
                                        <th width="20%">旅客姓名</th>
                                        <th width="20%">预定入住时间</th>
                                        <th width="20%">预计退房时间</th>
                                        <th width="20%">操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                                <tfoot>
                                	<tr>
                                		<td align="right" colspan="10">
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
	
	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog model-lg" style="width:700px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">结账</h4>
			</div>
			<div class="modal-body">
				<div class="row">
		            <div class="col-sm-12">
		                 <form class="form-horizontal">
		                     <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>入住时间：</label>
                                <div class="col-sm-10">
                                    <input placeholder="开始日期" class="form-control layer-date" name="liveTime" id="start">
                                    <input placeholder="结束日期" class="form-control layer-date" name="endTime" id="end">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>押金（元）：</label>
                                <div class="col-sm-10">
                                    <input id="deposit" name="depositStr" onkeyup="sumPrice();" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">总价（元）：</label>
                                <div class="col-sm-10">
                                    <input id="charge" name="chargeStr" type="text" class="form-control" readonly="readonly"/>
                                </div>
                            </div>
		                 </form>
		            </div>
		        </div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" onclick="settleRoomCom();" class="btn btn-primary">确认</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<input type="hidden" id="roomId" />
<input type="hidden" id="price" />
<input type="hidden" id="liveId" />
</body>
 <script type="text/javascript">
	$(document).ready(function() {
		ajax();
	});
	function ajax() {
		$.ajax({
			url: "<%=root%>/living/live/settleList.do?flag=2",
			type: "post",
			data: {
				searchData:$("#search").val(),
				page:$("#page").val(),
				pageCount:$("#pageCount").val(),
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
					data.push(o.roomNum);
					data.push("</td>");
					data.push("<td>");
					data.push(o.passengerName);
					data.push("</td>");
					data.push("<td>");
					data.push(FormatDate(o.liveTime));
					data.push("</td>");
					data.push("<td>");
					data.push(FormatDate(o.endTime));
					data.push("</td>");
					data.push("<td>");
					data.push('<a class="btn btn-info btn-rounded" onclick="livingRoom(\''+o.id+'\', \''+o.price+'\')">入住</a>');
					data.push('<a class="btn btn-danger btn-rounded" onclick="cancelOrder(\''+o.id+'\', \''+o.roomNum+'\');" href="javascript:;">取消</a>');
					data.push("</td>");
					data.push("</tr>");
				});
				$("#dyntable>tbody").append(data.join(""));
			}
		});
	}
	function FormatDate (strTime) {
	    var date = new Date(strTime);
	    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	}
	function livingRoom(id, price) {
		$.ajax({
			url: "<%=root%>/living/live/getLiving.do",
			type: "post",
			data: {id:id},
			dataType: 'json',
			success:function(result){
				if (result.live) {
					$("#roomId").val(result.live.id);
					$("#price").val(price);
					$("#liveId").val(id);
				}
			}
		});
		$("#start").val('');
		$("#end").val('');
		$("#deposit").val('');
		$("#charge").val('');
		$('#myModal').modal('show');
	}
	//日期范围限制
    var start = {
        elem: '#start',
        format: 'YYYY/MM/DD 12:00:00',
        min: laydate.now(), //设定最小日期为当前日期
        max: '2099-06-16 23:59:59', //最大日期
        istime: false,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#end',
        format: 'YYYY/MM/DD 12:00:00',
        min: laydate.now(),
        max: '2099-06-16 23:59:59',
        istime: false,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
	function settleRoomCom() {
		if ($("#start").val() == '') {
			toastr.error("入住时间不能为空!");
			return;
		}
		if (new Date().toDateString() === new Date($("#start").val()).toDateString() == false) {
			toastr.error("开始入住时间必须是今天!");
			return false;
		}
		if ($("#end").val() == '') {
			toastr.error("退房时间不能为空!");
			return;
		}
		swal({
			  title: "入住",
			  text: "应收金额"+$("#charge").val()+"元",
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
						url: "<%=root%>/living/live/livingOrder.do",
						type: "post",
						data: {
							id:$("#liveId").val(),
							liveTime:$("#start").val(),
							endTime:$("#end").val(),
							deposit:$("#deposit").val(),
							charge:$("#charge").val()
						},
						success:function(result){
							ajax();
						    swal("成功!", "入住成功", "success");
						    $('#myModal').modal('hide');
						}
				  })
			  } else {
			    swal("取消", "谢谢您的考虑:)", "error");
			  }
			});
	}
	function sumPrice() {
		var end_date = new Date($("#end").val());//将字符串转化为时间  
		//开始时间  
		var sta_date = new Date($("#start").val());  
		var num = (end_date-sta_date)/(1000*3600*24);//求出两个时间的时间差，这个是天数  
		var days = parseInt(Math.ceil(num));//转化为整天（小于零的话剧不用转了
		var price = $("#price").val() * days + parseInt($("#deposit").val());
		$("#charge").val(price);
	}
	function cancelOrder(roomId, roomNum) {
		swal({
			  title: "取消预订",
			  text: "确定取消"+roomNum+"的预定么",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "确认",
			  cancelButtonText: "取消",
			  closeOnConfirm: false,
			  closeOnCancel: false,
			  showLoaderOnConfirm: true
			},
			function(isConfirm){
			  if (isConfirm) {
				  $.ajax({
						url: "<%=root%>/living/live/cancelOrder.do",
						type: "post",
						data: {
							id:roomId
						},
						success:function(result){
							ajax();
						    swal("成功!", "取消预订成功", "success");
						    $('#myModal').modal('hide');
						}
				  })
			  } else {
			    swal("取消", "谢谢您的考虑:)", "error");
			  }
			});
	}
</script>
</html>