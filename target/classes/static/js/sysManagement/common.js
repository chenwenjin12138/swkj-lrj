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

function add(url, form, returnUrl) {
	$.getJSON(
		""+url,
        form.serialize(),
		function (date) {
			if(date.errorMsg == "success"){
					alert("添加成功！");
                    window.location.href = returnUrl;
			}else {
				alert("添加失败");
			}
        }
	)
}

/**
 * 通用数据更新保存AJAX方法(POST)
 *
 * @param uri
 *            表单提交的地址
 * @param form
 *            表单jquery对象
 * @param returnUri
 *            返回列表页面地址
 */
function edit(url, form, returnUrl) {
    $.getJSON(
        ""+url,
        form.serialize(),
        function (date) {
            if(date.errorMsg == "success"){
                alert("更新成功！");
                window.location.href = returnUrl;
            }else {
                alert("更新失败");
            }
        }
    )
}

