/**
 * z-global.js 该JS主要用于整个页面
 * 主要功能有SESSION登录会话获取校验
 * 用户权限的校验
 * 及一些全局参数的保存
 * 
 */
/** 后台服务器路径* */
var basePath = $("#basePath").val();
var rootPath = $("#rootPath").val();
/** 文件服务器路径* */
var fileBaseUrl = rootPath + "/file";
/** 用户登录信息对象* */
var sysAdminSessionInfo = {};
/** 同步,不缓存* */
$.ajaxSetup({
    async: false,
    cache: false
});
/** 该类为一些静态常量,如 1,0分别代表启用禁用等一系列常用不可修改的常量.* */
var Constant = new Object();
/**
 * 1:代表该用户或者角色为已核对状态.
 */
Constant.CHECK = 1;
/**
 * 0:代表该用户或者角色为未核对状态.
 */
Constant.CHECK_FALSE = 0;
/**
 * 1:代表该用户或者角色等为可用状态.
 */
Constant.ACTIVE = 1;
/**
 * 0:代表该用户或者角色等为不可用可用状态.
 */
Constant.FORBIDDEN = 0;
/**
 * 0:代表请求执行后返回客户端状态为执行成功.
 */
Constant.SUCCESS = 0;
/**
 * 1:代表请求执行后返回客户端状态为执行失败.
 */
Constant.FAIL = 1;
/**
 * 1:代表商品为显示状态.
 */
Constant.SHOW = 1;
/**
 * 0:代表商品为显示隐藏状态.
 */
Constant.HIDDEN = 0;
/**
 * 帐号没被占用
 */
Constant.AVAILABLE = "0";
/**
 * ajax请求状态 成功
 */
Constant.AJAXSTATUSSUCCESS = "success";

/** 按钮1 代表编辑* */
Constant.EDIT = 1;
/** 0代表保存* */
Constant.SAVE = 0;

/** 订单未完成* */
Constant.UNFINISHED = 1;
/** 订单已完成* */
Constant.FINISHED = 2;
/** 订单未支付* */
Constant.NONPAYMENT = 1;
/** 订单已支付* */
Constant.PAYMENT = 2;
/** 支付失败* */
Constant.PAYMENTFAILED = 3;
/** 未抢* */
Constant.UNLOCK = 1;
/** 已抢* */
Constant.LOCK = 2;
/** 追踪状态 1为系统 2为用户自设* */
Constant.SYSTEMTYPE = 1;
Constant.USERTYPE = 2;
/** 富文本为1 外链为2* */
Constant.RICHTEXT = 1;
Constant.LINK = 2;
/** 消息读取* */
Constant.READ = 2;
Constant.NOREAD = 1;
/** 用户类型* */
Constant.APPUSER = 1;
Constant.STAFFUSER = 2;
Constant.ADMIN = 3
/** 消息类型* */
Constant.SYSTEMMESSAGE = 1;
/** 关于我们* */
Constant.ABOUTUS = 1;
/** 帮助* */
Constant.HELP = 2;
/** 法律* */
Constant.LAW = 3;
/** 1* */
Constant.ONE = 1;
/** 2* */
Constant.TWO = 2;
Constant.WXMONTH = 100;
Constant.WXMONTH1 = 101;
Constant.WXMONTH2 = 102;
/** 3* */
Constant.THREE = 3;

/** ********************************************************************全局变量声明*********************************************************************************************************************** */

/** *********************************session检查**************************************************** */
/** 校验session,是否超时,如果超时跳转登录* */
function checkSession() {
    $.ajax({
        type: 'GET',
        url: basePath + "admin/session",
        async: true,
        cache: false,
        dataType: "json",
        success: function(data) {
            if (!data) {
                /** 如果是在主页直接跳转登录* */
                if (location.pathname.indexOf("/admin/main") != -1) {
                    location.href = basePath + "login.jsp";
                } else {
                /** 如果不是主页,刷新页面利用spring拦截器跳转登录页面* */	
                    var yes = function() {
                        window.location.reload();
                    };
                    var no = function() {
                        window.location.reload();
                    };
                    layer.confirm("登陆超时,可能您的帐号已被其他人登录!", yes, no);
                }
            }
        },
        error: function(data) {
            location.href = basePath + "login.jsp";
        }
    });
}
/** 定时检查会话是否超时10秒检查一次* */
/*window.setInterval(checkSession, 10000);*/
/** *********************************session检查**************************************************** */

/** *********************************订单个数检查**************************************************** */
/**
 * @功能说明 获取最新订单个数与cookie中订单个数比较 如果获取的订单数比cookie大则提示有新订 单
 * @param type
 *            标识执行的上级 定时器执行；页面加载执行
 */
function getOrderCount(type) {
    $.ajax({
        type: 'GET',
        url: basePath + "order/getOrderCount",
        async: true,
        cache: false,
        dataType: "text",
        success: function(data) {
            /** 获取订单总数* */
            var num = parseInt(data);
            if (!isNaN(num)) {
                if (!$.cookie("ordersCount")) {
                    $.cookie("ordersCount", num, {
                        expires: 7,
                        path: '/'
                    })
                } else if ($.cookie("ordersCount") && parseInt($.cookie("ordersCount")) != num) {
                    /**
					 * 自动消失提示框 style 1 6 9 11 15 17*
					 */
                    var differ = num - parseInt($.cookie("ordersCount"));
                    /** 新订单个数* */
                    if (differ >= 1) {
                        $("#new_order_message").html(differ + "条新订单"); 
                        $("#new_order_message").show();
                        /** 定时器执行才有消息框* */
                        if (!type) {
                            msg_("您有" + differ + "条新订单!", 2, 17);
                        }
                    }

                }
            }
        },
        error: function(data) {
            console.log("数据请求失败!");
        },
    });

}
/**
 * 每10秒检查一次订单数量
 */
window.setInterval(getOrderCount, 10000);

/** *********************************订单个数检查**************************************************** */

/** *********************************用户反馈个数检查**************************************************** */
/**
 * @功能说明 获取最新用户反馈数与cookie中反馈数比较 如果获取的反馈数比cookie大则提示有新反 馈
 * @param type
 *            标识执行的上级 定时器执行；页面加载执行
 */
function getUserFeedBackCount(type) {
    $.ajax({
        type: 'GET',
        url: basePath + "feed-back/getUserFeedBackCount",
        async: true,
        cache: false,
        dataType: "text",
        success: function(data) {
            /** 获取用户反馈总数* */
            var num = parseInt(data);
            if (!isNaN(num)) {
                if (!$.cookie("userFeedBack")) {
                    $.cookie("userFeedBack", num, {
                        expires: 7,
                        path: '/'
                    })
                } else if ($.cookie("userFeedBack") && parseInt($.cookie("userFeedBack")) != num) {
                    /**
					 * 自动消失提示框 style 1 6 9 11 15 17*
					 */
                    var differ = num - parseInt($.cookie("userFeedBack"));
                    /** 新反馈个数* */

                    if (differ >= 1) {
                        $("#new_feedback_message").html(differ + "条新反馈");
                        $("#new_feedback_message").show();
                        /** 定时器执行才有消息框* */
                        if (!type) {
                            msg_("您有" + differ + "条新反馈待查看!", 2, 17);
                        }
                    }

                }
            }

        },
        error: function(data) {
            console.log("数据请求失败!");
        },
    });

}
/**
 * 每10秒检查一次订单数量
 */
window.setInterval(getUserFeedBackCount, 10000);

/** *********************************用户反馈个数检查**************************************************** */






/** --------------------------------提现记录数量检查------------------------* */

/**
 * @功能说明:检查并提示是否有未处理的提现记录
 * @param:type
 */
function getWithdrawApplyNoHandleCount(type) {
    $.ajax({
        type: 'GET',
        url: basePath + "withdrawApply/getWithdrawApplyNoHandleCount",
        async: true,
        cache: false,
        dataType: "text",
        success: function(data) {
            /** 获取未处理提现记录总数* */
            var num = parseInt(data);
            /** 判断是否为数字* */
            if (!isNaN(num)) {
            	/** 判断cookie中是否存在* */
                if (!$.cookie("withdrawApplyNoHandleCount")) {
                	/** 不存在则保存到cookie中* */
                    $.cookie("withdrawApplyNoHandleCount", num, {
                        expires: 7,
                        path: '/'
                    })
                }
                /** 如果存在且与服务端最新的不相等则计算数量差* */
                else if ($.cookie("withdrawApplyNoHandleCount") && parseInt($.cookie("withdrawApplyNoHandleCount")) != num) {
                	/** 计算数量差* */
                    var differ = num - parseInt($.cookie("withdrawApplyNoHandleCount"));
                    /** 如果大于1提示有新记录* */
                    if (differ >= 1) {
                    	/** 左边菜单显示文字* */
                        $("#newWithdrawApply").html(differ + "条新提现记录");
                        /** 左边菜单显示* */
                        $("#newWithdrawApply").show();
                        /** 定时器执行才有消息框* */
                        if (!type) {
                            msg_("您有" + differ + "条新提现记录待查看!", 2, 17);
                        }
                    }

                }
            }

        },
        error: function(data) {
            console.log("数据请求失败!");
        },
    });

/**
 * 每10秒检查一次订单数量
 */
/*window.setInterval(getWithdrawApplyNoHandleCount, 10000);*/
/** --------------------------------提现记录数量检查------------------------* */
}

/**
 * 判断是否含有该权限
 * 
 * @param data{权限数据源}
 * @param target{目标导航名称}
 * @returns {Boolean}
 */
/*function AuthorityIsHas(data, target) {
    var authorityNames = data.authoritys; // authoritys
    for (var key in authorityNames) {
        if ($.trim(authorityNames[key].authorityName) == $.trim(target)) {
            return true;
        }
    }
    return false;
}*/

/**
 * 初始化左边菜单,判断是否有权限,是否需要隐藏
 * 
 * @param data{权限数据源}
 */
function initLeftMenue(data) {
    var menues = $("ul[class='sub']").find("li");
    menues.each(function() {
        var menue = $(this);
        var bool = true;
        if (bool) {
            menue.show();
        } else {
            menue.hide();
        }
    });

}

/**
 * 判断左边才当选中状态,初始化记载菜单
 */
function initMenu() {
    $("#" + getURLParamsByKey("unfolder")).attr("class", "active");
    $("#" + getURLParamsByKey("unfolder")).parent().parent().addClass("active");
    $("#" + getURLParamsByKey("unfolder")).parent().parent().find("a").find("span[class='arrow']").addClass("open");
}
/** 校验是否有修改分销比例权限* */
/*function initDistributeSetButton() {
	/!** 获取权限* *!/
	var bool = AuthorityIsHas(sysAdminSessionInfo, "分销设置");
	/!** 如果无权限隐藏按钮* *!/
	if (!bool) {
		$("#earningsratioEdit").parent().hide();
	}
}*/


/**
 * 一级菜单隐藏:如果其下级所有菜单都隐藏了
 */

function initSuperMenue(){
	/** 遍历菜单* */
	$("li[class='sub-menu']").each(function(){
		/** 一级菜单* */
		var subMenu=$(this);
		/** 二级菜单* */
		var sub= subMenu.find("ul");
		if(sub){
			sub.find("li").each(function(){
				
				/** 如果存在一个二级菜单可见则一级菜单可见* */
				if($(this).css("display")!='none'){
					subMenu.show();
					return;
				}
			});
		}
	});
}

$(document).ready(function() {
    /** 初始化二级菜单* */
    initLeftMenue(sysAdminSessionInfo); 
    /** 初始化一级菜单* */
    initSuperMenue();
    /** 获取订单数量* */
    getOrderCount("redirect");
    /** 获取反馈数* */
    getUserFeedBackCount("redirect");
    /** 获取提现记录* */
    getWithdrawApplyNoHandleCount("redirect");
    /** 菜单选中状态* */
    initMenu(); 
    $("#btn-exit").click(function() {
        location.href = basePath;
    });

    /** **************************************************************************修改密码******************************************************************************** */
    $("#changePassword").click(function() {
        $("#changePasswordModal").modal("show");
    });

    $("#changePasswordForm").validate({
        rules: {
            "orginPassword": {
                required: true,
                rangelength: [6, 32],

            },
            "newPassword": {
                required: true,
                rangelength: [6, 32]
            },
            "rNewPassword": {
                required: true,
                equalTo: "#newPassword",
                rangelength: [6, 32]
            }
        },
        messages: {
            "orginPassword": {
                required: "请输入旧密码!",
                rangelength: "密码长度为6到32位!",
            },
            "newPassword": {
                required: "请输入新密码!",
                rangelength: "密码长度为6到32位!"
            },
            "rNewPassword": {
                required: "请再次输入新密码!",
                equalTo: "两次输入密码不一致!",
                rangelength: "密码长度为6到32位!"
            }

        }
    });

    $("#change_sure").click(function() {
        /** 校验表单输入* */
        if ($("#changePasswordForm").valid()) {
            $.ajax({
                type: 'POST',
                url: basePath + "admin/changePassword",
                async: true,
                data: {
                    orginPassword: $.md5($("#orginPassword").val()),
                    newPassword: $.md5($("#newPassword").val()),
                    adminName: sysAdminSessionInfo.sysAdminEntity.adminName
                },
                dataType: "json",
                success: function(data) {
                    if (data.flag == Constant.SUCCESS) {
                        alert(data.message);
                        $("#changePasswordModal").modal("hide");
                    } else {
                        alert(data.message);
                    }
                },
                error: function(data) {
                    console.log(data);
                },
            });
        }

    });
    /** **************************************************************************修改密码end******************************************************************************** */

    /** **************************************************************************用户分销收益比******************************************************************************** */
    
    $("#earningsratioEdit").click(function() {
    	 $.ajax({
             type: 'GET',
             url: basePath + "admin/findAllEarningsratio",
             async: true,
             data: '',
             dataType: "json",
             success: function(data) {
               $("#APPEARNINGSRATIO").val(data.APPEARNINGSRATIO);
               $("#BUSSEARNINGSRATIO").val(data.BUSSEARNINGSRATIO);   
             }    
         });
        $("#earningsratioModal").modal("show");
    });
    
    
    $("#EarningsratioForm").validate({
        rules: {
            "APPEARNINGSRATIO": {
                required: true,
             

            },
            "BUSSEARNINGSRATIO": {
                required: true
            }
        },
        messages: {
            "APPEARNINGSRATIO": {
                required: "请输入APP分销收益比"
            },
            "BUSSEARNINGSRATIO": {
                required: "请输入商家分销收益比!"
            }
        }
    });
    
    $("#submit_app").click(function() {
        /** 校验表单输入* */
        if ($("#EarningsratioForm").valid()) {
            $.ajax({
                type: 'POST',
                url: basePath + "admin/changeEarningsratio",
                async: true,
                data: {
            	APPEARNINGSRATIO: $("#APPEARNINGSRATIO").val(),
            	BUSSEARNINGSRATIO: $("#BUSSEARNINGSRATIO").val()
                },
                dataType: "json",
                success: function(data) {
                    if (data.flag == Constant.SUCCESS) {
                        alert(data.message);
                        $("#earningsratioModal").modal("hide");
                    } else {
                        alert(data.message);
                    }
                },
                error: function(data) {
                    console.log(data);
                },
            });
        }

    });

    
    
    
    /** **************************************************************************用户分销收益比end******************************************************************************** */
});