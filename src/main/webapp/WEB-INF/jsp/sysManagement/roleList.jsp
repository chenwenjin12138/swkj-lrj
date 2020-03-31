<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<!-- <div id="theme-change" class="hidden-phone">
					<i class="icon-cogs"></i> <span class="settings"> <span
						class="text">Theme Color:</span> <span class="colors"> <span
							class="color-default" data-style="default"></span> <span
							class="color-green" data-style="green"></span> <span
							class="color-gray" data-style="gray"></span> <span
							class="color-purple" data-style="purple"></span> <span
							class="color-red" data-style="red"></span> </span> </span>
				</div> -->
				<!-- END THEME CUSTOMIZER-->
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title">角色设置</h3>
				<ul class="breadcrumb">
					<li><a href="javascript:">首页</a> <span class="divider">/</span>
					</li>
					<li><a href="javascript:">角色管理</a><span class="divider">/</span>
					</li>
					<li class="">角色设置</li>
					<!-- <li class="pull-right search-wrap">
						<form action="search_result.html" class="hidden-phone">
							<div class="input-append search-input-area">
								<input class="" id="appendedInputButton" type="text">
								<button class="btn" type="button">
									<i class="icon-search"></i>
								</button>
							</div>
						</form></li> -->
				</ul>
				<!-- END PAGE TITLE & BREADCRUMB-->
			</div>
		</div>
		<!-- END PAGE HEADER-->
		<!-- BEGIN PAGE CONTENT-->
		<div id="page-wraper">
			<div class="row-fluid">
				<div class="span12">
					<form id="frm-add-role" class="form-vertical" method="post"
						action="#">
						<div class="row-fluid">
							<div class="span6">
								<div class="control-group">
									<label class="control-label">角色名称:</label>
									<div class="controls controls-row">
										<input type="text" class="input-block-level"
											placeholder="请输入角色名称" name="roleName" id="roleName">
										<input type="hidden" id="sysRoleId" name="sysRoleId">
									</div>
								</div>
							</div>
							<div class="span6">
								<div class="control-group">
									<label class="control-label">角色描述:</label>
									<div class="controls controls-row positionfu">
										<input type="text" class="input-block-level"
											placeholder="请输入角色描述" name="roleDescride" id="roleDescride" />
									</div>
								</div>
							</div>

						</div>

						<div class="row-fluid">

							<div class="span6">
								<div class="control-group">
									<label class="control-label">角色状态:</label>
									<div class="controls controls-row positionfu">
										<!-- <input type="text" class="input-block-level"
											placeholder="请输入角色描述" name="" id="" /> -->
										<select id="active" class="input-block-level" name="active">
											<option id="" value="">请选择角色状态</option>
											<option id="" value="1">启用</option>
											<option id="" value="0">禁用</option>
										</select>
									</div>
								</div>
							</div>

							<div class="span6">
								<div class="control-group">
									<label class="control-label">角色授权:</label>
									<div class="controls controls-row positionfu">
										<input type="text" class="input-block-level"
											placeholder="请单击选择权限" name="role_right" readonly="readonly"
											id="authority_select" />
										<%@ include file="../sysManagement/sys-menu-tree.jsp"%>
									</div>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div align="right" class="span12">
								<div class="controls controls-row">
									<button name="btn-save" class="btn  btn-success" type="button"
										id="">
										 保存
									</button> 
									<button onclick="cleanInput()"  class="btn  btn-primary" type="reset">
										 重置
									</button> 
								</div>
							</div>
						</div>
						<hr style=" height:2px;border:none;border-top:2px dotted #185598;" />
						<!--横线-->
						<div class="row-fluid">
							<div class="span12">
								<div class="controls controls-row">

									<button class="btn  btn-success" type="button" id="invoke">启用</button>
									<button class="btn  btn-warning" type="button" id="forbidden">禁用</button>
								</div>
							</div>
						</div>
					</form>
					<!-- BEGIN BASIC PORTLET-->
					<div class="widget blue" id="grid_array_parent">
						<div id="grid_array">
							<table width="100%">
								<caption><img src="images/sysManagement/table.png" width="15px">角色列表</caption>
								<thead align="center" border="1px">
								<tr>
									<td>序号</td>
									<td>选择</td>
									<td>状态</td>
									<td>角色名</td>
									<td>角色描述</td>
									<td>权限</td>
									<td>操作</td>
								</tr>
								</thead>
								<tbody  align="center" border="0.5px">
								<c:forEach items="${roleList}" var="role">
									<tr >
										<td>1</td>
										<td><input type='checkbox'/></td>
										<td>${role.active}</td>
										<td>${role.roleName}</td>
										<td>${role.roleDescride}</td>
										<td>${role.sysRoleAuthoritys}</td>
										<td><button class='btn btn-small btn-info' rowIndex='ui.rowIndxPage' name='role-edit'>编辑</button></td>
									</tr>
								</c:forEach>
								</tbody>
								<tfoot></tfoot>
							</table>
						</div>
					</div>
					<!-- END BASIC PORTLET-->
				</div>
			</div>
		</div>
		<!-- END PAGE CONTENT-->
	</div>
	<!-- END PAGE CONTAINER-->
</div>
<!-- END PAGE -->
<%@ include file="../index/index-footer.jsp"%>
<script type="text/javascript" src="js/sysManagement/base.js"></script>
<script src="js/sysManagement/sysRole_list.js"></script>
