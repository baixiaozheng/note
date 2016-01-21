(function($) {
	
	
		/**
		 * bootstrap-bootstrapTable
		 * @param options
		 */
		$.fn.commonbootstrapTable = function(options) {
			var settings = {
				method: 'get',
				pagination: true,
				clickToSelect: true,
				sidePagination: "server",
				pageList: "[5, 10, 20, 50, 100, 200, 500, 1000]"
			};
			options = $.extend(settings, options);
			with({
				dom: this
			}) {
				options.onLoadSuccess = function(r) {
					if (r.code == 201) {
						window.location.href = r.url;
						return;
					} else if (r.code != 200) {
						//判断code类型，做相应处理
						alert(r.message);
					} else {
						var d = r.data;
						var data = {};
						data.total = d.totalCount;
						data.rows = d.result;
						$(dom).bootstrapTable('load', data);
					}
				}
			}
			$(this).bootstrapTable(options);
		}
	

})(jQuery);

(function($) {
	
/**
	 * 对Date的扩展，将 Date 转化为指定格式的String 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q)
	 * 可以用 1-2 个占位符 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) eg: (new
	 * Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 (new
	 * Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04 (new
	 * Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04 (new
	 * Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04 (new
	 * Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
	 */
	Date.prototype.format = function(fmt) {
		var o = {
			"M+": this.getMonth() + 1, // 月份
			"d+": this.getDate(), // 日
			"h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
			"H+": this.getHours(), // 小时
			"m+": this.getMinutes(), // 分
			"s+": this.getSeconds(), // 秒
			"q+": Math.floor((this.getMonth() + 3) / 3), // 季度
			"S": this.getMilliseconds()
				// 毫秒
		};
		var week = {
			"0": "/u65e5",
			"1": "/u4e00",
			"2": "/u4e8c",
			"3": "/u4e09",
			"4": "/u56db",
			"5": "/u4e94",
			"6": "/u516d"
		};
		if (/(y+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
		}
		if (/(E+)/.test(fmt)) {
			fmt = fmt
				.replace(
					RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "") + week[this.getDay() + ""]);
		}
		for (var k in o) {
			if (new RegExp("(" + k + ")").test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			}
		}
		return fmt;
	};
})(jQuery);

/**
 * jQuery扩展
 */
(function($) {
	$.url = {};
	/**
	 *  在url字符串中提取参数值
	 * @param urlStr    url字符串
	 * @param name  参数名称
	 * @returns 参数值
	 */
	$.url.extractParamByStr = function(urlStr, name) {
		var i = urlStr.indexOf("?");
		if (-1 != i) {
			var searchStr = urlStr.substr(i + 1);
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = searchStr.match(reg);
			if (r != null)
				return unescape(r[2]);
		}
		return null;
	};
	$.url.getURLParams = function(urlStr) {
		var params = urlStr.split('?').pop().split('&'); //params:?id,date   
		var args = new Object();
		for (var i = 0; i < params.length; i++) {
			var pos = params[i].indexOf('='); //查找name=value   
			if (pos == -1) continue; //如果没有找到就跳过   
			var key = params[i].substring(0, pos); //提取name  
			var value = params[i].substring(pos + 1); //提取value   
			args[key] = unescape(value); //存为属性   
		}
		return args;
	};
	/**
	 * 获取浏览器url参数的参数值
	 * @param name  浏览器url参数
	 * @returns
	 */
	$.url.getParam = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	};
	/**
	 * 给浏览器添加参数并重定向
	 * @param key   新参数key
	 * @param value 新参数value
	 */
	$.url.addParam = function(key, value) {
		var url = location.href;
		// 去掉末尾的#符号
		url = url.replace(/#/g, '');

		var newUrl = '';
		if (-1 == url.indexOf('?')) {
			newUrl = url + '?' + key + '=' + value;
		} else {
			var cvalue = $.url.getParam(key);
			if (null == cvalue) {
				newUrl = url + '&' + key + '=' + value;
			} else {
				var keyStrLength = ("?" + key + "=").length;
				var keyIndex = url.indexOf("?" + key + "=") + keyStrLength;
				if (-1 == keyIndex) {
					keyIndex = url.indexOf("&" + key + "=") + keyStrLength;
				}
				newUrl = url.substring(0, keyIndex) + value;
				// newUrl = url.replace(key + '=' + cvalue, key + '=' + value);
			}
		}
		location = newUrl;
	};

	/**
	 * 自定义加载时的效果
	 * @param options
	 */
	$.fn.loading = function(options) {
		$(this).html('loading...');
	};

	/**
	 * 将表单序列化成json对象
	 * @returns {{}}
	 */
	$.fn.serializeJson = function() {
		var serializeObj = {};
		var array = this.serializeArray();
		var str = this.serialize();
		$(array).each(function() {
			if (serializeObj[this.name]) {
				if ($.isArray(serializeObj[this.name])) {
					serializeObj[this.name].push(this.value);
				} else {
					serializeObj[this.name] = [serializeObj[this.name], this.value];
				}
			} else {
				serializeObj[this.name] = this.value;
			}
		});
		return serializeObj;
	};

})(jQuery);



/**
 * 常用工具插件扩展
 */
(function($) {
	if ('undefined' == typeof(common)) {
		window.common = $.extend({
			main_wrap_id: "main_wrap", //主页面容器标签id属性
			current_main_url: null, //当前主页面的url
			current_main_detail_id: null, //当页面是详情页时存储详情页id值
			current_main_params: null //当页面是详情页时存储传参的参数
		}, {});
	}

	/**
	 * 通用ajax请求，可实现指定区域的加载图效果
	 * 适用范围：请求失败时只弹出错误提示
	 * @param options
	 * {loadDom：设置后指定区域的加载图效果}
	 */
	common.ajax = function(options) {
		//是否显示加载图效果
		if ('undefined' != typeof(options.loadDom)) {
			$(options.loadDom).loading();
		}

		//本地无缓存权限列表则同步ajax请求
		//权限判断
		//有权放行
		//执行请求
		var callback = options.success;
		options.success = function(r) {
			if (typeof(r) == "string") {
				r = $.parseJSON(r);
			}
			if (r.code == 201) {
				window.location.href = r.url;
				return;
//			} else if (r.code == gm.httpCode.no_permission) {
//				gm.alert({
//					content: r.message
//				});
//			} else if (r.code == gm.httpCode.no_font_permission) {
//				gm.alert({
//					content: r.message
//				});
//			} else if (r.code == gm.httpCode.delete_exists) {
//				gm.alert({
//					content: r.message
//				});
//			} else if (r.code == gm.httpCode.sys_error) {
//				gm.alert({
//					content: r.message
//				});
//			} else if (r.code != gm.httpCode.ok && !$.isEmptyObject(r.message)) {
//				gm.alert({
//					content: r.message
//				});
			} else {
				callback(r.data);
			}
		}
		options.error = function(r) {
			if (typeof(r) == "string") {
				r = $.parseJSON(r);
			}
			if (r.code == 201) {
				window.location.href = r.url;
				return;
//			} else if (r.code == gm.httpCode.no_permission) {
//				gm.alert({
//					content: r.message
//				});
//			} else if (r.code == gm.httpCode.menu_name_exists) {
//				gm.alert({
//					content: r.message
//				});
//			} else if (r.code == gm.httpCode.no_font_permission) {
//				gm.alert({
//					content: r.message
//				});
//			} else if (r.code == gm.httpCode.sys_error) {
//				gm.alert({
//					content: r.message
//				});
//			} else if (r.code != gm.httpCode.ok && !$.isEmptyObject(r.message)) {
//				gm.alert({
//					content: r.message
//				});
			}
		}
		$.ajax(options);
};
})(jQuery);