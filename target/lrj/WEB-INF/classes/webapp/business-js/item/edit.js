/** ***********************************全局对象设置****************************************************************** */
$.ajaxSetup({
	async : false
});
/** ***********************************全局对象设置****************************************************************** */

/** ***********************************js方法声明****************************************************************** */

/** 初始化化数据到页面* */
function initItem() {
	var appItemId = getURLParamsByKey("appItemId");// 获取URL参数
	var itemCategoryName = decodeURI(getURLParamsByKey("itemCategoryName"));//中文URI参数解码
	var itemCategoryId = decodeURI(getURLParamsByKey("itemCategoryId"));
	$.get(basePath + "item/find", {
		appItemId : appItemId
	}, function(data, status) {
		if (status == Constant.AJAXSTATUSSUCCESS) {
			/** 编辑数据回写表单* */
			$("#appItemId").val(data.appItemId);//商品编号
			$("#itemName").val(data.itemName);// 商品名
			$("#itemUnit").val(data.itemUnit);
			$("#price").val(data.price);// 价格
			$("#commodityExplain").val(data.commodityExplain);// 说明
			$("#duration").val(data.duration);// 洗护时长
			$("#itemCategoryId").val(data.itemCategoryId);// 类别ID
			$("#itemCategoryName").val(itemCategoryName);// 类别名称
			$("#isShow").val(data.isShow);// 是否显示
			$("#url").val(data.picture);// 图片路径
			$("#image_show").attr("src", fileBaseUrl+data.picture);// 图片显示
			$("#image_show").show();// 显示
			$("#img_del").show();// 删除图片按钮显示
			
			if(itemCategoryId == 17){
				$("#promotionOriginalCost").val(data.promotionOriginalCost);
				$("#promotionBeginDate").val(data.promotionBeginDate);
				$("#promotionEndDate").val(data.promotionEndDate);
				$("#baopin").toggle();
			}
			} else {
			msg("数据获取失败!", 2, 2);
		}
	}, "json");

}

/** 校验* */
$("#frm-edit-item").validate({
	rules : {
		"itemName" : {
			required : true,
			maxlength : 50,
		},
		"price" : {
			required : true,
			number : true
		},
		"commodityExplain" : {
			maxlength : 50,
		},
		"duration" : {
			required : true
		},
		"itemCategoryName" : {
			required : true
		},
		"isShow" : {
			required : true
		},
		"picture" : {
			required : true
		}

	},
	messages : {
		"itemName" : {
			required : "商品名称不能为空!",
			maxlength : "商品名称不能超过50个字符!",
		},
		"price" : {
			required : "商品价格不能为空!",
			number : "请输入正确的商品价格!"
		},
		"commodityExplain" : {
			maxlength : "商品描述不能超过50个字符!",

		},
		"duration" : {
			required : "洗衣时长不能为空!"
		},
		"itemCategoryName" : {
			required : "请选择该商品所属的类别!"
		},
		"isShow" : {
			required : "请选择该商品是否显示!"
		},
		"picture" : {
			required : "请选择上传一张商品图片!"
		}

	}
});


/** ***********************************js方法声明****************************************************************** */

/** ***********************************js方法执行****************************************************************** */

$(document).ready(function() {

	initItem();// 加载页面编辑信息

	getItemCateGroryData();// 加载类别信息

	$("button[name='btn-save']").click(function() {
		if ($("#frm-edit-item").valid()) {
			if($("#url").val()!=""&&$("#url").val()!=null){
				edit("item/edit",$("#frm-edit-item"),"item/init-list?unfolder=item-init-list"+getReturnURLparam()); // 校验通过调用新增方法
			}else{
				alert("请上传图片!");
			}
			
		}
	});

	/**有用户图片不能点击上传 必须先删除**/
	$("#image-button").live("mouseover", function() {
		if ($("#url").val() != "")
			$("#image").uploadify('disable', true);//禁用上传按钮
	});

});
/** ***********************************js方法执行****************************************************************** */
