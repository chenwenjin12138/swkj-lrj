

/**
 * @功能说明:日期格式化静态方法.
 */
if (!Date.format) {
	Date.format = function(d, format) {
		format = format || 'yyyy-MM-dd';
		var o = {
			"M+" : d.getMonth() + 1, // month
			"d+" : d.getDate(), // day
			"h+" : d.getHours(), // hour
			"m+" : d.getMinutes(), // minute
			"s+" : d.getSeconds(), // second
			"q+" : Math.floor((d.getMonth() + 3) / 3), // quarter
			"S" : d.getMilliseconds()
		}
		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (d.getFullYear() + "").substr(4 - RegExp.$1.length));
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k])
						.substr(("" + o[k]).length));
			}
		}
		return format;
	};
};

/**
 * @功能说明:日期格式化实例方法.
 */
if (!Date.prototype.format) {
	Date.prototype.format = function(format) {
		return Date.format(this, format);
	};
};