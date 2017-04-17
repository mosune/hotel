<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<script src="<%=root %>/resources/js/sweetalert.min.js"></script>
	<script src="<%=root %>/resources/js/echarts.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=root %>/resources/css/sweetalert.css" rel="stylesheet">
    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="<%=root %>/resources/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=root %>/resources/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <!-- Morris -->
    <link href="<%=root %>/resources/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
    <link href="<%=root %>/resources/css/animate.css" rel="stylesheet">
    <link href="<%=root %>/resources/css/style.css?v=4.1.0" rel="stylesheet">
<style type="text/css">
</style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
            <div style="margin-top:20px;" class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>账单流水</h5>
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
                        </div>
                        <div class="table-responsive">
                            <table id="dyntable" class="table table-striped">
                                <thead>
                                    <tr>
                                        <th width="30%">金额(元)</th>
                                        <th width="30%">类型</th>
                                        <th width="40%">时间</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                                <tfoot>
                                	<tr>
                                		<td align="right" colspan="3">
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
		<div class="row">
			<div id="charts" style="height:600px;"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	ajax();
});
function ajax() {
	$.ajax({
		url: "<%=root%>/information/billflow/list.do",
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
				if(o.charge < 0) data.push(o.charge * (-1));
				else data.push(o.charge);
				data.push("</td>");
				data.push("<td>");
				if (o.type == 1) data.push("收款");
				if (o.type == 2) data.push("退款");
				data.push("</td>");
				data.push("<td>");
				data.push(format(o.createTime, 'yyyy-MM-dd HH:mm:ss'));
				data.push("</td>");
				data.push("</tr>");
			});
			$("#dyntable>tbody").append(data.join(""));
		}
	});
}
var format = function(time, format){
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : '') + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    })
}
function showModel() {
	$("#myModal").modal("show");
}
var myChart = echarts.init(document.getElementById("charts"));
option = {
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b}: {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        x: 'left',
	        data:['收款','退款']
	    },
	    series: [
	        {
	            name:'收入支出',
	            type:'pie',
	            radius: ['50%', '70%'],
	            avoidLabelOverlap: false,
	            label: {
	                normal: {
	                    show: false,
	                    position: 'center'
	                },
	                emphasis: {
	                    show: true,
	                    textStyle: {
	                        fontSize: '30',
	                        fontWeight: 'bold'
	                    }
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:[
	                {value:"${gathering}", name:'收款'},
	                {value:"${refund}", name:'退款'}
	            ]
	        }
	    ]
	};

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</html>