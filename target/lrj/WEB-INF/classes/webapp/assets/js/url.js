/**
 * 获取URL中参数
 * 
 * @param name
 * @returns
 * @deprecated
 * @notice--------此方法废弃-------------
 */
function getUrlvalue(name) {
	var str = decodeURI(window.location.search);
	if (str.indexOf(name) != -1) {
		var pos_start = str.indexOf(name) + name.length + 1;
		var pos_end = str.indexOf("&", pos_start);
		if (pos_end == -1) {
			return str.substring(pos_start);
		} else {
			return str.substring(pos_start, pos_end);
		}
	} else {
		return "";
	}
}



/**
 * @功能说明:通过参数名称获取浏览器地址栏URL参数的值.
 * @param tkey
 *            参数名称
 * @returns value 参数值
 */
function getURLParamsByKey(tkey){
	var urlParam=(window.location.search.split("?"))[1];
	if(typeof(urlParam)!="undefined"&&urlParam!=null&&urlParam!=''){
		var params=urlParam.split("&");
		if(typeof(params)!="undefined"&&params!=null&&params.length>0){
			for(var t in params){
			   var key= params[t].split("=")[0];
			   var value=params[t].split("=")[1];
			   if(tkey==key){
				   return value;
			   }
			   
			}
		}
	}
	return null;
}

/**
 * @功能说明:获取浏览器参数集合Map。
 * @returns {Array} 参数Map
 */
function getURLParamsMap() {
	var paramsMap = [];
	/** 获取?后的参数字符串* */
	var search = (location.search.split("?"))[1];

	if (search) {
		/** 获取每组参数* */
		var entrys = search.split("&");
		if (entrys && entrys.length > 0) {

			/** 迭代获取每组参数* */
			for ( var k in entrys) {

				/** 参数名* */
				var key = (entrys[k].split("="))[0];
				/** 参数值* */
				var value = (entrys[k].split("="))[1];
               paramsMap[key]=value;
				
			}

		}

	}
	return paramsMap;
}

/**
 * URL解码
 * 
 * @param str
 * @returns {String}
 */
function UrlDecode(str) 
{    
var i,temp;   
var result="";   
for(i=0;i<str.length;i++) 
{     
if(str.charAt(i)=="%") 
{      
   if(str.charAt(++i)=="u") 
     {       
      temp=str.charAt(i++) + str.charAt(i++) + str.charAt(i++) + str.charAt(i++) + str.charAt(i); 
      result += unescape("%" + temp);      
     } 
     else 
     {       
      temp = str.charAt(i++) + str.charAt(i);      
      if(eval("0x"+temp)<=160) 
      {        
       result += unescape("%" + temp);       
      } 
      else 
      {        
       temp += str.charAt(++i) + str.charAt(++i) + str.charAt(++i);       
       result += Decode_unit("%" + temp);       
      }      
     }     
    } 
    else 
    {      
     result += str.charAt(i); 
    }   
}   
return result; 
} 


