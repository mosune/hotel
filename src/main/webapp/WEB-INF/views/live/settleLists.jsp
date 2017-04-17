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
                                        <th width="10%">房间编号</th>
                                        <th width="10%">旅客姓名</th>
                                        <th width="15%">入住时间</th>
                                        <th width="15%">预计退房时间</th>
                                        <th width="10%">单价（元/日）</th>
                                        <th width="10%">押金（元）</th>
                                        <th width="10%">已收款（元）</th>
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
	<div class="modal-dialog model-lg">
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
		                        <label class="col-sm-2 control-label"><span style="color:red">*</span>退房日：</label>
		                        <div class="col-sm-10">
                                    <input type="text" class="form-control laydate-icon canRead" onchange="checkPrice();" id="realTime" name="realTime" class="form-control">
                                </div>
		                     </div>
		                     <!-- <div class="hr-line-dashed"></div>
		                     <div class="form-group">
                                <label class="col-sm-2 control-label">结余：</label>
                                <div class="col-sm-10">
                                    <input type="text" id="number" name="number" class="form-control" readonly="readonly" />
                                </div>
                            </div> -->
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

<div class="modal fade" id="countinueRoom" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog model-lg">
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
		                        <label class="col-sm-2 control-label"><span style="color:red">*</span>退房日：</label>
		                        <div class="col-sm-10">
                                    <input type="text" class="form-control laydate-icon canRead" id="conEndTime" name="conEndTime" class="form-control">
                                </div>
		                     </div>
		                     <div class="hr-line-dashed"></div>
		                     <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>增加押金：</label>
                                <div class="col-sm-10">
                                    <input type="text" id="conDeposit" name="conDeposit" class="form-control" />
                                </div>
                            </div>
		                 </form>
		            </div>
		        </div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" onclick="continueRoomCom();" class="btn btn-primary">确认</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<input type="hidden" id="roomId" />
<input type="hidden" id="liveTime" />
<input type="hidden" id="endTime" />
<input type="hidden" id="charge" />
<input type="hidden" id="price" />
<input type="hidden" id="liveId" />
<input type="hidden" id="deposit" />
</body>
 <script type="text/javascript">
	$(document).ready(function() {
		ajax();
	});
	function ajax() {
		$.ajax({
			url: "<%=root%>/living/live/settleList.do?flag=1",
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
					data.push(o.price);
					data.push("</td>");
					data.push("<td>");
					data.push(o.deposit);
					data.push("</td>");
					data.push("<td>");
					data.push(o.charge);
					data.push("</td>");
					data.push("<td>");
					data.push('<a class="btn btn-info btn-rounded" onclick="settleRoom(\''+o.id+'\', \''+o.price+'\')">结账</a>');
					data.push('<a class="btn btn-danger btn-rounded" onclick="continuedRoom(\''+o.id+'\', '+o.endTime+', \''+o.price+'\', \''+o.deposit+'\');" href="javascript:;">续房</a>');
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
	function settleRoom(id, price) {
		$.ajax({
			url: "<%=root%>/living/live/getLiving.do",
			type: "post",
			data: {id:id},
			dataType: 'json',
			success:function(result){
				if (result.live) {
					$("#roomId").val(result.live.id);
					$("#liveTime").val(result.live.liveTime);
					$("#endTime").val(result.live.endTime);
					$("#price").val(price);
					$("#charge").val(result.live.charge);
					$("#liveId").val(id);
				}
			}
		});
		$("#realTime").val('');
		$('#myModal').modal('show');
	}
	laydate({
		elem : '#realTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		format : 'YYYY-MM-DD 12:00:00', // 分隔符可以任意定义，该例子表示只显示年月
		festival : true, //显示节日
		min: $("#liveTime").val(),
		max: laydate.now(), 
		istoday : true, //是否显示今天
		start: laydate.now(0, "YYYY/MM/DD hh:mm:ss"),
	});
	laydate({
		elem : '#conEndTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		format : 'YYYY-MM-DD 12:00:00', // 分隔符可以任意定义，该例子表示只显示年月
		festival : true, //显示节日
		min: laydate.now(),
		istoday : true, //是否显示今天
		start: laydate.now(0, "YYYY/MM/DD hh:mm:ss"),
	});
	function settleRoomCom() {
		if ($("#realTime").val() == '') {
			toastr.error("退房时间不能为空!");
			return;
		}
		var liveTime = $("#liveTime").val();
		var realTime = new Date($("#realTime").val()).getTime();
		if (liveTime > realTime) {
			toastr.error("退房时间不能早于入住时间!");
			return;
		}
		var price = checkPrice();
		var str;
		if (price < 0) str = "退款"+(price*-1)+"元";
		if (price > 0) str = "收款款"+(price)+"元";
		if (price == 0) str = "";
		swal({
			  title: "收出账单",
			  text: str,
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#AEDEF4",
			  confirmButtonText: "结账",
			  cancelButtonText: "取消",
			  closeOnConfirm: false,
			  closeOnCancel: false,
			  showLoaderOnConfirm: true
			},
			function(isConfirm){
			  if (isConfirm) {
				  $.ajax({
						url: "<%=root%>/living/live/settleRoom.do",
						type: "post",
						data: "id="+$("#liveId").val()+"&realTime="+$("#realTime").val()+"&price="+price,
						success:function(result){
							ajax();
						    swal("成功!", "已结清", "success");
						    $('#myModal').modal('hide');
						}
				  })
			  } else {
			    swal("取消", "谢谢您的考虑:)", "error");
			  }
			});
	}
	function checkPrice() {
		var end_date = new Date($("#realTime").val());//将字符串转化为时间  
		//开始时间  
		var sta_date = $("#liveTime").val();  
		var num = (end_date-sta_date)/(1000*3600*24);//求出两个时间的时间差，这个是天数  
		var days = parseInt(Math.ceil(num));//转化为整天（小于零的话剧不用转了
		var price = $("#price").val() * days - $("#charge").val();
		return price;
	}
	function continuedRoom(id, endTime, price, deposit) {
		$.ajax({
			url: "<%=root%>/living/live/getLiving.do",
			type: "post",
			data: {id:id},
			dataType: 'json',
			success:function(result){
				if (result.live) {
					$("#roomId").val(result.live.id);
					$("#liveTime").val(result.live.liveTime);
					$("#endTime").val(result.live.endTime);
					$("#price").val(price);
					$("#charge").val(result.live.charge);
					$("#deposit").val(deposit);
					$("#liveId").val(id);
				}
			}
		});
		$("#conDeposit").val('');
		$("#conEndTime").val('');
		$("#countinueRoom").modal("show");
	}
	function continueRoomCom() {
		if ($("#conEndTime").val() == '') {
			toastr.error("退房时间不能为空!");
			return;
		}
		var liveTime = $("#endTime").val();
		var realTime = new Date($("#conEndTime").val()).getTime();
		if (liveTime > realTime) {
			toastr.error("退房时间不能早于原来退房时间!");
			return;
		}
		if ($("#conDeposit").val() == '') {
			toastr.error("押金不能为空!");
			return;
		}
		var end_date = new Date($("#conEndTime").val());//将字符串转化为时间  
		//开始时间  
		var sta_date = $("#liveTime").val();  
		var num = (end_date-sta_date)/(1000*3600*24);//求出两个时间的时间差，这个是天数  
		var days = parseInt(Math.ceil(num));//转化为整天（小于零的话剧不用转了
		var price = parseInt($("#price").val()) * days + parseInt($("#conDeposit").val()) + parseInt($("#deposit").val()) - parseInt($("#charge").val());
		swal({
			  title: "续房",
			  text: "应再收取"+price+"元",
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
						url: "<%=root%>/living/live/continueRoom.do",
						type: "post",
						data: {
							id: $("#liveId").val(),
							endTimeStr: $("#conEndTime").val(),
							price: price,
							deposit: $("#conDeposit").val()
						},
						success:function(result){
							ajax();
						    swal("成功!", "已续房", "success");
						    $('#countinueRoom').modal('hide');
						}
				  })
			  } else {
			    swal("取消", "谢谢您的考虑:)", "error");
			  }
			});
	}
</script>
</html>