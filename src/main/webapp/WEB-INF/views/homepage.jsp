<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=root %>/resources/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="<%=root %>/resources/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="<%=root %>/resources/css/animate.css" rel="stylesheet">
<link href="<%=root %>/resources/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body>
	<div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-4">
                <div class="ibox">
                    <div class="ibox-content">
                        <h3>待结账</h3>
                        <p class="small"><i class="fa fa-hand-o-up"></i> 在列表之间拖动房间</p>
                        <ul class="sortable-list connectList agile-list">
                        	<c:forEach items="${liveSettleDtos }" var="liveSettleDto">
                        		<li class="warning-element">
                        			${liveSettleDto.roomNum }
                        			<div class="agile-detail">
	                                    <i class="fa fa-clock-o"></i>预计退房时间：<fmt:formatDate value="${liveSettleDto.endTime}" pattern="yyyy/MM/dd  HH:mm:ss" var="d" />${d}
	                                </div>
                        		</li>
                        	</c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="ibox">
                    <div class="ibox-content">
                        <h3>空闲</h3>
                        <p class="small"><i class="fa fa-hand-o-up"></i> 在列表之间拖动房间</p>
                        <ul class="sortable-list connectList agile-list">
                        	<c:forEach items="${rooms }" var="room">
                        		<li class="success-element">
                        			${room.roomNum }
                        			<div class="agile-detail">
	                                    <i class="fa fa-clock-o"></i>空闲
	                                </div>
                        		</li>
                        	</c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="ibox">
                    <div class="ibox-content">
                        <h3>已预订</h3>
                        <p class="small"><i class="fa fa-hand-o-up"></i> 在列表之间拖动房间</p>
                        <ul class="sortable-list connectList agile-list">
                        	<c:forEach items="${liveOrderDtos }" var="liveOrderDto">
                        		<li class="success-element">
                        			${liveOrderDto.roomNum }
                        			<div class="agile-detail">
	                                    <i class="fa fa-clock-o"></i>预计入住时间：<fmt:formatDate value="${liveOrderDto.liveTime}" pattern="yyyy/MM/dd  HH:mm:ss" var="d" />${d}
	                                </div>
                        		</li>
                        	</c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

	<!-- 全局js -->
    <script src="<%=root %>/resources/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=root %>/resources/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="<%=root %>/resources/js/jquery-ui-1.10.4.min.js"></script>
    <!-- 自定义js -->
    <script src="<%=root %>/resources/js/content.js?v=1.0.0"></script>
    <script>
        $(document).ready(function () {
            $(".sortable-list").sortable({
                connectWith: ".connectList"
            }).disableSelection();

        });
    </script>
    </div>
</body>
</html>