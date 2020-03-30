/*************************************全局对象设置*******************************************************************/

/** 数据请求设置为同步* */
$.ajaxSetup({
	async : false
});
/** ***********************************全局对象设置****************************************************************** */

/** ***********************************js方法声明****************************************************************** */

/** 初始化化数据到页面* */
function initAdmin() {
	var sysAdminId = getURLParamsByKey("sysAdminId");// 获取URL参数
	$.get(basePath + "admin/find", {
		sysAdminId : sysAdminId
	}, function(data, status) {
		if (status == Constant.AJAXSTATUSSUCCESS) {
			/** 编辑数据回写表单* */
			$("#sysAdminId").val(data.sysAdminId);
			$("#adminName").val(data.adminName);
			$("#active").val(data.active);
			$("#sysAdminRoleType").find("option[value='" + data.sysAdminRoleType + "']").attr("selected", true);
		} else {
			msg("数据获取失败!", 2, 2);
		}
	}, "json");

}

/** 校验* */
$("#frm-edit-admin").validate({
	rules : {
		"adminPassword" : {
			rangelength : [ 6, 20 ],
			userNameValidate : true,
		},
		"active" : {
			required : true,
		},
		"sysAdminRoleType" : {
			required : true,
		}
	},
	messages : {
		"adminPassword" : {
			rangelength : "密码名长度必须介于6-20个字符!",
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
	/** 角色加载下拉选项* */
	selectValidityRole();
	/** 初始化编辑表单数据* */
	initAdmin();
	/**
	 * 保存按钮事件绑定
	 */
	$("button[name='btn-save']").click(function() {
		/** 校验表单输入* */
		if ($("#frm-edit-admin").valid()) {

			/** 如果修改密码密码加密* */
			if ($("input[name='adminPassword']").val()) {
				$("input[name='adminPassword']").val($.md5($("input[name='adminPassword']").val()));
			}

			/** 调用通用的编辑方法* */
			edit("admin/edit", $("#frm-edit-admin"), "admin/init-list?unfolder=admin-init-list" + getReturnURLparam());
		}

	});
});
/** ***********************************js方法执行****************************************************************** */
