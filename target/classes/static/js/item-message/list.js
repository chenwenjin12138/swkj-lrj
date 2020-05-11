var vm = new Vue({
	el : '#main-content',
	data : {
		items : null,
		checked : null,
		ids : null,
		operate:"",
		options:null,
		selected:"",
		itemmsg : {
			itemMessageId : null,
			itemCategoryId : null,
			itemCategoryName : null,
			itemMessage : null,
			active : null,
			createTime : null
		}
	},
	methods : {
		addMsg : function() {
			vm.operate = "新增";
			vm.itemmsg = null;
			getOptions();
			$("#coupon_model").modal("show");
			
		},
		save: function(event){
			if ($("#frm-account").valid()) {
				if(vm.operate == "新增"){
					var url = 'item-message/add';									
				}else if(vm.operate =="编辑"){
					var url = 'item-message/edit';
				}
				submit(vm.itemmsg,url);	
			}
		},
		eidtMsg: function(item){
			vm.operate = "编辑";
			vm.itemmsg = $.extend(true,{},item);// 拷贝对象
			getOptions();
			vm.selected=vm.itemmsg.itemCategoryId;
			$("#coupon_model").modal("show");
		},
		deleteOne: function(id){
			vm.ids = new Array();
			vm.ids.push(id);
			del(vm.ids);
		},
		deleteChecked: function(){
			vm.ids = new Array();
			for(var i=0;i<vm.items.length;i++){
				if(vm.checked[i]){
					vm.ids.push(vm.items[i].itemMessageId);
				}
			}
			if(vm.ids.length == 0){
				alert("请至少选择一条");
			}else{
				del(vm.ids);
			}
		},
		checkAll:function(event){
			for(var i=0;i<vm.checked.length;i++){
				vm.checked.$set(i,$(event.target).prop("checked"));
			}
		}
	}
})

/**删除所选**/
function del(ids) {
	var a = window.confirm("确定删除所选吗？");
	if (a) {
		$.ajax({
			type : 'post',
			data : {
				ids : ids
			},
			async : true,
			url : basePath + 'item-message/del',
			dataType : 'json',
			success : function(data) {
				if (data.flag == 0) {
					list();
				}
			},
			error : function(data) {
				console.log(data);
				alert("程序异常");
			}
		})
	}
}

/**获取所有**/
function list(){
	/** 弹出加载层* */
	var index = layer.load('数据正在努力加载中...', 3);
	$.ajax({
		type:'post',
		async:true,
		url:basePath+"item-message/list",
		dataType:'json',
		success:function(data){
			grid(data);
			/** 关闭加载层* */
			layer.close(index);
		},
		error:function(data){
			alert("程序异常！");
		}
	})
}
/**
 * 绘制表格
 * @param data
 */
function grid(data){
	vm.items = data;
	/**
	 * checkbox
	 */
	var arr = new Array();
	/**
	 * 默认下不选中
	 */
	for(var i=0;i<vm.items.length;i++){
		arr.push(false);
	}
	vm.checked = arr;
	$("#dataList").show();
}

/**得到商品类别名称和Id**/
function getOptions(){
	
	$.ajax({
		type:'post',
		async:true,
		url:basePath+"item-message/getCategoryName",
		dataType:'json',
		success:function(data){
			vm.options = data;		
		},
		error:function(data){
			alert("程序异常！");
		}
	})
}

/**数据提交方法**/
function submit(data,url){
	$.ajax({
		type:'post',
		data:data,
		async:true,
		url:basePath+url,
		dataType:'json',
		success:function(data){
			alert(data.message);
			if(data.flag==0){
				$("#coupon_model").modal("hide");
				list();	// 页面初始化加载列表
			}
		},
		error:function(data){
			alert(data.message);
		}
	})
}

$(document).ready(function() {
	// 页面初始化加载列表
	list();	
	
	$("#frm-account").validate({
		rules : {
			"itemCategoryName" : {
				required : true
			},
			"active":{
				required:true
			},
			"itemMessage" : {
				required : true
			}

		},
		messages : {
			"itemCategoryName" : {
				required : "请选择商品类别!"
			},
			"active":{
				required:"请选择是否启用!"
			},
			"itemMessage" : {
				required : "商品公告不能为空!"
			}
		}
	});


});