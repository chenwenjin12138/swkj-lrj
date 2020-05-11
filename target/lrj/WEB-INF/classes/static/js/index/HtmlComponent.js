/**
 * HtmlComponent.js
 * 该文件封装一些公用JS操作HTML方法
 * 以及一些效果弹框登组件
 * 
 */



/**
 * JS生成下拉框选项组件.
 *
 * @param item
 *            {Json Array} 主数据
 * @param name
 *            {String} 下拉选项 text 文本 对应JSONObject key
 * @param value{String}
 *            下拉选项 value 值 对应JSONObject key
 * @param obj
 *            select标签DOM对象
 */
function createSelect(item, name, value, obj) {
	var htm = "<option value=''>请选择</option>";
	for ( var i = 0; i < item.length; i++) {
		htm += "<option value='" + item[i][value] + "'>" + item[i][name]
				+ "</option>";
	}
    console.log(htm);
	obj.html(htm);
}


function createSelectWithoutNull(item, name, value, obj) {
	var htm = "";
	for ( var i = 0; i < item.length; i++) {
		htm += "<option value='" + item[i][value] + "'>" + item[i][name]
				+ "</option>";
	}
	obj.html(htm);
}

/**
 * JS生成下拉框选项组件.默认选中值
 * 
 * @param item
 *            {Json Array} 主数据
 * @param name
 *            {String} 下拉选项 text 文本 对应JSONObject key
 * @param value{String}
 *            下拉选项 value 值 对应JSONObject key
 * @param obj
 *            obj select标签DOM对象
 * @param defaultValue
 *            默认选中值/或者text
 * @param flag
 *            0默认用value,1默认用text
 */
function createSelectWithDefaultSelected(item, name, value, obj, defaultValue,
		flag) {
	var htm = "";
	for ( var i = 0; i < item.length; i++) {

		if ((flag == 0 && item[i][value] == defaultValue)
				|| (flag == 1 && item[i][name] == defaultValue)) {
			htm += "<option selected value='" + item[i][value] + "'>"
					+ item[i][name] + "</option>";

		} else {
			htm += "<option value='" + item[i][value] + "'>" + item[i][name]
					+ "</option>";
		}
		obj.html(htm);
	}

}

/**
 * JS生成checkbox组件
 * 
 * @param item
 *            {Json Array} 主数据
 * @param name
 *            {String} checkbox text 文本 对应JSONObject key
 * @param value
 *            {String} checkbox value 值 对应JSONObject key
 * @param obj
 *            显示checkbox DOM对象
 * @param checkName
 * @param allCheckName
 */
function createCheckBox(item, name, value, obj, checkName, allCheckName) {
	var htm = "&nbsp&nbsp&nbsp&nbsp&nbsp<input type=\"checkbox\" id='"
		+ allCheckName + "' name=\"\" value=''>&nbsp" + "全选";
	for ( var i = 0; i < item.length; i++) {
		htm+="<br>";
		htm += "&nbsp&nbsp&nbsp&nbsp&nbsp<input " + name + "='" + item[i][name]
				+ "' type=\"checkbox\" name=\"" + checkName + "\" value='"
				+ item[i][value] + "'>&nbsp" + item[i][name];
	}
	obj.html(htm);
}

/**
 * 对象数组排序回调
 */
function createComparsionFunction(propertyName)
{
    return function(object1, object2)
    {
        var value1 = object1[propertyName];
        var value2 = object2[propertyName];
        if (value1 < value2)
        {
            return -1;
        } else if (value1 > value2)
        {
            return 1;
        } else
        {
            return 0;
        }
    }
}


/**
 * 序列化表单生成json字符串
 */
$.fn.jsonSerialize = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return JSON.stringify(o);    
}; 

/**
 * String 扩展
 */
String.prototype.dateFormat=function(){
	try {
		return this.substring(0,this.lastIndexOf("."));
	} catch (e) {
		return "";
	}
	
}
String.dateFormat=function(rowData,key){
	try {
		return rowData[key].substring(0,rowData[key].lastIndexOf("."));
	} catch (e) {
		return "";
	}
}


/**
 * 消息提示框 依赖laryer
 * 
 * @author Sam
 * @param msg
 *            提示信息
 * @param time
 *            提示时间
 * @param style
 *            样式
 */

function msg_(msg,time,style,callback){
	
	  $.layer({ type: 0, shade: [0], time:time, area: ['auto', 'auto'], offset:
	  ['590px', '300px'], title: false, border: [1, 0.2, '#000'], closeBtn:
	  false, shift:'bottom', dialog: { type: style, msg: msg },end:callback });
	 
	
	
	
}



/**
 * alert
 * 
 * @param msg
 * @param time
 * @param style
 * @param callback
 */
function msg(msg,time,style,callback){
	alert(msg);
	if(callback){
		callback.call(this);
	}
	
}