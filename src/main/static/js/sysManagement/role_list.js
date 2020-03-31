/*************************************全局对象设置*******************************************************************/

/**角色特殊没有用到base.js的公共方法,用的是自己特使的方法**/

/** 权限ID数组* */
var authority_ids = new Array();
/** 权限树JS对象* */
var zTreeAuthorityObj = null;
/** 表格对象* */
var $grid = null;
/** 0代表保存1代表编辑* */
var saveOredit = Constant.SAVE;
/** ***********************************全局对象设置****************************************************************** */

/** ***********************************js方法声明****************************************************************** */

/**
 * 清空表单输入数据
 */
function cleanInput() {
	$("#roleName").val(null);
	$("#roleDescride").val(null);
	authority_ids.length = 0;// 权限ID数组清空
	$("#active").val(null);
	$("#authority_select").val(null);
	zTreeAuthorityObj.checkAllNodes(false);
	saveOredit = Constant.SAVE;// 标志位 为保存
	$("button[name='btn-save']").html("保存");// 更换BUTTON保存名称
}

/**
 * 绘制表格
 * 
 * @param data
 */
function grid(data) {
	$(function() {
		var obj = {
			width : '100%',
			height : 1000,
			title : "<img width='15px' src='" + basePath + "img/icon/table.png'></img>&nbsp;角色列表",
			flexHeight : true,
			pageModel : {
				type : 'local',
				rPP : 8,
				rPPOptions : [ 8, 10, 20, 50, 100 ]
			},

			selectionModel : {
				type : null
			}
		};
		obj.colModel = [
				{
					width : '10%',
					dataIndx : "null",
					align : "center",
					title : "<input type='checkbox' />全选",
					type : 'checkBoxSelection',
					dataType : 'bool',
					editable : false,
					sortable : false
				},
				{
					title : "状态",
					width : '10%',
					dataType : "integer",
					dataIndx : "active",
					editable : false,
					sortable : false,
					align : "center",
					render : function(ui) {
						var data = ui.rowData;
						if (data.active == Constant.ACTIVE) {
							return "<span class='label label-small label-success label-font'>启用</span>";
						} else if (data.active == Constant.FORBIDDEN) {
							return "<span class='label label-small label-warning label-font'>禁用</span>";
						}
					}
				},
				{
					title : "角色编号",
					width : '10%',
					dataType : "integer",
					dataIndx : "sysRoleId",
					editable : false,
					sortable : true,
					align : "center",
					hidden : true
				},
				{
					title : "角色名",
					width : '10%',
					dataType : "string",
					dataIndx : "roleName",
					editable : false,
					sortable : false,
					align : "center"
				},
				{
					title : "角色描述",
					width : '20%',
					dataType : "string",
					dataIndx : "roleDescride",
					editable : false,
					sortable : false,
					align : "center"
				},
				{
					title : "授权",
					width : '40%',
					dataType : "string",
					dataIndx : "authorityNames",
					editable : false,
					sortable : false,
					align : "center"
				},
				{
					title : "操作",
					width : "10%",
					editable : false,
					sortable : false,
					align : "center",
					render : function(ui) {
						return "<button class='btn btn-small btn-info' rowIndex='" + ui.rowIndxPage
								+ "'  name='role-edit'>编辑</button>";
					}

				}

		];

		obj.dataModel = {
			data : data,
			location : "local",
			sorting : "local",
			dataType : "JSON",
			sortDir : "down",
			sortIndx:"sysRoleId"
		};
		$grid = $("#grid_array").pqGrid(obj);

	});
}
/**
 * 特殊保存角色JS处理方法
 */
function add() {
	$.post(basePath + "role/add", {
		roleName : $("#roleName").val(),
		roleDescride : $("#roleDescride").val(),
		active : $("#active").val(),
		authority_ids : authority_ids + ""
	}, function(data, status) {
		if (status == Constant.AJAXSTATUSSUCCESS) {
			if (data.flag == Constant.SUCCESS) {
				msg(data.message, 2, 1);
				cleanInput();// 清空表单
				list("role/list", $("#frm-add-role"), grid);// 重新加载表格数据
			} else {
				msg(data.message, 2, 2);
			}

		} else {
			msg("保存失败!", 2, 2);
		}
	}, "json");

}
/**
 * 特殊角色编辑处理方法
 */
function edit() {
	$.post(basePath + "role/edit", {
		roleName : $("#roleName").val(),
		roleDescride : $("#roleDescride").val(),
		active : $("#active").val(),
		sysRoleId : $("#sysRoleId").val(),
		authority_ids : authority_ids + ""
	}, function(data, status) {
		if (status == Constant.AJAXSTATUSSUCCESS) {
			if (data.flag == Constant.SUCCESS) {
		/*		var param = new Object();
				param.uri = "role/list";
				param.form = $("#frm-add-role");
				param.grid = grid;*/
				msg(data.message, 2, 1);
				cleanInput();
				list("role/list",$("#frm-add-role"),grid);// 重新加载表格数据
			} else {
				msg(data.message, 2, 2);
			}

		} else {
			msg("保存失败!", 2, 2);
		}
	}, "json");

}

/**
 * 初始化权限树形结构, 根据ztree插件实现, 权限树形结构展示
 * 
 * @param obj
 *            数据源
 */
function initAuthorityTree(obj) {
	var setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "sysAuthorityId",
				pIdKey : "authorityPid",
				rootPId : "0",
			},
			key : {
				name : "authorityName"
			}
		},
		view : {
			selectedMulti : false
		},
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}
		},
		callback : {

		},

	};

	zTreeAuthorityObj = $.fn.zTree.init($("#tree_authority"), setting, obj);
	zTreeAuthorityObj.expandAll(true);
}
/**
 * 向后台请求权限列表数据, 并构建树形结构
 */
function initAuthorityData() {
	$.get(basePath + "role/getAuthoritys", {}, function(data, status) {
		if (status == "success") {
			initAuthorityTree(data);// 加载数据源构建树
		} else {
			layer.alert("程序异常!", 9);
		}
	}, "json");
}

/** 校验* */
$("#frm-add-role").validate({
	rules : {
		"roleName" : {
			required : true,
			maxlength : 20
		},
		"roleDescride" : {
			maxlength : 128
		},
		"active" : {
			required : true
		},
		"role_right" : {
			required : true
		}
	},
	messages : {
		"roleName" : {
			required : "角色名不能为空!",
			maxlength : "角色名不能超过20个字符!",
		},
		"roleDescride" : {
			maxlength : "角色描述不能超过128个字符!",
		},
		"active" : {
			required : "请选择角色状态!"
		},
		"role_right" : {
			required : "请选择角色权限!"
		}

	}
});

/**
 * 初始化角色数据到相应表单以供编辑
 * 
 * @data 行数据
 */
function initRole(data) {
	zTreeAuthorityObj.checkAllNodes(false);// 清空所有树的选中项
	$("#roleName").val(data.roleName);// 角色名字回写
	$("#sysRoleId").val(data.sysRoleId);// 角色ID隐藏提交
	$("#roleDescride").val(data.roleDescride);// 角色描述
	$("#active").val(data.active);// 角色状态
	$("#authority_select").val(data.authorityNames);// 角色授权信息
	authority_ids.length = 0;
	for ( var i = 0; i < data.authorityIds.length; i++) {
		var node = zTreeAuthorityObj.getNodeByParam("sysAuthorityId", data.authorityIds[i], null);// 在树中找到该节点
		zTreeAuthorityObj.checkNode(node, true, true);// 编辑中选中这个节点
		authority_ids.push(data.authorityIds[i]);// 保存默认选中的ID
	}
	$("#authorityTree").show();
}
/** ***********************************js方法声明****************************************************************** */

/** ***********************************js方法执行****************************************************************** */

$(document).ready(function() {

	initAuthorityData();
	list("role/list", $("#frm-add-role"), grid);// 加载角色列表
	/**
	 * 保存按钮事件绑定
	 */
	$("button[name='btn-save']").click(function() {
		/** 校验表单输入* */
		if ($("#frm-add-role").valid()) {
			if (saveOredit == Constant.SAVE) {// 0代表保存 1代表编辑
				add();
			} else {
				edit();
			}
		}

	});

	/**
	 * 启用账户按钮事件绑定执行方法
	 */
	$("#invoke").click(function() {
		if (validateRowSelection($grid)) {
			changeState(Constant.ACTIVE, "sysRoleId", "role/changeState", $grid);
			list("role/list", $("#frm-add-role"), grid);//
			// 重新刷新列表
		}
	});
	/**
	 * 禁止用账户按钮事件绑定执行方法
	 */
	$("#forbidden").click(function() {
		if (validateRowSelection($grid)) {
			changeState(Constant.FORBIDDEN, "sysRoleId", "role/changeState", $grid);
			list("role/list", $("#frm-add-role"), grid);//
			// 重新刷新列表
		}

	});

	/** 绑定点击选择权限事件弹出选择框* */
	$("#authority_select").click(function() {
		$("#authorityTree").show();
	});
	/** 绑定关闭权限选项弹框事件* */
	$("button[name='close_authority_modal']").click(function() {
		$("#authorityTree").hide();
	});
	/** 绑定权限选择框确认选择事件* */
	$("#sureSelectAuthority").click(function() {
		var select_nodes = zTreeAuthorityObj.getCheckedNodes(true);// 获取选中权限节点
		var txt = "";// 用于显示选中的权限
		var offset = 0;
		authority_ids.length = 0;// 清空数组
		/** 辨理选中数据,并保存或显示* */
		for ( var i = 0; i < select_nodes.length; i++) {
			if (!select_nodes[i].isParent) {
				if (offset != 0) {
					txt += "；";
				}
				txt += select_nodes[i].authorityName;
				/** 存入id数组中* */
				authority_ids.push(select_nodes[i].sysAuthorityId);
				offset++;
			}
		}
		$("#authority_select").val(txt);
		$("#authorityTree").hide();
	});
	/** 角色编辑按钮事件触发函数* */
	$("button[name='role-edit']").live("click", function() {
		/** 表格对象 根据编辑行ID 获取该行的数据* */
		var rowInex = window.parseInt($(this).attr("rowIndex"));
		var rowData = $grid.pqGrid("getRowData", {
			rowIndxPage : rowInex
		});
		initRole(rowData);// 初始化回写表单数据
		saveOredit = Constant.EDIT;// 标志位 为编辑
		$("button[name='btn-save']").html("点击保存修改");// 更换BUTTON保存名称
		/**浏览器回到顶部**/
		$("html,body").animate({scrollTop:0},200);
	});

});
/** ***********************************js方法执行****************************************************************** */
