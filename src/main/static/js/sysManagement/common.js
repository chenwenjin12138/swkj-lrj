/**
 * 该文件封装一些公用的业务处理JS对象
 */

/** 选择有效的角色加载到下拉框中* */
function selectValidityRole() { // 待抽象公用
	$.getJSON(
		"selectRoleList",
		function (data) {
			if(data.errorMsg == "success"){
                console.log(data);
                // 创建下拉选项
                createSelect(data.resultDate, "roleName", "sysRoleId", $("#sysAdminRoleType"));
			}else {
                msg("数据获取失败!", 2, 2);
			}

        }
	)
}

function submit(url, form, returnUrl) {
	$.getJSON(
		""+url,
        form.serialize(),
		function (date) {
			if(date.errorMsg == "success"){
				window.location.href = returnUrl;
			}else {
				alert("提交失败");
			}
        }
	)
	
}

