<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../index/index-top.jsp"%>
<%@ include file="../index/index-left.jsp"%>
<!-- BEGIN PAGE -->
<div id="main-content">
	<!-- BEGIN PAGE CONTAINER-->
	<div class="container-fluid">
		<!-- BEGIN PAGE HEADER-->
		<div class="row-fluid">
			<div class="span12">
				<!-- BEGIN THEME CUSTOMIZER-->

				<!-- END THEME CUSTOMIZER-->
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">商品分类管理</h3>
				<ul class="breadcrumb">
					<li><a href="javascript:">首页</a> <span class="divider">/</span>
					</li>
					<li><a href="javascript:">商品管理</a> <span class="divider">/</span>
					</li>
					<li class="">商品分类管理</li>

				</ul>
				<!-- END PAGE TITLE & BREADCRUMB-->
			</div>
		</div>
		<!-- END PAGE HEADER-->
		<!-- BEGIN PAGE CONTENT-->
		<div class="row-fluid">
			<div class="span3" class="zTreeDemoBackground left">

				<div class="widget green">
					<div class="widget-title">
						<h4>
							<i class=" icon-indent-left"></i> 商品分类
						</h4>
						<span class="tools"> </span>
					</div>

					<div class="widget-body">
						<div class="actions">
							<a class="btn btn-small btn-success" id="add_new_category"
								href="javascript:;"><i class="icon-plus"></i> 新 增</a>
						</div>
						<div class="kongge-sai"></div>

						<ul id="item-category-tree" name="item-category-tree"
							class="ztree"></ul>
					</div>
					<div class="kongge"></div>
				</div>

			</div>
			<div class="span9" id="edit-area" style="display: none;">
				<div class="widget purple">
					<div class="widget-title">
						<h4>
							<i class=" icon-indent-left"></i>
							<txt id="showTxt">编辑</txt>
						</h4>
						<span class="tools"> </span>
					</div>
					<div class="widget-body">

						<div class="actions">
							<a class="btn btn-small btn-success" id="categorySave"
								href="javascript:;"><i class="icon-check"></i> 保 存</a> <a
								class="btn btn-small btn-warning" id="reset" href="javascript:;"><i
								class="icon-reply-all"></i> 重置</a>
						</div>
						<div class="space10"></div>
						<ul id="tree_2" class="tree">
							<li><a data-value="Bootstrap_Tree" data-toggle="branch"
								class="tree-toggle" data-role="branch" href="#"> </a>
								<form id="item-category-form" class="form-horizontal">
									<fieldset>
										<input type="hidden" id="appItemCategoryId"
											name="appItemCategoryId" value="">
										<div class="control-group">
											<label class="control-label" for="focusedInput">分类名称名称：</label>
											<div class="controls">
												<input class="input-block-level" type="text" id=categoryName
													name="categoryName" value="">
											</div>
										</div>

										<!-- <div class="control-group">
											<label class="control-label" for="focusedInput">分类图片：</label>
											<div class="controls">
												<select class="input-block-level" id="orgType"
													name="org.orgTypeId"></select>
												<input type="file" class="input-block-level"
													id="categrory_pic" name="categrory_pic">

											</div>
										</div> -->



										<div class="control-group">
											<label class="control-label" for="focusedInput">上级类别：</label>
											<div class="controls">
												<input readonly="readonly" class="input-block-level"
													type="text" id="parentName" name="parentName" value="">
												<input readonly="readonly" class="input-block-level"
													type="hidden" id="pid" name="pid" value="">
											</div>
										</div>

										<div class="control-group">
											<label class="control-label">是否显示：</label>
											<div class="controls">
												<select class="input-block-level" id="isShow" name="isShow">
													<option value="1">显示</option>
													<option value="0">不显示</option>
												</select>
											</div>
										</div>
									</fieldset>
								</form>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!-- END PAGE CONTENT-->
	</div>
	<!-- END PAGE CONTAINER-->
</div>
<!-- END PAGE -->
<%@ include file="../index/index-footer.jsp"%>
<style type="text/css">
div#ztreerMenu {
	position: absolute;
	visibility: hidden;
	top: 0;
	background-color: #FFFFFF;
	text-align: left;
	padding: 2px;
}

div#ztreerMenu ul li {
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #DFDFDF;
	width: 64px;
	text-align: center;
}
</style>
<script type="text/javascript" src="js/item-category/list.js"></script>
<!-- END PAGE -->