<!doctype html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>
<html lang="en" class="no-js">
<!--后台登录页面-->
<head>
<meta charset="UTF-8" />
<title>懒人洗衣后台业务系统登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/login/style.css" />
<link rel="shortcut icon" href="images/icon.png" type="images/image/x-icon" />
<!-- remember, jQuery is completely optional -->
<!-- <script type='text/javascript' src='js/jquery-1.11.1.min.js'></script> -->
<script type='text/javascript' src='js/login/jquery.particleground.min.js'></script>
<script type='text/javascript' src='js/login/login3.js'></script>
<script type='text/javascript' src='js/login/login5.js'></script>
<!--根路径-->
<style type="text/css">
.login {
	color: red;
}
</style>
</head>

<body>

	<div id="particles">
		<div id="intro">
			<!-- Mixins-->
			<div class="container">
				<div class="card"></div>
				<div class="card">
					<h2 class="title">懒人洗衣后台业务系统</h2>
					<form>
						<input type="hidden" name="type" id="type" value="" />
						<div class="input-container">
							<input type="text" name="username" id="userName" required="required" /> <label for="Username">用户名</label>
							<div class="bar"></div>
						</div>
						<div class="input-container">
							<input type="password" name="" id="passWord" required="required" /> <label for="Password">密码</label>
							<div class="bar"></div>
						</div>
						<%-- 验证码模块
                    <div class="input-container">
                        <input type="text" name="yzm" id="yzm" required="required" /> <label for="Password">验证码</label>
                        <div class="bar"></div>
                    </div>

                	</li> <img id="randomcode_img" src="../../validatecode.jsp" class="img-rounded pull-right" width='90'
                        height='40' align='absMiddle' onclick="randomcode_refresh()">--%>
						<li><span class="login" id="message"></span>
						<div class="button-container">
							<button type="button" id="btnlogin" onclick="loginsubmit(this);">
								<span>登陆</span>
							</button>
						</div>
					</form>
				</div>

			</div>
			<div class="pen-title">
				<span>设计开发 <i class='fa fa-code'></i> by <a href='http://chouchongkeji.com' target='_blank'>臭虫科技</a> </span>
			</div>
		</div>

	</div>

	</div>

</body>
<!-- URL参数 -->
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/login/layer.min.js"></script>
<script type="text/javascript" src="js/url.js"></script>
<script type="text/javascript" src="js/JQUERY-MD5.js"></script>

</html>

