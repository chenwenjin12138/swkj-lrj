<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- BEGIN CONTAINER -->
<div id="container" class="row-fluid">
	<!-- BEGIN SIDEBAR -->
	<div class="sidebar-scroll">
		<div id="sidebar" class="nav-collapse collapse">
			<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
			<div class="navbar-inverse">
				<form class="navbar-search visible-phone">
					<input type="text" class="search-query" placeholder="Search" />
				</form>
			</div>
			<!-- END RESPONSIVE QUICK SEARCH FORM -->
			<!-- BEGIN SIDEBAR MENU -->
			<ul class="sidebar-menu">

				<li class="sub-menu" id="mainMenu"><a href="/admin/main" class=""><i class="icon-dashboard"></i>
						<span>主菜单</span> </a></li>
				<li style="display: none;" class="sub-menu"><a href="javascript:;" class=""> <i class="icon-cog"></i> <span>后台管理</span>
						<span class="arrow"></span> </a>
					<ul class="sub">
						<li id="admin-init-list"><a class="" href="admin/init-list?unfolder=admin-init-list">帐号管理</a>
						</li>
						<li id="role-init-list"><a class="" href="role/init-list?unfolder=role-init-list">角色管理</a>
						</li>
					</ul></li>
				<li style="display: none;" class="sub-menu"><a href="javascript:;" class=""> <i class="icon-globe"></i> <span>页面管理</span>
						<span class="arrow"></span> </a>
					<ul class="sub">
						<li id="banner-init-list"><a class="" href="banner/init-list?unfolder=banner-init-list">Banner管理</a>
						</li>
						<li id="app-page-init-list"><a class="" href="app-page/init-list?unfolder=app-page-init-list">APP页面管理</a>
						</li>
						<li id="wx-page-init-list"><a class="" href="wx-page/init-list?unfolder=app-page-init-list">微信页面管理</a>
						</li>
					</ul></li>
				<li style="display: none;" class="sub-menu"><a href="javascript:;" class=""> <i class="icon-group"></i> <span>人员管理</span>
						<span class="arrow"></span> </a>
					<ul class="sub">
						<li id="user-init-list"><a class="" href="user/init-list?unfolder=user-init-list">APP用户管理</a>
						</li>
						<li id="staff-init-list"><a class="" href="staff/init-list?unfolder=staff-init-list">APP员工管理</a>
						</li>
						<li id="businessAdmin-init-list"><a class=""
							href="businessAdmin/init-list?unfolder=businessAdmin-init-list">后台商家管理</a></li>
						<li id="leader-init-list"><a class=""
						href="team_laundry/init-leaderList?unfolder=team_laundry-init-leaderList">团队洗衣开通用户</a>
					</ul></li>


				<li style="display: none;" class="sub-menu"><a href="javascript:;" class=""> <i class="icon-gift"></i> <span>优惠管理</span>
						<span class="arrow"></span> </a>
					<ul class="sub">
						<li id="userBalance-init-list"><a class="" href="user/init-list?unfolder=userBalance-init-list">用户金额管理</a>
						</li>
						<li id="coupon-init-list"><a class="" href="coupon/init-list?unfolder=coupon-init-list">红包管理</a>
						</li>
						<li id="twoCode-init-list"><a class="" href="twoCode/init-list?unfolder=twoCode-init-list">二维码管理</a>
						</li>
						<li id="twoCode-init-user-list"><a class="" href="twoCode/init-user-list?unfolder=twoCode-init-user-list">抵用券记录</a>
						</li>
						<li id="qrcodeMemberService-init-list"><a class="" href="qrcodeMemberService/init-list?unfolder=qrcodeMemberService-init-list">月卡二维码管理</a>
						</li>
					</ul></li>


				<li style="display: none;" class="sub-menu"><a href="javascript:;" class=""> <i class="icon-truck"></i> <span>商品管理</span>
						<span class="arrow"></span> </a>
					<ul class="sub">
						<li id="item-category-init-list"><a class=""
							href="item-category/init-list?unfolder=item-category-init-list">商品分类管理</a>
						</li>
						<li id="item-init-list"><a class="" href="item/init-list?unfolder=item-init-list">商品信息管理</a>
						</li>
						<li id="item-message-list"><a class="" href="item-message/init-list?unfolder=item-message-list">商品公告管理</a>
						</li>
					</ul></li>

				<li style="display: none;" class="sub-menu"><a href="javascript:;" class=""> <i class="icon-barcode"></i> <span>订单管理</span>
						<span id="new_order_message" style="display: none;" class="label label-important  label-mini"></span><span
						class="arrow"></span> </a>
					<ul class="sub">
						<li id="order-init-list"><a class="" href="order/init-list?unfolder=order-init-list">订单信息管理</a>
						</li>
						<li id="topup_order-init-list"><a class=""
							href="topup_order/init-list?unfolder=topup_order-init-list">充值订单管理</a>
						</li>
						<li id="memberServiceOrder-init-list"><a class=""
							href="memberServiceOrder/init-list?unfolder=memberServiceOrder-init-list">会员订单管理</a></li>
						<%-- <li id="yearsServiceOrder-init-list"><a class=""
							href="<%=basePath%>yearsServiceOrder/init-list?unfolder=yearsServiceOrder-init-list">包期订单管理</a>
						</li> --%>
						<li id="teamLaundry-init-list"><a class=""
							href="team_laundry/init-list?unfolder=teamLaundry-init-list">团队洗衣订单</a></li>
						<li id="trace-status-init-list">
							<a class="" href="trace-status/init-list?unfolder=trace-status-init-list">追踪状态管理</a>
						</li>
						<li id="wx-mc-init-list">
							<a class="" href="wxMonthCard/init-list?unfolder=wx-mc-init-list">微信月卡订单</a>
						</li>
					</ul></li>

				<li style="display: none;" class="sub-menu"><a href="javascript:;" class=""> <i class="icon-comments-alt"></i>
						<span>信息管理</span><span id="new_feedback_message" style="display: none;" class="label label-important  label-mini"></span><span
						class="arrow"></span> </a>
					<ul class="sub">
						<li id="doubleEleven-init-list"><a class="" href="<%=basePath%>doubleEleven/init-list?unfolder=doubleEleven-init-list">双十一活动</a>
						</li>
						<li id="wxMonthCard-init-list"><a class="" href="<%=basePath%>wxMonthCard/init-list2?unfolder=wxMonthCard-init-list">月卡管理</a>
						</li>
						<li id="feed-back-init-list"><a class="" href="<%=basePath%>feed-back/init-list?unfolder=feed-back-init-list">用户反馈管理</a>
						</li>
						<li id="message-init-list"><a class="" href="<%=basePath%>message/init-list?unfolder=message-init-list">用户消息管理</a>
						</li>
						<li id="app-push-init-list"><a class="" href="<%=basePath%>app-push/init-list?unfolder=app-push-init-list">用户消息推送</a>
						</li>
						<li id="orderComment-init-list"><a class="" href="<%=basePath%>orderComment/init-list?unfolder=orderComment-init-list">用户评论管理</a>
						</li>
					</ul></li>




				<li style="display: none;" class="sub-menu"><a href="javascript:;" class=""> <i class="icon-cog"></i> <span>字典管理</span><span
						class="arrow"></span> </a>
					<ul class="sub">
						<li id="orderTagDic-init-list"><a class=""
							href="<%=basePath%>orderTagDic/init-list?unfolder=orderTagDic-init-list">订单字典管理</a>
						</li>
						<li id="bankDic-init-list"><a class="" href="<%=basePath%>bankDic/init-list?unfolder=bankDic-init-list">银行字典管理</a>
						</li>
						<li id="valueAddedServices-init-list"><a class="" href="<%=basePath%>valueAddedServices/init-list?unfolder=valueAddedServices-init-list">增值服务管理</a>
						</li>
					</ul></li>

				<!-- icon-sitemap -->
				<li style="display: none;" class="sub-menu"><a href="javascript:;" class=""> <i class="icon-credit-card"></i>
						<span>提现管理</span><span id="newWithdrawApply" style="display: none;" class="label label-important  label-mini"></span><span
						class="arrow"></span> </a>
					<ul class="sub">
						<li id="withdrawApply-init-list"><a class=""
							href="<%=basePath%>withdrawApply/init-list?unfolder=withdrawApply-init-list">提现记录</a>
						</li>

					</ul></li>


				<li style="display: none;" class="sub-menu"><a href="javascript:;" class=""> <i class="icon-bar-chart"></i>
						<span>数据统计</span> <span class="arrow"></span> </a>
					<ul class="sub">
						<li id="orderStatistics-init-list"><a class=""
							href="<%=basePath%>orderStatistics/init-list?unfolder=orderStatistics-init-list">订单统计</a>
						</li>
						<li id="userStatistics-init-list"><a class=""
							href="<%=basePath%>userStatistics/init-list?unfolder=userStatistics-init-list">用户统计</a>
						</li>
						<li id=""><a class="" href="http://mtj.baidu.com/web/dashboard" target="_blank">百度统计</a>
						</li>

					</ul></li>

				<li style="display: none;" class="sub-menu"><a href="javascript:;" class=""> <i class="icon-dashboard"></i>
						<span>安装包管理</span> <span class="arrow"></span> </a>
					<ul class="sub">
						<li id="apk-init-list"><a class="" href="<%=basePath%>apk/init-list?unfolder=apk-init-list">Android包管理</a></li>

					</ul></li>
					
				<li style="display: none;" class="sub-menu"><a href="javascript:;" class=""> <i
                        class="icon-dashboard"></i>
                    <span>世界杯竞猜</span> <span class="arrow"></span> </a>
                    <ul class="sub">
                        <li id="wc-country-list"><a class="" href="<%=basePath%>worldcup/init-list?unfolder=wc-country-list">竞猜活动</a>
                        </li>
                        <%--<li id="wc-user-list"><a class="" href="<%=basePath%>apk/init-list?unfolder=wc-country-list">竞猜用户</a>--%>
                        <%--</li>--%>
                    </ul>
                </li>

				<li style="display: none;" class="sub-menu"><a href="javascript:;" class=""> <i class="icon-sitemap"></i> <span>分销管理(商家)</span><span
						class="arrow"></span> </a>
					<ul class="sub">
						<li id="businessBankCard-init-list"><a class=""
							href="<%=basePath%>businessBankCard/init-list?unfolder=businessBankCard-init-list">我的银行卡</a>
						</li>
						<li id="myEarnings-init-list"><a class=""
							href="<%=basePath%>myEarnings/init-list?unfolder=myEarnings-init-list">我的收益</a></li>

					</ul></li>


			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
	<!-- END SIDEBAR -->