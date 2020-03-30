<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>




<!--修改分销收益比模态框-->

<div id="earningsratioModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3"
	aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close martop7" data-dismiss="modal" aria-hidden="true">×</button>
		<h4 id="myModalLabel1">分销收益比修改</h4>
	</div>
	<div class="modal-body">

		<form id="EarningsratioForm" class="form-horizontal" style="margin-bottom: 0px;">
			<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
						<label class="control-label">APP分销收益:</label>
						<div class="controls controls-row">
							<input id="APPEARNINGSRATIO" class="input-block-level" type="text" name="APPEARNINGSRATIO">
						</div>
					</div>
				</div>
			</div>

			<div class="row-fluid">

				<div class="span12">
					<div class="control-group">
						<label class="control-label">商家分销收益:</label>
						<div class="controls controls-row">
							<input class="input-block-level" id="BUSSEARNINGSRATIO" type="text" name="BUSSEARNINGSRATIO">
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
		<button id="submit_app" class="btn btn-primary">
			<!-- data-dismiss="modal" -->
			<i class="icon-ok-sign"></i> 确定
		</button>
	</div>
</div>



<!--修改密码模态框-->
<div id="changePasswordModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3"
	aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close martop7" data-dismiss="modal" aria-hidden="true">×</button>
		<h4 id="myModalLabel1">后台用户密码修改</h4>
	</div>
	<div class="modal-body">

		<form id="changePasswordForm" class="form-horizontal" style="margin-bottom: 0px;">
			<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
						<label class="control-label">旧密码:</label>
						<div class="controls controls-row">
							<input id="orginPassword" type="password" name="orginPassword">
						</div>
					</div>
				</div>
			</div>

			<div class="row-fluid">

				<div class="span12">
					<div class="control-group">
						<label class="control-label">新密码:</label>
						<div class="controls controls-row">
							<input id="newPassword" type="password" name="newPassword">
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
						<label class="control-label">重复新密码:</label>
						<div class="controls controls-row">
							<input id="rNewPassword" type="password" name="rNewPassword">
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
		<button id="change_sure" class="btn btn-primary">
			<!-- data-dismiss="modal" -->
			<i class="icon-ok-sign"></i> 确定
		</button>
	</div>
</div>
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div id="footer">
	<a></a>&nbsp;© 2015 建议使用谷歌内核浏览器 建议使用大屏电脑
</div>

<!-- END FOOTER -->

<!-- include JS -->
<%@ include file="/index/template-js.jsp"%>
</body>
<!-- END BODY -->
</html>
