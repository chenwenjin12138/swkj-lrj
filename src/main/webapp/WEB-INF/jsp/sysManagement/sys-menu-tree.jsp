<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!--角色权限菜单树-->
<div id="authorityTree" style="display:none;position:absolute;z-index:999;width:100%;border:1px solid #3975B7;background-color:#fff">
	<div class="modal-header">
		<button name='close_authority_modal' type="button" class="close martop7" data-dismiss="modal" aria-hidden="true">×</button>
		<h4 id="myModalLabel1">菜单权限</h4>
	</div>
	<div class="modal-body">
		<ul id="tree_authority" class="ztree"></ul>
	</div>
	<div class="modal-footer">
		<button name='close_authority_modal'  class="btn" data-dismiss="modal" id="" aria-hidden="true" type="button">
			<i class="icon-remove"></i> 取消
		</button>
		<button data-dismiss="modal" id="sureSelectAuthority" class="btn btn-primary" type="button">
			<i class="icon-ok-sign"></i> 确定
		</button>
	</div>
</div>