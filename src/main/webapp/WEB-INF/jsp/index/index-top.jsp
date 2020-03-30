<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			String rootPath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort();
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>懒人洗衣后台业务系统</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="Mosaddek" name="author" />
	<link rel="shortcut icon" href="images/icon.png" type="image/x-icon" />
<!-- include css -->
<%@ include file="index-css.jsp"%>
</head>
<body class="fixed-top">
	<input type="hidden" value="<%=basePath%>" id="basePath">
	<input type="hidden" value="<%=rootPath%>" id="rootPath">
	<!-- BEGIN HEADER -->
	<div id="header" class="navbar navbar-inverse navbar-fixed-top">

		<div class="navbar-inner">
			<div class="container-fluid">
				<div class="sidebar-toggle-box hidden-phone">
					<div class="icon-reorder tooltips" data-placement="right" data-original-title=""></div>
				</div>
				<a class="brand"> <img src="images/index/logo_xiyi.png" alt="Metro Lab" /> </a> <a
					class="btn btn-navbar collapsed" id="main_menu_trigger" data-toggle="collapse" data-target=".nav-collapse"> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="arrow"></span>
				</a>

				<div class="top-nav ">
					<ul class="nav pull-right top-menu">
						<li class="dropdown mtop5"><a id="" class="dropdown-toggle element" data-placement="bottom"
							data-toggle="tooltip" data-original-title=""> <span id="adminNameTXT" class="tenantName"></span> </a>
						</li>
						
						<li class="dropdown mtop5"><a href="javascript:" id="earningsratioEdit" class="dropdown-toggle element" data-placement="bottom"
							data-toggle="tooltip" data-original-title=""><span>分销设置 </span></a>
						</li>

						<li class="dropdown mtop5"><a href="javascript:" id="changePassword" class="dropdown-toggle element" data-placement="bottom"
							data-toggle="tooltip" data-original-title=""><span>修改密码</span></a>
						</li>

						<li class="dropdown mtop5"><a id="" class="dropdown-toggle element" data-placement="bottom"
							data-toggle="tooltip" href="<%=basePath%>admin/loginOut" data-original-title=""> <span class="wz">退 出</span>
						</a>
						</li>
					</ul>
				</div>
			</div>
		</div>

	</div>