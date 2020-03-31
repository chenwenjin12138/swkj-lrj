/*************************************全局对象设置*******************************************************************/

/** ***********************************全局对象设置****************************************************************** */

/** ***********************************js方法声明****************************************************************** */

/** 校验* */
$("#frm-add-item").validate({
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
		},
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
		},
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

/** ***********************************js方法声明****************************************************************** */

/** ***********************************js方法执行****************************************************************** */

$(document).ready(function() {

	$("button[name='btn-save']").click(function() {
		if ($("#frm-add-item").valid()) {

			if ($("#url").val() != "" && $("#url").val() != null) {
				add("item/add", $("#frm-add-item"), "item/init-list?unfolder=item-init-list"+getReturnURLparam()); // 校验通过调用新增方法
			} else {
				alert("请上传图片!");
			}

		}
	});

});
/** ***********************************js方法执行****************************************************************** */
