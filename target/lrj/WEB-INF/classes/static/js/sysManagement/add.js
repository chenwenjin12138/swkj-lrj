/*************************************全局对象设置*******************************************************************/

/*************************************全局对象设置*******************************************************************/

/*************************************js方法声明*******************************************************************/
/** 校验表单* */
$("#frm-add-admin").validate({
	rules : {
		"adminName" : {
			required : true,
			rangelength : [ 3, 16 ],
			userNameValidate : true,
			remote : {
				url : "checkAccountIsExist",
				type : "GET",
				dataType : "text",
				data : {
					adminName : function() {
						return $("#adminName").val();
					}
				},
				dataFilter : function(data) {
                    var data = JSON.parse(data);
					if (data.isExist == Constant.AVAILABLE) {
						return true;
					} else {
						return false;
					}
				}

			}
		},
		"adminPassword" : {
			required : true,
			rangelength : [ 6, 32 ]
		},
		"active" : {
			required : true,
		},
		"sysAdminRoleType" : {
			required : true,
		}
	},
	messages : {
		"adminName" : {
			required : "用户名不能为空!",
			rangelength : "用户名长度必须介于6-16个字符!",
			remote : "用户名已被占用!"
		},
		"adminPassword" : {
			required : "密码不能为空!",
			rangelength : "密码长度为6到32位!"
		},
		"active" : {
			required : "请选择用户状态!",
		},
		"sysAdminRoleType" : {
			required : "请选择用户角色"
		}

	}
});
/** ***********************************js方法声明****************************************************************** */

/** ***********************************js方法执行****************************************************************** */

$(document).ready(function() {
	/** 加载角色下拉选项* */
	selectValidityRole();
	/**
	 * 保存按钮  点击
	 */
	$("button[id='btn-saveAccount']").click(function() {
		/** 校验表单输入* */
		if ($("#frm-add-admin").valid()) {

			/** 密码加密* */
			$("input[name='adminPassword']").val($.md5($("input[name='adminPassword']").val()));
			/** 调用通用保存方法* */
			submit("addSysUser", $("#frm-add-admin"), "toAccountManangemet");
		}

	});
});
/** ***********************************js方法执行****************************************************************** */
