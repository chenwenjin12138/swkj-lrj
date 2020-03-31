/**
 * 
 * 项目名称：lanrenxiyi 类名称：base.js 类描述：JS业务代码通用方法 创建人：SAM 创建时间：2015-10-21 下午2:20:27
 * 修改人：SAM 修改时间：2015-10-21 下午2:20:27 修改备注：
 * 
 * @author SAM QZL
 * @version
 * 
 */
/** 全局分页对象* */
var pageModel = null;
/** 数据源* */
var dataSource = null;
$.ajaxSetup({
	async : false,
	cache : false
});

function delImg(filePath) {
	var bool;
	$.get(basePath + "file-upload/img-del", {
		filePath : filePath
	}, function(data, status) {
		if (status == Constant.AJAXSTATUSSUCCESS) {
			if (data.flag == Constant.SUCCESS) {
				bool = true;
			} else {
				bool = false;
			}
		} else {
			bool = false;
		}
	}, "json");
	return bool;
}



/**
 * 批量删除
 * 
 * @param ids
 */
function del(ids, uri) {
	$.get(basePath + uri, {
		IDS : ids
	}, function(data, status) {
		if (status == Constant.AJAXSTATUSSUCCESS) {
			if (data.flag == Constant.SUCCESS) {
				msg(data.message, 2, 1);
			} else {
				msg(data.message, 2, 2);
			}
		} else {
			alert("程序异常!");
		}
	}, "json");
}

/**
 * 状态启用停用状态改变AJAX方法(GET)
 * 
 * @param state
 * @param idSign
 * @param uri
 * @param $grid
 *            表格对象
 */
function changeState(state, idSign, uri, $grid) {
	var selections = $grid.pqGrid("selection", {
		type : "row",
		method : "getSelection"
	});
	var IDS = new Array();// 选中帐号IDS
	for ( var i = 0; i < selections.length; i++) {
		var id = selections[i].rowData[idSign];
		IDS.push(id);
	}
	$.get(basePath + uri, {
		IDS : IDS + "",
		state : state
	}, function(data, status) {
		if (status == Constant.AJAXSTATUSSUCCESS) {
			if (data.flag == Constant.SUCCESS) {
				msg(data.message, 2, 1);
			} else {
				msg(data.message, 2, 2);
			}
		} else {
			layer.alert("程序异常!", 9);
		}
	}, "json");
}

/**
 * 数据列表显示,集成查询方法(POST)
 * 
 * @param uri
 * @param form
 */
function list(uri, form, grid) {
	rebulid();
	/** 弹出加载层* */
	var index = layer.load('数据正在努力加载中...', 3);
	$.ajax({
		type : 'POST',
		url : basePath + uri,
		async : true,
		data : form.serialize(),
		dataType : "json",
		success : function(data) {
			grid(data);
			/** 关闭加载层* */
			layer.close(index);
		},
		error : function(data) {
			console.log(data);
		},
	});

}

/**
 * 加载列表数据,保存数据源dataSource.
 * 
 * @param uri
 * @param form
 * @param grid
 */
function listWithDataSource(uri, form, grid) {
	rebulid();
	/** 弹出加载层* */
	var index = layer.load('数据正在努力加载中...', 3);
	$.ajax({
		type : 'POST',
		url : basePath + uri,
		async : true,
		data : form.serialize(),
		dataType : "json",
		success : function(data) {
			grid(data);
			dataSource = data;
			/** 关闭加载层* */
			layer.close(index);
		},
		error : function(data) {
		},
	});

}

/**
 * 服务端分页查询
 * 
 * @param uri
 * @param form
 * @param grid
 * @param component
 *            jquery component.html() 标签
 */
function listByServerPage(uri, form, grid, component, callBack) {
	rebulid();
	/** 弹出加载层* */
	var index = layer.load('数据正在努力加载中...', 3);
	$.ajax({
		type : 'POST',
		url : basePath + uri,
		async : true,
		data : form.serialize(),
		dataType : "json",
		success : function(data) {
			pageModel = data;
			grid(data.list);
			createPageIndex(component, pageModel.pageIndex);// 创建索引
			$("a[currentPage='" + pageModel.currentPage + "']").parent().addClass("active");// 选中当前页索引
			$("#pageRecord").html(
					"共:" + pageModel.totalRows + "条，共:" + pageModel.totalPages + "页，当前页:第" + pageModel.currentPage
							+ "页")// 更新显示记录
			try {
				callBack();// 执行回调函数
			} catch (e) {
				console.log(e);
			}

			layer.close(index);
		},
		error : function(data) {
		},
	});

}

/**
 * 创建页面分页索引
 * 
 * @param component
 * @param pageIndex
 */
function createPageIndex(component, pageIndex) {
	console.log(pageIndex);
	var str = "<li><a id=\"prePage\" href=\"javascript:\">上一页</a></li>";

	if (pageIndex.begin != 0 && pageIndex.end != 0) {
		for ( var i = pageIndex.begin; i <= pageIndex.end; i++) {

			str += "<li class=\"\"><a  name='index' currentPage='" + i + "' href=\"javascript:\">" + i + "</a></li>"
		}
	}

	str += "<li><a id=\"nextPage\" href=\"javascript:\">下一页</a></li>"
	component.html(str);
}

/**
 * 刷新表格对象
 */
/**
 * 
 */
function rebulid() {
	$("#grid_array").remove();
	$("#grid_array_parent").append("<div id=\"grid_array\"></div>");
}

/**
 * 获取选中行对象
 * 
 * @param $grid
 * @returns
 */
function getRowSelection($grid) {

	/** 获取选中行的对象数组* */
	var selections = $grid.pqGrid("selection", {
		type : "row",
		method : "getSelection"
	});

	return selections;

}

/**
 * 校验是否选中数据表格行对象
 * 
 * @returns {Boolean}
 */
function validateRowSelection($grid) {
	/** 获取选中行的对象数组* */
	var selections = $grid.pqGrid("selection", {
		type : "row",
		method : "getSelection"
	});
	if (selections.length == 0) {
		// layer.alert("请至少选择一行数据!", 8);
		alert("请至少选择一行数据!");
		return false;
	} else {
		return true;
	}
}