<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../index/index-top.jsp"%>
<%@ include file="../index/index-left.jsp"%>
<!-- page content -->
<div id="main-content">
	<!-- title begin -->
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="page-title">帐号管理</h3>
				<ul class="breadcrumb">
					<li><a href="#"> 首页 </a> <span class="divider"> / </span></li>
					<li><a href="#"> 帐号管理 </a> <span class="divider"> / </span></li>
					<li class="">新增帐号</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- title ends -->
	<div class="row-fluid">
		<div class="span12">
			<!-- form begin -->
			<form id="frm-add-admin" class="form-horizontal">
				<!-- first widgets begin -->
				<div class="widget-body paddingAll">
					<div class="row-fluid">
						<div class="span12">
							<!-- BEGIN SAMPLE FORMPORTLET-->
							<div class="widget blue">
								<div class="widget-title">
									<h4>
										<i class="icon-reorder"> </i> 帐号信息
									</h4>
								</div>
								<div class="widget-body">
									<div class="row-fluid">
										<div class="span6">
											<label class="control-label"> 用户名： </label>
											<div class="controls positionfu">
												<input class="input-block-level" id="adminName"
													name="adminName" placeholder="请输入后台用户名">
											</div>
										</div>
										<div class="span6">
											<label class="control-label"> 用户状态： </label>
											<div class="controls">
												<select id="active" name="active" class="input-block-level"
													tabindex="1">
													<option value="">请选择用户状态</option>
													<option value="1">启用</option>
													<option value="0">禁用</option>
												</select>
											</div>
										</div>
									</div>
									<div class="kongge"></div>
									<div class="row-fluid">
										<div class="span6">
											<label class="control-label">
												角&nbsp;&nbsp;&nbsp;&nbsp;色： </label>
											<div class="controls">
												<select id="sysAdminRoleType" name="sysAdminRoleType"
													class="input-block-level" tabindex="1">
													<!-- <option value="">请选择角色</option>
                                                                <option value="1">管理员</option>
                                                                <option value="2">普通用户</option> -->
												</select>
											</div>
										</div>
										<div class="span6">
											<label class="control-label">
												密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码： </label>
											<div class="controls">
												<input name="adminPassword" type="password" placeholder="请输入密码" class="input-block-level"/>
											</div>
										</div>
									</div>
									<div class="kongge"></div>
									<div class="kongge"></div>
								</div>
							</div>
						</div>
					</div>
					<!-- first widgets begin -->
					<div class="text-right">
						<button id="btn-saveAccount" class="btn btn-success" type="button">
							<i class="icon-check"> </i> 保 存
						</button>
						<button id="btn-cancel" class="btn btn-warning" type="reset">
							<i class="icon-reply-all"> </i> 重 置
						</button>
						<button
							onclick="javascript:window.location.href='toAccountManangemet'"
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
<script type="text/javascript" src="js/sysManagement/common.js"></script>
<script type="text/javascript" src="js/sysManagement/base.js"></script>
<script type="text/javascript" src="js/sysManagement/add.js"></script>
