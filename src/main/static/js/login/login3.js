var basePath ="../"
/**
 * 验证码刷新
 */
/*function randomcode_refresh() {
	$("#randomcode_img").attr('src','../validatecode.jsp?time' + new Date().getTime());
}*/
/**
 * 登录检验 是否为空 处理方法
 */
function loginsubmit() {
	var flag = validateInput();// 校验标志
    var username = $.trim($("#userName").val());
    var password = $.trim($("#passWord").val());
	if (flag) {
		$("#message").text("");// 不显示输入错误信息
		$.getJSON(
			'/userLogin',
			{'userName':username,'passWord':$.md5(password)},
			function (json) {
				if(json.errorCode !=0){
                    $("#message").text(json.errorTip);
				}else {
					window.location.href= json.errorTip;
				}
            }
		);

	}
}

/**
 * 登录信息用户输入校验
 * 
 * @returns {Boolean}
 */
function validateInput() {
	var username = $.trim($("#userName").val());
	var password = $.trim($("#passWord").val());
    /**
	 * 验证码功能效验，暂时注销，无太大作用
     */
	/*var yzm = $.trim($("#yzm").val());
	var validateCode ="<%=session.getAttribute("validateCode")%>";
	alert(validateCode);*/
    if (username == "") {
		$("#message").text("用户名不能为空!");
		return false;
	} else if (password == "") {
		$("#message").text("密码不能为空!");
		return false;
	}else {
		return true;
	}
}

/**
 * 监听键盘回车登录事件
 */
/*$("body").bind("keydown",function(e){
	//兼容浏览器
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {    
        //回车执行登录
            $("#btnlogin").click();
        }    
})*/
