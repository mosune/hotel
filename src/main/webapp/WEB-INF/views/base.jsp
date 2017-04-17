<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();   
	String root = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta charset="utf-8" />
<title>宾馆管理系统</title>
<script src="<%=root %>/resources/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=root %>/resources/js/bootstrap.min.js?v=3.3.6"></script>
<script src="<%=root %>/resources/js/plugins/toastr/toastr.min.js"></script>
<link rel="stylesheet" href="<%=root %>/resources/css/plugins/toastr/toastr.min.css">
<script type="text/javascript">
		$(document).ready(function() {
			toastr.options = {  
			        closeButton: true,  // 是否显示关闭按钮（提示框右上角关闭按钮）
			        debug: false,  // 是否为调试
			        progressBar: true,  // 是否显示进度条（设置关闭的超时时间进度条）
			        positionClass: "toast-top-center",  // 消息框在页面显示的位置
			        onclick: null,  // 点击消息框自定义事件
			        showDuration: "300",  // 显示动作时间 
			        hideDuration: "1000",  // 隐藏动作时间
			        timeOut: "2000",  // 自动关闭超时时间 
			        extendedTimeOut: "1000",  
			        showEasing: "swing",  
			        hideEasing: "linear",  
			        showMethod: "fadeIn",  // 显示的方式
			        hideMethod: "fadeOut"  // 隐藏的方式
			    }; 
			/* //常规消息提示，默认背景为浅蓝色  
			toastr.info("你有新消息了!");  
			//成功消息提示，默认背景为浅绿色 
			toastr.success("你有新消息了!");  
			//警告消息提示，默认背景为橘黄色 
			toastr.warning("你有新消息了!");  
			//错误消息提示，默认背景为浅红色 
			toastr.error("你有新消息了!"); */
		});
	</script>
