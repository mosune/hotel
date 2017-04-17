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
<link rel="stylesheet" type="text/css" href="<%=root %>/resources/css/sweetalert.css" rel="stylesheet">
<!-- 自定义js -->
<!-- layerDate plugin javascript -->
<script src="<%=root %>/resources/js/plugins/layer/laydate/laydate.js"></script>
<script src="<%=root %>/resources/js/content.js?v=1.0.0"></script>
<!-- iCheck -->
<script src="<%=root %>/resources/js/plugins/iCheck/icheck.min.js"></script>
<script src="<%=root %>/resources/js/sweetalert.min.js"></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>入住房间</h5>
                    </div>
                    <div class="ibox-content">
                        <form id="myform" onsubmit="return checkForm();" method="post" class="form-horizontal" action="<%=root%>/living/live/living.do">
                        	<input type="hidden" name="roomId" value="${room.id}" />
                        	<input type="hidden" id="vipflag" name="vipflag" value="0" />
                            <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>房间编号：</label>
                                <div class="col-sm-10">
                                    <input type="text" id="roomNum" name="roomNum" value="${room.roomNum}" class="form-control" disabled="disabled" /></div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>入住时间：</label>
                                <div class="col-sm-10">
                                    <input placeholder="开始日期" class="form-control layer-date" name="liveTime" id="start">
                                    <input placeholder="结束日期" class="form-control layer-date" name="endTime" id="end">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">单价（元/日）：</label>
                                <div class="col-sm-10">
                                    <input id="priceEach" type="text" value="${room.price}" class="form-control" readonly="readonly">
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
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>是否预订：</label>
                                <div class="col-sm-10">
                                    <div class="radio">
                                        <label><input type="radio" value="1" id="scheduleFlagTrue" name="destineTime">是</label>
                                    </div>
                                    <div class="radio">
                                        <label><input type="radio" value="0" checked="checked" id="scheduleFlagFalse" name="destineTime">否</label>
                                    </div>
                                    <span class="help-block m-b-none">如果不选择预定，入住时间必须是今天</span>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>旅客姓名：</label>
                                <div class="col-sm-10">
                                    <input id="custName" type="text" name="custName" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"><span style="color:red">*</span>身份证号：</label>
                                <div class="col-sm-10">
                                    <input id="idNum" type="text" name="custIdNum" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">入住</button>
                                    <a href="<%=root%>/living/live/toChooseRoom.do?id=${typeId}"><button class="btn btn-white" type="button">取消</button></a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
	</div>
</body>
<script type="text/javascript">
	function checkForm() {
		if ($("#start").val() == '') {
			toastr.error("开始时间不能为空!");
			return false;
		}
		if ($("#end").val() == '') {
			toastr.error("结束时间不能为空!");
			return false;
		}
		if ($("#deposit").val() == '') {
			toastr.error("押金不能为空!");
			return false;
		}
		if ($("#custName").val() == '') {
			toastr.error("旅客姓名不能为空!");
			return false;
		}
		if ($("#idNum").val() == '') {
			toastr.error("旅客身份证号不能为空!");
			return false;
		}
		var isIDCard=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
		if (isIDCard.test($("#idNum").val()) == false) {
			toastr.error("请输入正确的身份证号!");
			return false;
		}
		var val=$('input:radio[name="destineTime"]:checked').val();
		if (val == 0) {
			if (new Date().toDateString() === new Date($("#start").val()).toDateString() == false) {
				toastr.error("开始入住时间必须是今天!");
				return false;
			}
		} 
		return true;
	} 
	function sumPrice() {
		var price;
		if ($("#start").val() != '' && $("#end").val() != '') {
			var end_date = new Date($("#end").val());//将字符串转化为时间  
			//开始时间  
			var sta_date = new Date($("#start").val());  
			var num = (end_date-sta_date)/(1000*3600*24);//求出两个时间的时间差，这个是天数  
			var days = parseInt(Math.ceil(num));//转化为整天（小于零的话剧不用转了
			if($("#vipflag").val() == 0) price = days * $("#priceEach").val() + parseInt($("#deposit").val());
			else price = days * $("#priceEach").val() * 0.8 + parseInt($("#deposit").val());
			$("#charge").val(price);
		}
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
</script>
</html>