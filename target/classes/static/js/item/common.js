/** ************************************************************商品类别选择公共代码************************************************************************** */

/** 数据请求设置为同步* */
$.ajaxSetup({
	async : false
});
var nodeList = []; // 全局 查询到树节点集合
var zTreeObj = null; // 当前树结构对象
var orginData = null;
var searchFlag = 0;// 0 代表没有查询过

/**
 * ajax获取商品分类数据
 * 
 * @param null
 * @return null
 */
function getItemCateGroryData() {
	$.get(basePath + "cat-list", {}, function(data, status) {
		if (status == Constant.AJAXSTATUSSUCCESS) {
			orginData = data;// 保存原始数据
			initTree(data);
		} else {
			alert("程序异常!");
		}
	}, "json");
}

/**
 * 构建树结构
 * 
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

		}

	};
	zTreeObj = $.fn.zTree.init($("#itemCateGory_select"), setting, obj);
	zTreeObj.expandAll(true);

}

/**
 * 根据node获取所有父节点
 * 
 * @param node
 *            节点
 * @param tree
 *            保存节点数组
 */
function getParents(node, tree) {
	while (node.getParentNode()) {
		node = node.getParentNode();
		tree.push(node);
	}
}

/**
 * 根据node节点获取其所有子节点以及子节点的子节点一直往下
 * 
 * @param node
 *            节点
 * @param tree
 *            保存节点数组
 */
function getChilds(node, tree) {

	if (node && node.length > 0) {

		var pchilds = new Array();

		for ( var j = 0; j < node.length; j++) {

			var childs = node[j].children;

			if (childs) {

				for ( var key in childs) {
					tree.push(childs[key]);
				}

				pchilds.push(childs)
			}

		}

		getChilds(pchilds);

	}
}

/**
 * 根据查询结果nodes节点重新构建上下级树结构
 */
function rebuildSearchTreeData(nodeList) {
	var tree = [];
	for ( var i = 0; i < nodeList.length; i++) {
		getParents(nodeList[i], tree);
		getChilds(new Array(nodeList[i]), tree);
		tree.push(nodeList[i]);
	}
	return tree;
}

/**
 * 重构简单ztree树结构数据,根据搜索的nodes数据
 */
function recreateData(tree) {
	var arr = [];
	tree = tree.sort(createComparsionFunction("appItemCategoryId"));// 按照appItemCategoryId升序
	var lastItemCategoryId;// 保存一个ID,为了去重复
	for ( var a = 0; a < tree.length; a++) {
		if (lastItemCategoryId != tree[a].appItemCategoryId && tree[a].appItemCategoryId) { // 排除undefined数据
			var newTree = {};
			newTree.appItemCategoryId = tree[a].appItemCategoryId;
			newTree.categoryName = tree[a].categoryName;
			newTree.categroryPic = tree[a].categroryPic;
			newTree.pid = tree[a].pid;
			newTree.isShow = tree[a].isShow;
			lastItemCategoryId = tree[a].appItemCategoryId;// 保留上一个ID
			arr.push(newTree);
		}
	}
	return arr;

}
$(document).ready(
		function() {

			try {
				/** 图片上传* */
				$("#image").uploadify(
						{
							'height' : 27,
							'width' : 80,
							'buttonText' : '选择图片',
							'swf' : basePath + 'assets/uploadify3/uploadify.swf',
							'uploader' : basePath + "file-upload/image-upload?type=0",// 0代表
																						// 商品图片上传
							'auto' : true,
							'multi' : false,
							'removeCompleted' : false,
							'cancelImg' : basePath + 'assets/uploadify3/98fff0986f_24.png',
							'fileTypeExts' : '*.jpg;*.jpge;*.gif;*.png',
							'fileSizeLimit' : '2MB',
							'preventCaching' : true,
							'onUploadSuccess' : function(file, data, response) {
								var res = JSON.parse(data);
								$('#' + file.id).find('.data').html('');
								$("#url").val(res.data);// 设置上传成功图片URL到表单
								$("#image_show").attr("src", fileBaseUrl + res.data);// 设置图片预览
								$("#image_show").show();// 显示该图片
								$("#img_del").show();// 删除图片按钮显示
								$("#image").uploadify('disable', true);// 上传成功一次后禁用
							},
							// 加上此句会重写onSelectError方法【需要重写的事件】
							'overrideEvents' : [ 'onSelectError', 'onDialogClose' ],
							// 返回一个错误，选择文件的时候触发
							'onSelectError' : function(file, errorCode, errorMsg) {
								switch (errorCode) {
								case -110:
									alert("文件 [" + file.name + "] 大小超出系统限制的"
											+ jQuery('#image').uploadify('settings', 'fileSizeLimit') + "大小！");
									break;
								case -120:
									alert("文件 [" + file.name + "] 大小异常！");
									break;
								case -130:
									alert("文件 [" + file.name + "] 类型不正确！");
									break;
								}
							},
						});

				/**
				 * 删除上传的图片
				 */
				$("#img_del").click(function() {
					/** 删除服务端图片* */
					delImg($("#url").val());
					$("#url").val(null);// 表单URL制空
					$("#image_show").attr("src", null);// 图片显示URL制空
					$("#image_show").hide();// 图片隐藏
					$(this).hide();
					// 删除按钮隐藏
					$("#image").uploadify('disable', false);// 删除成功后开启上传功能,因为一个商品只能有一张图片
					$("#image").uploadify('cancel', '*');// 移除任务队列
				});
			} catch (e) {
				console.log(e);
			}

			/** autocomplete* */
			$(function() {
				$(function() {
					$("#serachVal").autocomplete(orginData, {
						max : 12, // 列表里的条目数
						minChars : 0, // 自动完成激活之前填入的最小字符
						width : 218, // 提示的宽度，溢出隐藏
						scrollHeight : 300, // 提示的高度，溢出显示滚动条
						matchContains : true, // 包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
						autoFill : false, // 自动填充
						formatItem : function(row, i, max) {
							return i + "/" + max + ":" + row.categoryName;
						},
						formatMatch : function(row, i, max) {
							return row.categoryName;
						},
						formatResult : function(row) {
							return row.categoryName;
						}
					}).result(function(event, row, formatted) {
					});
				});
			});


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
			$("#itemCategoryName").click(function() {
				$("#itemCateGory_Tree_div").show();
			});

			$("#search").click(function() {
				if ($.trim($("#serachVal").val()) != "") {
					initTree(orginData);// 重置树
					nodeList = zTreeObj.getNodesByParamFuzzy("categoryName", $("#serachVal").val());
					var tree = rebuildSearchTreeData(nodeList);
					initTree(recreateData(tree));
				}
			});

			$("#reset").click(function() {
				$("#serachVal").val(null);
				initTree(orginData);// 重置树
			});

			/** 商品类别选择取消* */
			$("button[name='cancel']").click(function() {
				$("#itemCateGory_Tree_div").hide();
			})

			/** 商品类别选择确认* */
			$("#commit").click(function() {
				var selectNode = zTreeObj.getSelectedNodes();// 获取选中的节点
				if (selectNode.length != 0) { // 校验选中一个

					if (!selectNode[0].isParent) {
						$("#itemCategoryId").val(selectNode[0].appItemCategoryId);// 把选中ID赋值表单
						$("#itemCategoryName").val(selectNode[0].categoryName);// 把选中NAME赋值表单}
						$("#itemCateGory_Tree_div").hide();// 关闭选择项
					} else {
						alert("只能选择末级类别添加!");
					}
				} else {
					alert("请点击选择一个类别!");
				}
			});
		});

/** ************************************************************商品类别选择公共代码************************************************************************** */
