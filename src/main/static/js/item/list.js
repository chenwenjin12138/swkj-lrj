/** ***********************************全局对象设置****************************************************************** */
/** 全局表格对象* */
var $grid = {};
/** ***********************************全局对象设置****************************************************************** */

/** ***********************************js方法声明****************************************************************** */

/** 查询校验* */
$("#item_query").validate({
	rules : {
		"appItemId" : {
			integerPositiveValidate : true
		}
	},
	messages : {
		"appItemId" : {
			integerPositiveValidate : "请输入正确的商品编号!"
		}

	}
});

$("#frm-itemHot").validate({
	rules : {
		"promotionOriginalCost" : {
			required : true,
			number : true,
		},
		"promotionBeginDate" : {
			required : true
		},
		"promotionEndDate" : {
			required : true
		}
		

	},
	messages : {
		"promotionOriginalCost" : {
			required : "促销价不能为空!",
			number : "必须输入合法的数字",
		},
		"promotionBeginDate" : {
			required : "促销开始日期不能为空!"
		},
		"promotionEndDate" : {
			required : "促销结束日期不能为空!"
		}
	}
});	

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
			title : "<img width='15px' src='" + basePath + "table.png'></img>&nbsp;商品列表",
			flexHeight : true,
			pageModel : {
				type : 'local',
				rPP : 20,
				rPPOptions : [ 20, 20, 50, 100 ]
			},

			selectionModel : {
				type : null
			}

		};
		obj.colModel = [
				{
					width : '3%',
					dataIndx : "null",
					align : "center",
					title : "<input type='checkbox'/>",
					type : 'checkBoxSelection',
					dataType : 'bool',
					editable : false,
					sortable : false
				},
				{
					title : "状态",
					width : '5%',
					dataType : "integer",
					dataIndx : "isShow",
					editable : false,
					sortable : false,
					align : "center",
					render : function(ui) {
						var data = ui.rowData;
						if (data.isShow == Constant.SHOW) {
							return "<span class='label label-small label-success label-font'>显示</span>";
						} else if (data.isShow == Constant.HIDDEN) {
							return "<span class='label label-small label-warning label-font'>隐藏</span>";
						}
					}
				},
				{
					title : "商品编号",
					width : '5%',
					dataType : "integer",
					dataIndx : "appItemId",
					editable : false,
					sortable : true,
					align : "center",
					hidden : true
				},
				{
					title : "名称",
					width : '10%',
					dataType : "string",
					dataIndx : "itemName",
					editable : false,
					/* hidden : true, */
					sortable : true,
					align : "center"
				},
				{
					title : "商品类别ID",
					width : '10%',
					dataType : "string",
					dataIndx : "itemCategoryId",
					editable : false,
					hidden : true,
					sortable : false,
					align : "center"
				},
				{
					title : "类别",
					width : '10%',
					dataType : "string",
					dataIndx : "categoryName",
					editable : false,
					sortable : false,
					align : "center"
				},
				{
					title : "单位",
					width : '5%',
					dataType : "string",
					dataIndx : "itemUnit",
					editable : false,
					sortable : false,
					align : "center"
				},
				{
					title : "价格(元)",
					width : '5%',
					dataType : "string",
					dataIndx : "price",
					editable : false,
					sortable : true,
					align : "center"
				},

				{
					title : "洗衣时长",
					width : '7%',
					dataType : "string",
					dataIndx : "duration",
					editable : false,
					sortable : false,
					align : "center"
				},

				{
					title : "图片",
					width : '4%',
					dataType : "string",
					dataIndx : "picture",
					editable : false,
					sortable : false,
					align : "center",
					render : function(ui) {
						return "<button  class='btn btn-small btn-info' url='" + ui.rowData.picture
								+ "' id='see_image'>查看</button>"
					}
				},

				{
					title : "商品说明",
					width : '21%',
					dataType : "string",
					dataIndx : "commodityExplain",
					editable : false,
					sortable : false,
					align : "center"
				},
				{
					title : "创建时间",
					width : '9%',
					dataType : "string",
					dataIndx : "createTime",
					editable : false,
					sortable : true,
					align : "center",
					render : function(ui) {
						return String.dateFormat(ui.rowData, "createTime");
					}
				},

				{
					title : "更新时间",
					width : '9%',
					dataType : "string",
					dataIndx : "updateTime",
					editable : false,
					sortable : false,
					align : "center",
					render : function(ui) {
						return String.dateFormat(ui.rowData, "updateTime");
					}
				},

				{
					title : "操作",
					width : "12%",
					editable : false,
					sortable : false,
					align : "center",
					render : function(ui) {
						return "<button class='btn btn-small btn-info' url='" + basePath
								+ "item/init-edit?unfolder=item-init-list&appItemId=" + ui.rowData.appItemId
								+ "&itemCategoryName=" + ui.rowData.categoryName 
								+ "&itemCategoryId="+ui.rowData.itemCategoryId +"'  id='edit'>编辑</button>  "
								+ "<button class='btn btn-small btn-info' appItemId='"+ ui.rowData.appItemId
								+ "' itemCategoryId='"+ui.rowData.itemCategoryId+"' id='setToHot'>设为爆品</button>";
								/*+ "<button class='btn btn-small btn-danger' appItemId='"+ ui.rowData.appItemId
								+ "' id='delById'>删除</button>";*/
					}
				}

		];

		obj.dataModel = {
			data : data,
			location : "local",
			sorting : "local",
			dataType : "JSON",
			sortDir : "down",
			sortIndx : "createTime"
		};
		$grid = $("#grid_array").pqGrid(obj);

	});
}

/** ***********************************js方法声明****************************************************************** */

/** ***********************************js方法执行****************************************************************** */

$(document).ready(function() {
	/** 获取商品分类信息* */
	getItemCateGroryData();
	/** 初始化表单条件* */
	initQueryForm($("#item_query"));
	/** 页面初始化加载列表* */
	list("list", $("#item_query"), grid);

	/** 编辑按钮事件触发* */
	$("#edit").live("click", function() {
		var url = $(this).attr("url");
		location.href = url + "&" + $('#item_query').serialize();
	});

	/**
	 * 显示商品按钮事件绑定执行方法
	 */
	$("#invoke").click(function() {
		if (validateRowSelection($grid)) {
			changeState(Constant.SHOW, "appItemId", "item/changeState", $grid);
			list("list", $("#item_query"), grid);// 重新加载数据
		}
	});
	/**
	 * 不显示商品按钮事件绑定执行方法
	 */
	$("#forbidden").click(function() {
		if (validateRowSelection($grid)) {
			changeState(Constant.HIDDEN, "appItemId", "item/changeState", $grid);
			list("list", $("#item_query"), grid);// 重新加载数据
		}
	});

	/** 查询按钮调用* */
	$("#search_item").click(function() {
		if ($("#item_query").valid()) {
			list("list", $("#item_query"), grid);// 重新加载数据
		}

	})

	/** 点击查看图片调用* */
	$("#see_image").live("click", function() {
		var url = fileBaseUrl + $(this).attr("url");
		$("#image_show").attr("src", url);
		$("#image_modal").modal("show");
	})
	
	/** 设置为爆品* */
	$("#setToHot").live("click",function(){
		var a = window.confirm("确定要设置吗？");
		if(a){
			if(($(this).attr("itemCategoryId"))==17){
				alert("该商品已经是爆品!");
			}else{
				//alert($(this).attr("appItemId"));
				$("#appItemId2").val($(this).attr("appItemId"));
				$("#hot_model").modal("show");
				
			}
		}
	})
	
	/**
	 * 设置爆品保存信息
	 */
	$("#saveHot").live("click",function(){
		if ($("#frm-itemHot").valid()) {
			var promotionOriginalCost = $("#promotionOriginalCost").val();
			var promotionBeginDate = $("#promotionBeginDate").val();
			var promotionEndDate = $("#promotionEndDate").val();
			//alert($("#appItemId2").val());
			$.ajax({
				url:basePath+'setToHot',
				data:{
					appItemId:$("#appItemId2").val(),
					promotionOriginalCost:promotionOriginalCost,
					promotionBeginDate:promotionBeginDate,
					promotionEndDate:promotionEndDate
				},
				type:'post',
				dataType:'json',
				error:function(data){
					alert("程序异常!");
				},
				success:function(data){
					if(data.flag==0){
						alert(data.message);
						/** 页面初始化加载列表* */
						$("#hot_model").modal("hide");
						list("item/list", $("#item_query"), grid);
					}else{
						alert("程序异常!");
					}
				}
				
			});
		}
	});
	
	
	/** 删除单条记录* */
	$("#delById").live("click",function(){
		var a = window.confirm("您确定要删除该记录吗?");
		if(a){
			//alert($(this).attr("appItemId"));
			$.ajax({
				url:basePath+'item/delById',
				data:{
					appItemId:$(this).attr("appItemId")
				},
				type:'post',
				dataType:'json',
				error:function(data){
					alert("程序异常!");
				},
				success:function(data){
					if(data.flag==0){
						alert(data.message);
						/** 页面初始化加载列表* */
						list("item/list", $("#item_query"), grid);
					}else{
						alert("程序异常!");
					}
				}
			})
		}
		
	})
	
	

});
/** ***********************************js方法执行****************************************************************** */
