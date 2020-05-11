<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div id="itemCateGory_Tree_div"
	style="display:none;position:absolute;z-index:999;width:100%;border:1px solid #3975B7;background-color:#fff">
	<div class="modal-header">
		<button id="cancel" type="button" name="cancel" class="close martop7" data-dismiss="modal" aria-hidden="true">×</button>
		<h4 id="myModalLabel1">商品类别：</h4>
	</div>
	<div class="modal-body">
	    输入类别名称查询：<input id="serachVal" type="text">  <button  id="search" class="btn btn-primary" type="button">查找</button>&nbsp;&nbsp;&nbsp;<button  id="reset" class="btn" type="button">重置</button>
		<ul id="itemCateGory_select" name="treeDemo" class="ztree"></ul>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" id="cancel" name="cancel" aria-hidden="true" type="button">
			<i class="icon-remove"></i> 取消
		</button>
		<button data-dismiss="modal" id="commit" class="btn btn-primary" type="button">
			<i class="icon-ok-sign"></i> 确定
		</button>
	</div>
</div>
