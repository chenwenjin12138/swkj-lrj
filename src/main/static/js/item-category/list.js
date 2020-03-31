/*************************************全局对象设置*******************************************************************/
/**商品特殊没有用到base.js的公共方法,用的是自己特使的方法**/

/** 数据请求设置为同步* */
$.ajaxSetup({
	async : false,
	cache:false
});
/** 树对象* */
var zTreeObj = null;

var operate = Constant.EDIT;// 操作对象 1代表编辑,0 代表 新增

/** ***********************************全局对象设置****************************************************************** */

/** ***********************************JS方法声明****************************************************************** */
/**
 * ajax获取商品分类数据
 * 
 * @param null
 * @return null
 */
function list() {
	$.get(basePath + "item-category/list", {}, function(data, status) {
		if (status == Constant.AJAXSTATUSSUCCESS) {
			initTree(data);
		} else {
			layer.alert("程序异常!")
		}
	}, "json");

}

/**
 * 构造树形结构
 * @param obj
 */
function initTree(obj) {
	var setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "appItemCategoryId",
				pIdKey : "pid",
				rootPId : "0",
			},
			key : {
				name : "categoryName"
			}
		},
		view : {
			selectedMulti : false
		},
		callback : {
			onClick : itemCategorySelect,
		}

	};
	zTreeObj = $.fn.zTree.init($("#item-category-tree"), setting, obj);
	zTreeObj.expandAll(false);

}
/**
 * 选中分类信息,显示到表单中
 */
function initSelectCategoryToForm(treeNode) {
	var pnode = treeNode.getParentNode() == null ? {
		categoryName : "无"
	} : treeNode.getParentNode();
	$("#appItemCategoryId").val(treeNode.appItemCategoryId);
	$("#categoryName").val(treeNode.categoryName);
	$("#pid").val(treeNode.pid);
	$("#isShow").val(treeNode.isShow);
	$("#parentName").val(pnode.categoryName);
}
/** 校验是否选中商品类别* */
function validateCategorySelection() {
	return zTreeObj.getSelectedNodes().length != 0;
}

/**
 * 清空编辑区
 */
function cleanInput() {
	/** 清空编辑区,供用户输入新的分类* */
	$("#appItemCategoryId").val(null);
	$("#categoryName").val(null);
	$("#isShow").val(null);
}

/** 新增商品分类初始化必要信息方法* */
function initAdd() {
	operate = Constant.SAVE;// 0代表新增
	cleanInput();// 清空表单
	var checkNode = zTreeObj.getSelectedNodes()[0];// 获取选中节点
	$("#pid").val(checkNode.appItemCategoryId);// 设置它的上级ID
	$("#parentName").val(checkNode.categoryName);// 设置它的上级名称
	$("#showTxt").html("请输入需要新增类别信息");// 设置标题信息
}
/**
 * 特殊新增方法
 */
function add() {
	$.post(basePath + "item-category/add", $("#item-category-form").serialize(), function(data, status) {
		if (status == Constant.AJAXSTATUSSUCCESS) {
			if (data.flag == Constant.SUCCESS) {
				msg(data.message, 2, 1);
				init();// 清空一些残留设置
				list();// 重新加载树
			} else {
				msg(data.message, 2, 2);
			}
		} else {
			layer.alert("程序异常!", 8);
		}
	}, "json");
}
/**
 * 特殊编辑方法
 */
function edit() {
	$.post(basePath + "item-category/edit", $("#item-category-form").serialize(), function(data, status) {
		if (status == Constant.AJAXSTATUSSUCCESS) {
			if (data.flag == Constant.SUCCESS) {
				msg(data.message, 2, 1);
				list();// 重新加载树
			} else {
				msg(data.message, 2, 2);
			}
		} else {
			alert("程序异常!");
		}
	}, "json");
}

/** 初始化,恢复页面默认设置* */
function init() {
	$("#edit-area").hide();
	$("#showTxt").html("编辑");// 设置标题信息
	operate = Constant.EDIT;// 操作符1,代表编辑
	cleanInput();// 清空表单
}

/* ztree点击选中类别执行的函数* */
function itemCategorySelect(event, treeId, treeNode) {
	$("#edit-area").show();// 显示编辑区域
	$("#showTxt").html("编辑");// 设置标题信息
	operate = Constant.EDIT;// 操作符1,代表编辑
	initSelectCategoryToForm(treeNode);
}

/** 校验表单* */
$("#item-category-form").validate({
	rules : {
		"categoryName" : {
			required : true,
			maxlength : 20
		}
	},
	messages : {
		"categoryName" : {
			required : "商品类别名称不能为空!",
			maxlength : "商品类别名称不能超过20个字符!",
		}
	}
});
/** ***********************************JS方法声明****************************************************************** */

/** ***********************************JS方法执行****************************************************************** */
$(document).ready(function() {
	list(); // 加载分类树结构
	$("#add_new_category").click(function() {
		if (validateCategorySelection()) {// 校验是否选中一个上级
			initAdd();
		} else {
			alert("请单击左边类别，再单击新增按钮添加其下级类别!");
		}
	});
	$("#categorySave").click(function() {// 保存方法执行

		if ($("#item-category-form").valid()) {// 先校验表单输入
			if (operate == Constant.SAVE) {
				add();
			} else if (operate == Constant.EDIT) {
				edit();
			}
		}

	});
});
/** ***********************************JS方法执行****************************************************************** */
