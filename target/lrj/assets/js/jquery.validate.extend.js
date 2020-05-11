/**
 * 添加自定义校验用户名
 */
jQuery.validator.addMethod("userNameValidate", function(value, element, param) {
	var userPattern = /^[a-zA-Z0-9_]{1,}$/; //用户名校验正则表达式
	return this.optional(element) || userPattern.test(value);
}, "用户名只能由字母数字下划线组成!");

/**
 * 正整数
 */
jQuery.validator.addMethod("integerPositiveValidate", function(value, element, param) {
	var integerPositive = /^[0-9]*[1-9][0-9]*$/; //正整数正则
	return this.optional(element) || integerPositive.test(value);
}, "请输入合法的数字!");

/**
 * 校验手机号码
 */
jQuery.validator.addMethod("cellPhoneValidate", function(value, element, param) {
	var cellPhone = /^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/; //正整数正则
	return this.optional(element) || cellPhone.test(value);
}, "请输入正确的手机号码!");

/**
 * 金额校验
 */
jQuery.validator.addMethod("money", function(value, element, param) {
	var money = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	return this.optional(element) || money.test(value);
}, "请输入正确的金额!");