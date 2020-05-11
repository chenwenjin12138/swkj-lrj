<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../index/index-top.jsp"%>
<%@ include file="../index/index-left.jsp"%>
<div id="main-content">
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="page-title" style="font-family: "微软雅黑 ";">商品管理</h3>
				<ul class="breadcrumb">
					<li><a href="javascript:"> 首页 </a> <span class="divider"> / </span></li>
					<li><a href="javascript:"> 商品管理 </a> <span class="divider"> / </span></li>
					<li class="">商品列表</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget green">
					<div class="widget-title ">
						<h4>
							<i class="icon-reorder"> </i> 条件查询
						</h4>
						<span class="tools"> <a href="javascript:;" class="icon-chevron-down"> </a> </span>
					</div>
					<div class="widget-body">
						<!-- BEGIN FORM-->
						<form class="form-horizontal" method="post" id="item_query" style="margin-bottom: 0px;">
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"> 商品名: </label>
										<div class="controls controls-row">
											<input placeholder="请输入商品名" class="input-block-level" type="text" name="itemName" id="itemName" />
										</div>
									</div>
								</div>
								<div class="span6" style="display: none;">
									<div class="control-group">
										<label class="control-label"> 商品状态: </label>
										<div class="controls controls-row">
											<select class="input-block-level" name="isShow" id="isShow">
												<option value="">请选择</option>
												<option selected="selected" value="1">显示</option>
												<option value="0">隐藏</option>
											</select>
										</div>
									</div>
								</div>
								<!-- <div class="span6">
									<div class="control-group">
										<label class="control-label"> 商品编号: </label>
										<div class="controls controls-row">
											<input placeholder="请输入商品编号" class="input-block-level" type="text" name="appItemId" id="appItemId" />
										</div>
									</div>
								</div> -->
							</div>


							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label"> 商品类别: </label>
										<div class="controls positionfu">
											<input placeholder="请点击选择商品所属的类别" readonly="readonly" type="text" class="input-block-level"
												id="itemCategoryName" name="itemCategoryName"> <input type="hidden" id="itemCategoryId"
												name="itemCategoryId">
											<%@ include file="/template/template-itemCategory-tree.jsp"%>
										</div>
									</div>
								</div>


							</div>




							<div class="row-fluid">
								<div class="span12">
									<div class="control-group">
										<div class="text-right">
											<button id="search_item" class="btn  btn-primary" type="button">
												<i class="icon-search"> </i> 搜索
											</button>
											<button onclick="javascript:$('#itemCategoryId').val(null);" class="btn  btn-primary" type="reset">重置</button>
										</div>
									</div>
								</div>
							</div>
						</form>
						<!-- ENDS FORM-->
					</div>
				</div>
			</div>
		</div>
		<div class="kongge"></div>
		<div class="row-fluid">
			<div class="span12">
				<div class="control-group">
					<div class="text-left">

						<a href="javascript:location.href='<%=basePath%>item/init-add?unfolder=item-init-list'+'&'+$('#item_query').serialize();"
							role="button" class="btn" data-toggle="modal"> <i class="icon-plus"> </i> 新增 </a>
						<a href="javascript:location.href='<%=basePath%>item/init-addHot?unfolder=item-init-list'+'&'+$('#item_query').serialize();"
							role="button" class="btn" data-toggle="modal"> <i class="icon-plus"> </i> 新增爆品 </a>	
							 <a style="display: none;" id="invoke" href="#"
							role="button" class="btn btn-success" data-toggle="modal">显示 </a> <a id="forbidden" href="#" role="button"
							class="btn btn-warning" data-toggle="modal">删除</a> <br>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget blue" id="grid_array_parent">
					<div id="grid_array"></div>
				</div>
			</div>
		</div>
	</div>
	<!--商品图片展示模态框-->
	<div id="image_modal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3"
		aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close martop7" data-dismiss="modal" aria-hidden="true">×</button>
			<h4 id="myModalLabel1">商品图片</h4>
		</div>
		<div align="center">
			<img align="middle" id="image_show" src="">
		</div>
	</div>
	
	<!--设置爆品模态框-->
	<div id="hot_model" style="width:650px;" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel3" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close martop7" data-dismiss="modal" aria-hidden="true">×</button>
			<h4 id="myModalLabel1">设置爆品信息</h4>
		</div>
		<div class="modal-body">

			<form id="frm-itemHot" class="form-horizontal" style="margin-bottom: 0px;">
				<%--

				<input type="hidden" v-model="account.coupon_id"> 

				--%>
				<div class="row-fluid">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">促销产品原价:</label>
							<div class="controls controls-row">
								<input class="input-block-level" type="text" id="promotionOriginalCost"  name="promotionOriginalCost" />
								<input class="input-block-level" type="hidden" id="appItemId2"  name="appItemId2"/>
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid" >
					<div class="span12">
						<div class="control-group">
							<label class="control-label">促销开始日期:</label>
							<div class="controls controls-row">
								<input class="input-block-level"  class="input-block-level"  id="promotionBeginDate" type="text" name="promotionBeginDate"
									onClick="WdatePicker({dateFmt:'yyyy-M-d '})" readonly="readonly"  />
								<!--v-model="coupon.starting_time"-->
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid" >
					<div class="span12">
						<div class="control-group">
							<label class="control-label">促销结束日期:</label>
							<div class="controls controls-row">
								<input class="input-block-level" id="promotionEndDate" type="text" name="promotionEndDate"
									onClick="WdatePicker({dateFmt:'yyyy-M-d '})" readonly="readonly" />
								<!-- v-model="coupon.expiration_time" -->
							</div>
						</div>
					</div>
				</div>
			</form>



		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" id="" aria-hidden="true">
				<i class="icon-remove"></i> 取消
			</button>
			<button class="btn btn-primary" id="saveHot">
				<i class="icon-ok-sign"></i> 确定
			</button>
		</div>
	</div>
</div>
<%@ include file="/template/template-footer.jsp"%>
<!--autocomplete-->
<script type="text/javascript" src="<%=basePath%>assets/autocomplete/jquery.autocomplete.js">
	
</script>
<link rel="stylesheet" href="<%=basePath%>assets/autocomplete/jquery.autocomplete.css" type="text/css">
</link>
<link rel="stylesheet" href="<%=basePath%>assets/autocomplete/styles.css" type="text/css">
</link>
<!--autocomplete-->
<script type="text/javascript" src="<%=basePath%>business-js/item/common.js"></script>
<script type="text/javascript" src="<%=basePath%>business-js/base.js"></script>
<script src="<%=basePath%>assets/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>business-js/item/list.js"></script>
<script type="text/javascript">
	/**模态框自适应**/
	$("#hot_model").find("div[class='modal-body']").css("max-height", $(window).height() - 220 + "px");
	window.addEventListener("resize", function() {
		$("#hot_model").find("div[class='modal-body']").css("max-height", $(window).height() - 220 + "px");
	});
</script>