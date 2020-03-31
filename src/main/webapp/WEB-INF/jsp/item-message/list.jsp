<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../index/index-top.jsp"%>
<%@ include file="../index/index-left.jsp"%>
<div id="main-content">
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="page-title" style="font-family: "微软雅黑 ";">商品管理</h3>
				<ul class="breadcrumb">
					<li><a href="javascript:"> 首页 </a> <span class="divider">
							/ </span>
					</li>
					<li><a href="javascript:"> 商品管理 </a> <span class="divider">
							/ </span>
					</li>
					<li class="">商品公告</li>
				</ul>
			</div>
		</div>
		
	<div class="row-fluid">
			<div class="span12">
				<div class="control-group">
					<div id="" class="text-left">
						<a @click="addMsg" role="button" class="btn" data-toggle="modal"> <i class="icon-plus"></i> 新增</a>&nbsp;<a
							v-on:click="deleteChecked" href="#" role="button" class="btn btn-danger" data-toggle="modal"><i
							class="icon-remove"></i>删除</a><br>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">

				<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper" role="grid">

					<table class="table table-striped table-bordered bootstrap-datatable datatable dataTable" id="DataTables_Table_0"
						aria-describedby="DataTables_Table_0_info">
						<thead>
							<tr role="row">
								<th hidden="hidden" align="center" class="center" role="columnheader" tabindex="0" style="width:6%;text-align: center;" tabindex="0">ID</th>
								<th role="columnheader" style="width:5%;text-align: center;"><input id="" v-on:click="checkAll"
									type="checkbox"></th>
								<th align="center" class="center" role="columnheader" tabindex="0" style="width:7%;text-align: center;">是否启用</th>
								<th align="center" class="center" role="columnheader" tabindex="0" style="width:10%;text-align: center;">商品类别</th>
								<th align="center" class="center" role="columnheader" tabindex="0" style="width:20%;text-align: center;">公告信息</th>
								<th align="center" class="center" role="columnheader" tabindex="0" style="width:20%;text-align: center;">创建时间</th>								
								<th align="center" class="center" role="columnheader" tabindex="0" style="width:12%;text-align: center;">操作</th>
							</tr>
						</thead>

						<tbody id="dataList" hidden="true" role="alert" aria-live="polite" aria-relevant="all">
							<tr v-for="item in items">
								<!-- <td style="text-align: center;" hidden="true" class="center">{{item.itemMessageId}}</td> -->
								<td hidden="hidden" style="text-align: center;" class="center">{{$index+1}}</td>
								<td style="text-align: center;" class="center"><input v-model="checked[$index]" type="checkbox"></td>
								<td style="text-align: center;" class="center ">
								<template v-if="item.active==1">
								<span class='label label-success label-font'>启用</span>
								</template>
								<template v-else>
								<span class='label label-warning label-font'>禁用</span>
								</template>
								</td>
								<td style="text-align: center;" class="center">{{item.itemCategoryName}}
									<input type="hidden" value="{{item.itemCategoryId}}">
								</td>
								<td style="text-align: center;" class="center ">{{item.itemMessage}}</td>
								<td style="text-align: center;" class="center ">{{item.createTime.dateFormat()}}</td>
								
								<td style="text-align: center;" class="center "><button class="btn btn-small btn-info"
										systemAdminId="{{item.itemMessageId}}" @click="eidtMsg(item)" name="role-edit">编辑</button>
									<button class="btn btn-small btn-danger" @click="deleteOne(item.itemMessageId)" name="role-delete">删除</button>
								</td>

							</tr>

						</tbody>
					</table>
				</div>

			</div>


		</div>
	</div><!-- style="width:1000px;height: 900px;" -->
	<div id="coupon_model"  class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel3" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close martop7" data-dismiss="modal" aria-hidden="true">×</button>
			<h4 id="myModalLabel1">{{operate}}商品公告</h4>
		</div>
		<div class="modal-body">

			<form id="frm-account" class="form-horizontal" style="margin-bottom: 0px;"><%--

				<input type="hidden" v-model="account.coupon_id"> 

				--%><div class="row-fluid">
					<div class="span12">
						 <div class="control-group">  
							<label class="control-label">商品类别:</label>
							 
							<div class="controls controls-row">
								<!-- <input class="input-block-level" type="text" v-model="coupon.denomination" name="denomination" /> -->
								<select v-model="selected" class="input-block-level">
									<option v-for="option in options" v-bind:value="option.itemCategoryId">
										{{ option.itemCategoryName }}
									</option>
								</select> 
							 <!-- <span>Selected: {{ selected}}</span>  -->
							<input type="hidden" v-model="itemmsg.itemCategoryId" v-bind:value="selected">
							</div>
						</div> 
					</div>
				</div>

			<div class="row-fluid">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">状态:</label>
							<div class="controls controls-row">
								<select v-model="itemmsg.active" name="active" class="input-block-level">
									<option selected="selected" value="1">启用</option>
									<option value="0">禁用</option>
								</select>
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">公告内容:</label>
							<div class="controls controls-row">
								<textarea type="text" v-model="itemmsg.itemMessage" class="input-block-level"
									name="itemMessage"></textarea>
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
			<button @click="save()" class="btn btn-primary">
				<i class="icon-ok-sign"></i> 确定
			</button>
		</div>
	</div>


	
	
</div>
<%@ include file="../index/index-footer.jsp"%>
<!--autocomplete-->
<script type="text/javascript" src="js/item-message/vue.js"></script>
<script type="text/javascript" src="js/item-message/list.js"></script>


