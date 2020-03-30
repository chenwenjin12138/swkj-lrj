/**
 * @comment:demo.util,demo.Es 工具对象
 * @datetime:20150415
 * @author:sam
 * @see /demo-webresource/webroot/js/commons.js 查看原版内容
 * @depend JS JQUERY
 */

(function(win, $) {

	/**
	 * demo 对象创建
	 */
	var demo = {
		config : {}
	};

	/**
	 * util 工具对象创建
	 */
	demo.util = {

		/**
		 * 判断对象是否为空
		 * 
		 * @param v
		 *            对象
		 * @param allowBlank
		 *            {Boolean} 是否允许空格标识
		 * @returns {Boolean}
		 */
		/*
		 * isEmpty : function(v, allowBlank) { return v === null || v ===
		 * undefined || (($.isArray(v) && v.length===0)) ||
		 * ($.type(v)==="object"&&$.isEmptyObject(v)) || (!allowBlank ? v.trim &&
		 * v.trim() === "" : false); },
		 */

		isEmpty : function(v, allowBlank) {

			if (this.isString(v)) {

				if ($.type(allowBlank) === "boolean" && allowBlank === true) {
					return false;
				} else {
					return v.trim() === "";
				}

			} else {

				if (v === null || v === undefined
						|| (($.isArray(v) && v.length === 0))
						|| ($.type(v) === "object" && $.isEmptyObject(v))) {
					return true;
				} else {
					return false;
				}

			}
		},

		/**
		 * 
		 * @param str
		 * @param value
		 * @param other
		 * @returns
		 */
		toggle : function(str, value, other) {
			return str == value ? other : value;
		},

		/**
		 * 
		 * Object KISSY.unparam (str[ ,sep=’&’,eq=’=’ ]) 将参数字符串 str 还原为对象.
		 * Parameters: o (object) – 参数字符串 seq (string) – 参数间分隔符, 默认 & eq
		 * (string) – 参数与参数值间的分割符, 默认 =
		 * 
		 * Returns:参数的对象表示 Return type:Object
		 */
		REG_ARR_KEY : /^(\w+)\[\]$/,
		unparam : function(str, sep) {
			if (typeof str !== 'string' || (str.trim().length === 0))
				return {};

			var ret = {}, pairs = str.split(sep || '&'), pair, key, val, m, i = 0, len = pairs.length;

			for (; i < len; i++) {
				pair = pairs[i].split('=');
				key = decodeURIComponent(pair[0]);

				// pair[1] 可能包含gbk编码中文, 而decodeURIComponent 仅能处理utf-8 编码中文
				try {
					val = decode(pair[1]);
				} catch (e) {
					val = pair[1] || '';
				}

				if ((m = key.match(this.REG_ARR_KEY)) && m[1]) {
					ret[m[1]] = ret[m[1]] || [];
					ret[m[1]].push(val);
				} else {
					ret[key] = val;
				}
			}
			return ret;
		},

		/**
		 * 获取url参数返回JSON对象
		 * 
		 * @returns
		 */
		getUrlParams : function() {
			var queryStr = location.search.substring(1);
			return this.unparam(queryStr);
		},

		/**
		 * 根据KEY获取对应的参数
		 * 
		 * @param key
		 * @returns
		 */
		getUrlParam : function(key) {
			return this.getUrlParams()[key];
		},

		getUrlPath : function(idx) {
			var path = location.pathname.split("/");
			return path[idx];
		},

		/**
		 * 字符串转换为16进制
		 */
		stringToHex : function(s) {
			var r = "0x";
			var hexes = new Array("0", "1", "2", "3", "4", "5", "6", "7", "8",
					"9", "a", "b", "c", "d", "e", "f");
			for (var i = 0; i < s.length; i++) {
				r += hexes[s.charCodeAt(i) >> 4] + hexes[s.charCodeAt(i) & 0xf];
			}
			return r;
		},

		/**
		 * 16进制转换为字符串
		 */
		hexToString : function(h) {
			var r = "";
			for (var i = (h.substr(0, 2) == "0x") ? 2 : 0; i < h.length; i += 2) {
				r += String.fromCharCode(parseInt(h.substr(i, 2), 16));
			}
			return r;
		},

		htmlToText : function(html) {
			var txt = "";
			if (html) {
				txt = html.replace(/<[^>]+>/g, "");//
				txt = txt.replace(/&nbsp;/ig, "");
			}
			return txt;
		},

		/**
		 * 判断一个对象是否为string类型
		 * 
		 * @param obj
		 * @returns {Boolean}
		 */
		isString : function(obj) {
			return Object.prototype.toString.call(obj) === "[object String]"

		},

		/**
		 * 判断对象是否为Date类型
		 * 
		 * @param obj
		 *            输入对象
		 * @returns {Boolean}
		 */
		isDate : function(obj) {
			return Object.prototype.toString.call(obj) === "[object Date]"
		},

		/*
		 * Jquery 已做封装 isNumber : function(o) { return toString.call(o) ===
		 * '[object Number]' && isFinite(o); },
		 */

		/**
		 * 判断对象是否维数组类型 Array
		 * 
		 * @param obj
		 * @returns {Boolean} Jquery 已做封装
		 */
		/*
		 * isArray : function(obj) { //return obj instanceof Array; return
		 * Object.prototype.toString.call(obj) === "[object Array]" },
		 */

		/*
		 * Jquery 已做封装 isEmptyObject : function(o) { for ( var p in o) { return
		 * false; } return true; },
		 */

		/**
		 * 返回元素所在数组的下标
		 * 
		 * @param item
		 *            目标元素
		 * @param arr
		 *            数组
		 * @returns {Number} 如果找到返回元素下标,否则返回-1
		 */
		indexofArray : function(item, arr) {
			if (!this.isArray(arr) || arr.length == 0) {
				return -1;
			} else {
				for ( var key in arr) {
					if (arr[key] === item) {
						return key;
					}
				}

				return -1;
			}

		},

		/**
		 * 确定目标元素是否存在于数组中
		 * 
		 * @param item
		 *            目标元素
		 * @param arr
		 *            被查询数组
		 * @returns {Boolean}
		 */
		inArray : function(item, arr) {
			return this.indexofArray(item, arr) > -1;
		},

		/*
		 * 
		 * isFunction : function(obj) { return toString.call(obj) === '[object
		 * Function]'; },
		 */

		/**
		 * 迭代对象
		 * 
		 * @param object
		 * @param fn
		 * @param context
		 * @returns
		 */
		each : function(object, fn, context) {
			var key, val, i = 0, length = object.length, isObj = length === undefined
					|| $.isFunction(object);
			context = context || win;

			if (isObj) {
				for (key in object) {
					if (fn.call(context, object[key], key, object) === false) {
						break;
					}
				}
			} else {
				// 极尽压缩之能事
				for (val = object[0]; i < length
						&& fn.call(context, val, i, object) !== false; val = object[++i]) {
				}
			}

			return object;
		},

		/**
		 * 格式化缩进json对象
		 * 
		 * @param Obj
		 * @param formatChar
		 * @returns {String}
		 */
		formatJson : function(Obj, formatChar) {
			var jsonObj = this.isString(Obj) ? JSON.parse(Obj) : Obj;
			return JSON.stringify(jsonObj, null, formatChar)
		},

		/**
		 * 将多层的json对象展开为单层json对象，主要用于post参数传输(因为jquery不支持多层对象的展开)，name用'.'分隔的方式。例：{x:1,
		 * y:{y1:2, y2:{z:3}}} 将 展开为{"x":1, "y.y1":2, "y.y2.z":3}
		 * 
		 * @param data
		 * @returns {___anonymous751_752}
		 */
		unpackData : function(data) {
			var buf = {};
			this._unpackData(null, data, buf);
			return JSON.stringify(buf);
		},

		_unpackData : function(name, data, buf) {
			if (!jQuery.isPlainObject(data)) {
				return data;
			}
			var buf1 = {};
			for ( var name1 in data) {
				if (jQuery.isPlainObject(data[name1])) {
					var buf2 = {};
					var unpackPart = this._unpackData(name1, data[name1], buf2);
					// console.debug(name1 + ':' + this.parseJson(unpackPart));
					for ( var name2 in unpackPart) {
						buf[name1 + '.' + name2] = unpackPart[name2];
						buf1[name1 + '.' + name2] = unpackPart[name2];
					}
				} else {
					if (this.isEmpty(name)) {
						buf[name1] = data[name1];
					}
					buf1[name1] = data[name1];
				}
			}
			return buf1;
		}
	}

	/**
	 * util 工具包为了方便用一个变量来代替 便于调用以及依赖关系维护
	 */
	window.U = demo.util;

	/** ********************************************************************************************************** */
	/**
	 * ES工具对象
	 */
	demo.ES = {

		EMPTY : [ -99, '-99' ],
		QueryType : {
			RANGE : '$range$'
		},
		/**
		 * 指定fields后，ES返回的结果在fields里，且格式不同于_source，本函数将fields格式的数据parse为_source格式并将数据放入
		 * _source里
		 */
		_processFieldsData : function(fieldsData) {
			if (fieldsData._source && !fieldsData.fields) {
				return;
			}
			for ( var fieldName in fieldsData.fields) {
				var value = fieldsData.fields[fieldName];
				if ($.isArray(value)) {
					if (value.length === 1) {
						fieldsData.fields[fieldName] = value[0];
					}
				}
			}
			fieldsData._source = fieldsData.fields;
			// alert(fieldsData.fields);
		},

		parseESJson : function(data, page, callback) {
			var jsonObj = {
				start : 0,
				limit : 10,
				pageNo : 1,
				result : []
			};
			if (page) {
				jsonObj['start'] = page['start'] || page.get('start');
				jsonObj['limit'] = page['limit'] || page.get('limit');
				jsonObj['pageNo'] = page['pageNo'] || page.get('pageNo');
				jsonObj['sort'] = page['sort'] || page.get('sort');
				jsonObj['dir'] = page['dir'] || page.get('dir');
				jsonObj['totalCount'] = page['totalCount']
						|| page.get('totalCount');
			}
			var dataObj = U.isString(data) ? JSON.parse(data) : data;
			var hits = dataObj.hits;
			if (hits) {// 返回多个结果
				if (hits.total > 0) {
					jsonObj['totalCount'] = hits.total;
					var arr = hits.hits;
					for ( var key in arr) {
						this._processFieldsData(arr[key]);
						if (callback && typeof (callback) === "function") {
							callback(v._source);
						}
						jsonObj['result'].push(arr[key]._source);
					}

				}
			} else if (dataObj._source) {// 返回单个结果
				jsonObj = dataObj._source;
			} else if (dataObj.error) {// 搜索过程出错
			} else {// 处理过程出错
			}
			return jsonObj;
		}

		,
		/**
		 * 指定fields后，ES返回的结果在fields里，且格式不同于_source，本函数将fields格式的数据parse为_source格式并将数据放入
		 * _source里
		 */
		_processFieldsData : function(fieldsData) {
			if (fieldsData._source && !fieldsData.fields) {
				return;
			}
			for ( var fieldName in fieldsData.fields) {
				var value = fieldsData.fields[fieldName];
				if ($.isArray(value)) {
					if (value.length === 1) {
						fieldsData.fields[fieldName] = value[0];
					}
				}
			}
			fieldsData._source = fieldsData.fields;
			// alert(fieldsData.fields);
		},

		/**
		 * 将搜索引擎返回的json结果转化为page格式的json
		 */
		filterStoreData : function(dataObj, page) {
			var resultJson = {};
			if (dataObj.success == false) {
				alert(dataObj.msg);
				resultJson = dataObj;
			} else if (dataObj.error) {
				alert(dataObj.error);
				resultJson = dataObj;
			} else {
				resultJson = this.parseESJson(dataObj, page, '\t');
			}
			return resultJson;
		},

		/**
		 * 根据传入值对构建查询片段(忽略值为空的字段) params - 多个key:value
		 */
		buildQuery4Equals : function(params) {
			var s = '{"bool":{"must":[';
			for ( var key in params) {
				var value = params[key];
				if (!U.isEmpty(value)) {
					s += '{"term":{"' + key + '":"' + value + '"},';
				}
			}
			if (s.endsWiths(',')) {
				s = s.substring(0, s.length);
			}
			s += ']}}';
			return s;
		},

		/**
		 * 简易ES查询构造器，主要做了空值的判定。支持must, must_not, should这3种查询条件。
		 */
		QueryBuilder : function() {
			this._query = {
				"bool" : {}
			};

			this._mustBuf = [];

			this._must_notBuf = [];

			this._shouldBuf = [];

			this._minimum_should_match = 1;

			this.must = function(field, value) {
				return this._addQueryItem(this._mustBuf, field, value);
			};

			this.must_not = function(field, value) {
				return this._addQueryItem(this._must_notBuf, field, value);
			};

			this.should = function(field, value) {
				return this._addQueryItem(this._shouldBuf, field, value);
			};

			this._addQueryItem = function(buf, field, value) {
				var theField = field;
				if ($.isArray(field)) {
					theField = field.join(',');
				}
				if (!U.isEmpty(value) && !U.inArray(value, this.EMPTY)) {
					var q = {};
					q[theField] = value;
					buf.push(q);
				}
				return this;
			};

			this.minShouldMatch = function(minValue) {
				this._minimum_should_match = minValue;
				return this;
			};

			this._buildQuery = function(buf) {
				var query = [];
				U.each(buf, function(item) {
					for ( var field in item) {
						var value = item[field];
						switch (field) {
						case this.QueryType.RANGE:
							var jsonValue = U.isString(value) ? JSON
									.parseJson(value) : value;
							query.push({
								"range" : jsonValue
							});
							break;
						default:
							if (U.isString(value)) {
								if (field.contains(',')) {// 多字段匹配
									query.push({
										"multi_match" : {
											"query" : value,
											"fields" : field.split(','),
											"zero_terms_query" : "all"
										}
									});
								} else {// 单字段匹配
									if (value.contains(',')) {
										var matchQuery = {};
										var sValue = '[' + value + ']';
										matchQuery[field] = JSON
												.parseJson(sValue);
										matchQuery['minimum_should_match'] = 1;
										query.push({
											"terms" : matchQuery
										});
									} else {
										var matchQuery = {};
										matchQuery[field] = {
											"query" : value,
											"zero_terms_query" : "all"
										};
										query.push({
											"match" : matchQuery
										});
									}
								}
							} else if ($.isNumeric(value)) {
								var matchQuery = {};
								matchQuery[field] = value;
								query.push({
									"term" : matchQuery
								});
							} else {
								var matchQuery = {};
								matchQuery[field] = value;
								query.push({
									"match" : matchQuery
								});
							}
						}
					}
				});
				return query;
			};

			this.build = function(formatChar) {
				var mustQuery = this._buildQuery(this._mustBuf);
				var must_notQuery = this._buildQuery(this._must_notBuf);
				var shouldQuery = this._buildQuery(this._shouldBuf);
				if ($.isEmptyObject(mustQuery)
						&& $.isEmptyObject(must_notQuery)
						&& $.isEmptyObject(shouldQuery)) {
					this._query = {
						"match_all" : {}
					};
				} else {
					if (!$.isEmptyObject(mustQuery)) {
						this._query["bool"]["must"] = mustQuery;
					}
					if (!$.isEmptyObject(must_notQuery)) {
						this._query["bool"]["must_not"] = must_notQuery;
					}
					if (!$.isEmptyObject(shouldQuery)) {
						this._query["bool"]["should"] = shouldQuery;
						this._query["bool"]["minimum_should_match"] = this._minimum_should_match;
					}
				}
				return U.parseJson(this._query, formatChar);
			};
		}

	}
	/**
	 * ES 搜索引擎工具包 E 用变量E调用
	 */
	window.E = demo.ES;

	/**
	 * String 对象扩展
	 */

	/**
	 * Sting.contains(s|char)
	 */
	if (!String.prototype.contains) {
		String.prototype.contains = function() {
			return String.prototype.indexOf.apply(this, arguments) !== -1;
		};
	}
	;

	/**
	 * 字符串去空格
	 */
	if (!String.prototype.trim) {
		String.prototype.trim = function() {
			return this.replace(/(^\s*)|(\s*$)/g, "");
		}
	}

	/**
	 * Sting.leftPad(s|char, len)
	 */
	if (!String.prototype.leftPad) {
		String.prototype.leftPad = function(padChar, len) {
			var str = this;
			var strLen = str.toString().length;
			while (strLen < len) {
				str = padChar + str;
				strLen++;
			}
			return str;
		};
	}
	;
	/***************************************************************************
	 * replaceAll： 替换字符串中的字符。 用法： yourstring.replaceAll("要替换的字符", "替换成什么"); 例子:
	 * "cssrain".replaceAll("s", "a"); " cs sr ai n".replaceAll(" ", "");
	 **************************************************************************/
	if (!String.prototype.replaceAll) {

		String.prototype.replaceAll = function(AFindText, ARepText) {
			var raRegExp = new RegExp(AFindText, "g");
			return this.replace(raRegExp, ARepText);
		}
	}

	/***************************************************************************
	 * 计算字符串的真正长度 //String有个属性length，但是它不能区分英文字符，
	 * //计算中文字符和全角字符。但是在数据存储的时候中文和全角都是用两个字节来存储的，
	 * //所有需要额外处理一下。自己写了个函数，返回String正真的长度. 用法： <input type="text" name="rain"
	 * id="rain" /> <input type="button" id="test" value="test" onclick="alert(
	 * document.getElementById('rain').value.codeLength() )"/>
	 **************************************************************************/

	if (!String.prototype.codeLength) {
		String.prototype.codeLength = function() {
			var len = 0;
			if (this == null || this.length == 0)
				return 0;
			var str = this.replace(/(^\s*)|(\s*$)/g, "");// 去掉空格
			for (i = 0; i < str.length; i++)
				if (str.charCodeAt(i) > 0 && str.charCodeAt(i) < 128)
					len++;
				else
					len += 2;
			return len;
		}

	}
	/**************
	//编码HTML  和  解码Html。
	//在评论的时候为了防止用户提交带有恶意的脚本，可以先过滤HTML标签，过滤掉双引号，单引号，符号&，符号<，符号
	用法：
	<input type="text" name="rain" id="rain" />
	<input type="button" value="test" onclick=" document.getElementById('rain2').value= document.getElementById('rain').value.htmlEncode()  "/>
	<input type="text" name="rain2" id="rain2" />
	<input type="button" value="test" onclick=" document.getElementById('rain').value= document.getElementById('rain2').value.htmlDecode()  "/>
	**************/
	if (!String.prototype.htmlEncode) {
		String.prototype.htmlEncode = function() {
			return this.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(
					/>/g, "&gt;").replace(/\"/g, "&#34;").replace(/\'/g,
					"&#39;");
		}
	}

	if (!String.prototype.htmlDecode) {
		String.prototype.htmlDecode = function() {
			return this.replace(/\&amp\;/g, '\&').replace(/\&gt\;/g, '\>')
					.replace(/\&lt\;/g, '\<').replace(/\&quot\;/g, '\'')
					.replace(/\&\#39\;/g, '\'');
		}
	}
	
	
	
	
	
	
	
	/**
	 * Date 对象扩展
	 */

	/**
	 * 返回当前日期在一周中的第一天(1 - 7)
	 */
	if (!Date.prototype.dayOfWeek) {
		Date.prototype.dayOfWeek = function() {
			var n = this.getDay();
			return n === 0 ? 7 : n;
		};
	}
	;

	/**
	 * 返回指定年月的最大天数
	 */
	if (!Date.maxDayOfDate) {
		Date.maxDayOfDate = function(year, month) {
			return new Date(year, month + 1, 0).getDate();
		};
	}
	;

	/**
	 * 返回当前日期所在月的最大天数
	 */
	if (!Date.prototype.maxDayOfDate) {
		Date.prototype.maxDayOfDate = function(d) {
			d = d || this;
			return Date.maxDayOfDate(d.getFullYear(), d.getMonth());
		};
	}
	;

	/**
	 * 截掉日期的天之后的部分
	 */
	if (!Date.truncateToDay) {
		Date.truncateToDay = function(d) {
			d = d || new Date();
			return new Date(d.getFullYear(), d.getMonth(), d.getDate());
		};
	}
	;

	/**
	 * 截掉日期的天之后的部分
	 */
	if (!Date.prototype.truncateToDay) {
		Date.prototype.truncateToDay = function() {
			return Date.truncateToDay(this);
		};
	}
	;

	/**
	 * 是否是闰年
	 */
	if (!Date.isLeapYear) {
		Date.isLeapYear = function(iYear) {
			return iYear % 4 == 0 && iYear % 100 != 0 || iYear % 400 == 0;
		};
	}
	;

	/**
	 * 是否是闰年
	 */
	if (!Date.prototype.isLeapYear) {
		Date.prototype.isLeapYear = function() {
			return Date.isLeapYear(this.getFullYear());
		};
	}
	;

	/**
	 * 返回两个日期相差的天数
	 */
	if (!Date.daysBetween) {
		Date.daysBetween = function(d1, d2) {
			var dd1 = U.isDate(d1) ? d1 : new Date(Date.parse(d1));
			var dd2 = U.isDate(d2) ? d2 : new Date(Date.parse(d2));

			var ddd1 = new Date(dd1.getFullYear(), dd1.getMonth(), dd1
					.getDate());
			var ddd2 = new Date(dd2.getFullYear(), dd2.getMonth(), dd2
					.getDate());
			return parseInt((ddd2 - ddd1) / 1000 / 60 / 60 / 24);
		};
	}
	;

	/**
	 * 
	 */
	Date.prototype.addMillisecond = function(offset) {
		return new Date(this.getTime() + offset);
	};

	Date.prototype.addSecond = function(offset) {
		return this.addMillisecond(offset * 1000);
	};

	Date.prototype.addMinute = function(offset) {
		return this.addSecond(offset * 60);
	};
	Date.prototype.addHour = function(offset) {
		return this.addMinute(60 * offset);
	};

	Date.prototype.addDay = function(offset) {
		return this.addHour(offset * 24);
	};

	Date.prototype.addMonth = function(offset) {
		return new Date(this.getFullYear(), this.getMonth() + offset, this
				.getDate(), this.getHours(), this.getMinutes(), this
				.getSeconds());
	};

	Date.prototype.addYear = function(offset) {
		return new Date(this.getFullYear() + offset, this.getMonth(), this
				.getDate(), this.getHours(), this.getMinutes(), this
				.getSeconds());
	};

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
			// millisecond
			}
			if (/(y+)/.test(format)) {
				format = format.replace(RegExp.$1, (d.getFullYear() + "")
						.substr(4 - RegExp.$1.length));
			}
			for ( var k in o) {
				if (new RegExp("(" + k + ")").test(format)) {
					format = format.replace(RegExp.$1,
							RegExp.$1.length == 1 ? o[k] : ("00" + o[k])
									.substr(("" + o[k]).length));
				}
			}
			return format;
		};
	}
	;

	/**
	 * Date.format(formatString)
	 */
	if (!Date.prototype.format) {
		Date.prototype.format = function(format) {
			return Date.format(this, format);
		};
	}
	;

})(window, $);