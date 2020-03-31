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
var pageModel = {};
/** 数据源* */
var dataSource = {};
$.ajaxSetup({
	async : false,
	cache : false
});
/**
 * 通用数据新增保存AJAX方法(POST)
 * 
 * @param uri
 *            表单提交的地址
 * @param form
 *            表单jquery对象
 * @param returnUri
 *            返回列表页面地址
 */
function
add(uri, form, returnUri) {
	$.post(basePath + uri, form.serialize(), function(data, status) {
		if (status == Constant.AJAXSTATUSSUCCESS) {
			if (data.flag == Constant.SUCCESS) {
				msg(data.message, 2, 1, function() {
					location.href = basePath + returnUri;
				});
			} else {
				msg(data.message, 2, 2);
			}
		} else {
			msg("系统异常!", 2, 2);
		}
	}, "json");

}
/**
 * 通用数据保存AJAX方法(POST)--不带返回地址
 * 
 * @param uri
 *            表单提交的地址
 * @param form
 *            表单jquery对象
 */
function save(uri, form, yes, no) {
	$.post(basePath + uri, form.serialize(), function(data, status) {
		if (status == Constant.AJAXSTATUSSUCCESS) {
			if (data.flag == Constant.SUCCESS) {
				alert(data.message);
				if (yes)
					yes();
			} else {
				alert(data.message);
				if (no)
					no();
			}
		} else {
			alert("程序异常!");
		}
	}, "json");

}

/**
 * 同步图片删除方法
 * 
 * @param filePath
 * @returns
 */
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
 * 通用数据更新保存AJAX方法(POST)
 * 
 * @param uri
 *            表单提交的地址
 * @param form
 *            表单jquery对象
 * @param returnUri
 *            返回列表页面地址
 */
function edit(uri, form, returnUri) {
	$.post(basePath + uri, form.serialize(), function(data, status) {
		if (status == Constant.AJAXSTATUSSUCCESS) {
			if (data.flag == Constant.SUCCESS) {
				msg(data.message, 2, 1, function() {
					location.href = basePath + returnUri
				});
			} else {
				msg(data.message, 2, 2);
			}
		} else {
			msg("系统异常!", 2, 2);
		}
	}, "json");
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
 * 刷新表格2
 */
function rebulid2() {
	$("#grid_array2").remove();
	$("#grid_array_parent2").append("<div id=\"grid_array2\"></div>");
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

/**
 * 图片详情
 */
function imgDetail(url) {
	var i = $.layer({
		type : 1,
		title : false,
		fix : true,
		shadeClose : true,
		closeBtn : [ 1, true ],
		page : {
			html : "<img src='" + url + "'></img>"
		}
	});
}

/**
 * 获取url参数, 用于返回列表 页面查询条件 重新显示-- 过滤unfolder(用于定位左边菜单选中的参数)参数 返回值以&开头
 */
function getReturnURLparam() {

	/** 获取?后的参数字符串* */
	var search = (location.search.split("?"))[1];
	/** 如果存在继续处理* */
	if (search) {
		/** 获取每组参数* */
		var entrys = search.split("&");
		/** 过滤后的参数* */
		var param = "&";
		if (entrys && entrys.length > 0) {

			var index = 0;

			/** 迭代获取每组参数* */
			for ( var k in entrys) {

				/** 参数名* */
				var key = (entrys[k].split("="))[0];
				/** 参数值* */
				var value = (entrys[k].split("="))[1];
				/** 过滤unfolder* */
				if (key != "unfolder") {
					if (index != 0) {
						param += "&";
					}
					param += key + "=" + value;
					index++;
				}

			}

		}
		return param;

	} else {
		return "";
	}

}
/**
 * 查询条件表单 初始化
 * 
 * @param form
 *            jquery表单对象-仅支持id选择器
 */
function initQueryForm(form) {

	/** 获取初始化参数* */
	var initParamsMap = getURLParamsMap();
	/** 遍历赋值* */
	for ( var i = 0; i < form[0].length; i++) {
		var jq = $(form[0][i]);
		/** 获取参数值* */
		var value = initParamsMap[jq.attr("name")];
		if (value) {
			/** 解码* */
			jq.val(decodeURI(value));
		}
	}

}