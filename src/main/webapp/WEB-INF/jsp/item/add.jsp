<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../index/index-top.jsp"%>
<%@ include file="../index/index-left.jsp"%>
<!--autocomplete-->
<link rel="stylesheet" href="css/item/jquery.autocomplete.css" type="text/css"></link>
<link rel="stylesheet" href="css/item/styles.css" type="text/css"></link>
<!--autocomplete-->
<link rel="stylesheet" href="css/item/uploadify.css" type="text/css"></link>
<link rel="stylesheet" href="css/item/default.css" type="text/css"></link>
<!-- page content -->
<div id="main-content">
	<!-- title begin -->
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="page-title">商品管理</h3>
				<ul class="breadcrumb">
					<li><a href="#"> 首页 </a> <span class="divider"> / </span></li>
					<li><a href="#"> 商品管理 </a> <span class="divider"> / </span></li>
					<li class="">新增商品信息</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- title ends -->
	<div class="row-fluid">
		<div class="span12">
			<!-- form begin -->
			<form id="frm-add-item" class="form-horizontal">
				<!-- first widgets begin -->
				<div class="widget-body paddingAll">
					<div class="row-fluid">
						<div class="span12">
							<!-- BEGIN SAMPLE FORMPORTLET-->
							<div class="widget blue">
								<div class="widget-title">
									<h4>
										<i class="icon-reorder"> </i> 商品信息
									</h4>
								</div>
								<div class="widget-body">
									<div class="row-fluid">
										<div class="span6">
											<label class="control-label"> 商品名称： </label>
											<div class="controls positionfu">
												<input type="text" class="input-block-level" id="itemName" name="itemName" placeholder="请输入商品名称">
											</div>
										</div>


										<div class="span6">
											<label class="control-label"> 价格： </label>
											<div class="controls positionfu">
												<input class="input-block-level" id="price" type="number" name="price" placeholder="请输入商品价格（单位：元）">
											</div>
										</div>

									</div>



									<div class="kongge"></div>
									<div class="row-fluid">
										<div class="span6">
											<label class="control-label"> 商品说明： </label>

											<div class="controls positionfu">
												<input type="text" class="input-block-level" id="commodityExplain" name="commodityExplain"
													placeholder="请输入商品说明">

											</div>
										</div>
										<div class="span6">
											<label class="control-label"> 洗衣时长： </label>
											<div class="controls">
												<select id="duration" name="duration"  class="input-block-level">
													<option value="">请选择洗衣时长</option>
													<option value="1天">1天</option>
													<option value="2天">2天</option>
													<option value="3天">3天</option>
													<option value="4天">4天</option>
													<option value="5天">5天</option>
													<option value="6天">6天</option>
													<option value="7天">7天</option>
												</select>
											</div>
										</div>
									</div>
									<div class="kongge"></div>

									<div class="row-fluid">



										<div class="span6">
											<label class="control-label"> 商品类别： </label>
											<div class="controls positionfu">
												<input placeholder="请点击选择商品所属的类别" readonly="readonly" type="text" class="input-block-level"
													id="itemCategoryName" name="itemCategoryName"> <input type="hidden" id="itemCategoryId"
													name="itemCategoryId">
												<%@ include file="../index/index-itemCategory-tree.jsp"%>
											</div>

										</div>
										<div class="span6">
											<label class="control-label"> 是否显示： </label>
											<div class="controls positionfu">
												<select id="isShow" name="isShow" class="input-block-level" tabindex="1">
													<option value="">请选择是否显示</option>
													<option value="1">显示</option>
													<option value="0">隐藏</option>
												</select>
											</div>
										</div>
									</div>
									<div class="kongge"></div>
									<div class="row-fluid">
										<div class="span6">
											<label class="control-label"> 商品图片： </label>
											<div class="controls">
												<input id="image" type="file" name="image"> <img style="display: none" id="image_show" src=""
													width="300"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img style="display: none;" alt="删除" id="img_del"
													src="css/item/98fff0986f_24.png"> <input type="hidden" name="picture" id="url" />
											</div>
										</div>
										<div class="span6">
											<label class="control-label"> 商品单位： </label>
											<div class="controls">
												<input id="itemUnit" name="itemUnit" type="text" placeholder="请输入商品单位（单位：件or袋）" class="input-block-level" />
											</div>

										</div>
									</div>


								</div>
							</div>
						</div>
					</div>
					<!-- first widgets begin -->
					<div class="text-right">
						<button name="btn-save" class="btn btn-success" type="button">
							<i class="icon-check"> </i> 保 存
						</button>
						<button id="btn-cancel" class="btn btn-warning" type="reset">
							<i class="icon-reply-all"> </i> 重 置
						</button>
						<button onclick="javascript:window.location.href='localhost:8081/item/init-list?unfolder=item-init-list'+getReturnURLparam()"
							class="btn " type="button">
							<i class=" icon-share"> </i> 返 回
						</button>
					</div>
					<!-- second widgets ends -->
				</div>
			</form>
		</div>
		<!-- form ends -->
	</div>
</div>
<%@ include file="../index/index-footer.jsp"%>
<!--autocomplete-->
<script type="text/javascript" src="js/item/jquery.autocomplete.js"></script>
<!--autocomplete-->
<!--file upload-->
<script type="text/javascript" src="js/item/jquery.uploadify.min.js"></script>

<script type="text/javascript" src="js/item/common.js"></script>
<script type="text/javascript" src="js/item/base.js"></script>
<script type="text/javascript" src="js/item/add.js"></script>
