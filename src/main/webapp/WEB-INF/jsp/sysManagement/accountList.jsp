<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../index/index-top.jsp"%>
<%@ include file="../index/index-left.jsp"%>
<div id="main-content">
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="page-title" style="font-family: "微软雅黑";">帐号管理</h3>
				<ul class="breadcrumb">
					<li><a href="javascript:">首页</a><span class="divider">/</span>
					</li>
					<li><a href="javascript:">帐号管理</a><span class="divider">/</span>
					</li>
					<li class="">帐号列表</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget green">
					<div class="widget-title ">
						<h4>
							<i class="icon-reorder"></i>条件查询
						</h4>
						<span class="tools"><a href="javascript:;" class="icon-chevron-down"></a> </span>
					</div>
					<div class="widget-body">
						<!-- BEGIN FORM-->
						<form class="form-horizontal" method="post" id="admin_query" style="margin-bottom: 0px;">
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label class="control-label">用户名:</label>
										<div class="controls controls-row">
											<input placeholder="请输入用户名" class="input-block-level" type="text" name="adminName" id="adminName" />
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label class="control-label">用户状态:</label>
										<div class="controls controls-row">
											<select class="input-block-level" name="active" id="active">
												<option value="">请选择</option>
												<option value="1">启用</option>
												<option value="0">禁用</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">

								<div class="span6">
									<div class="control-group">
										<label class="control-label">角色:</label>
										<div class="controls controls-row">
											<select class="input-block-level" name="sysAdminRoleType" id="sysAdminRoleType">
												<option value="">请选择</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<div class="control-group">
										<div class="text-right">
											<button id="search_admin" class="btn btn-primary" type="button">
												<i class="icon-search"></i> 搜索
											</button>
											<button id="reset" class="btn btn-primary" type="reset">重置</button>
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
						<a
							href="javascript:location.href='toAddAccount'"
							role="button" class="btn" data-toggle="modal"> <i class="icon-plus"></i> 新增</a>
						<a id="invoke" href="#"
							role="button" class="btn btn-success" data-toggle="modal">启用</a>
						<a id="forbidden" href="#" role="button"
							class="btn btn-warning" data-toggle="modal">禁用</a><br>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget blue" id="grid_array_parent">

					<table width="100%">
						<caption><img src="images/sysManagement/table.png" width="15px">账号列表</caption>
						<thead align="center" border="1px">
							<tr>
								<td>序号</td>
								<td>选择</td>
								<td>状态</td>
								<td>用户名</td>
								<td>角色</td>
								<td>创建时间</td>
								<td>操作</td>
							</tr>
						</thead>
						<tbody  align="center" border="0.5px">
						<c:forEach items="${accountList}" var="account">
							<tr >
								<td>1</td>
								<td><input type='checkbox'/></td>
								<td>${account.active}</td>
								<td>${account.adminName}</td>
								<td>${account.sysAdminRoles}</td>
								<td>${account.createTime}</td>
								<td><a href="toEditSysUser?sysAdminId=${account.sysAdminId}"><button id="btn_toEditAccount" class="btn btn-small btn-info">编辑</button></a></td>
							</tr>
						</c:forEach>
						</tbody>
						<tfoot></tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../index/index-footer.jsp"%>
<script type="text/javascript" src="js/sysManagement/common.js"></script>
<script type="text/javascript" src="js/sysManagement/base.js"></script>
<script type="text/javascript" src="js/sysManagement/sysUser_list.js"></script>