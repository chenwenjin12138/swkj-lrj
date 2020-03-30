/*************************************全局对象设置*******************************************************************/
/** 表格全局对象* */
var $grid = {};

/** ***********************************全局对象设置****************************************************************** */

/** ***********************************js方法声明****************************************************************** */

/** 查询校验* */
$("#admin_query").validate({
	rules : {
		"adminName" : {
			maxlength : 16,
		},
		"sysAdminId" : {
			integerPositiveValidate : true
		}
	},
	messages : {
		"adminName" : {
			maxlength : "输入不能超过16个字符!",
		}

	}
});

/**
 * 绘制表格
 * 
 * @param data
 */

/** ***********************************js方法声明****************************************************************** */

/** ***********************************js方法执行****************************************************************** */

$(document).ready(function() {

	/** 角色下拉框加载* */
	selectValidityRole();
	/** 初始化表单条件* */
	/*initQueryForm($("#admin_query"));*/
	/** 加载列表* */
	list("admin/list", $("#admin_query"), grid);

	/** 编辑按钮事件触发* */
	$("#edit").live("click", function() {
		var url = $(this).attr("url");
		/** 带上条件编辑条件和查询条件* */
		location.href = url + "&" + $('#admin_query').serialize();
	});

	/**
	 * 启用账户按钮事件绑定执行方法
	 */
	$("#invoke").click(function() {
		if (validateRowSelection($grid)) {
			changeState(Constant.ACTIVE, "sysAdminId", "admin/changeState", $grid);
			/**改变状态后重新加载数据**/
			list("admin/list", $("#admin_query"), grid);
		}
	});
	/**
	 * 禁止用账户按钮事件绑定执行方法
	 */
	$("#forbidden").click(function() {
		if (validateRowSelection($grid)) {
			changeState(Constant.FORBIDDEN, "sysAdminId", "admin/changeState", $grid);
			list("admin/list", $("#admin_query"), grid);// 改变状态后重新加载数据
		}
	});

	/** 查询按钮调用* */
	$("#search_admin").click(function() {
		if ($("#admin_query").valid()) {
			list("admin/list", $("#admin_query"), grid);
		}

	})

});
/** ***********************************js方法执行****************************************************************** */
