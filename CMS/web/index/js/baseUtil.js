(function($w) {
	if(typeof $w.BaseUtil === 'undefined')
		var BaseUtil = $w.BaseUtil = {};

	/**
	 * 生成不重复的随机数
	 * @param {Object} len 长度
	 * @param {Object} radix ，基数 abcd-123
	 */
	BaseUtil.uuid = function(len, radix) {
		var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
		var uuid = [],
			i;
		radix = radix || chars.length;

		if(len) {
			// Compact form
			for(i = 0; i < len; i++) uuid[i] = chars[0 | Math.random() * radix];
		} else {
			// rfc4122, version 4 form
			var r;

			// rfc4122 requires these characters
			uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
			uuid[14] = '4';

			// Fill in random data. At i==19 set the high bits of clock sequence as
			// per rfc4122, sec. 4.1.5
			for(i = 0; i < 36; i++) {
				if(!uuid[i]) {
					r = 0 | Math.random() * 16;
					uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
				}
			}
		}

		return uuid.join('');
	}

	/**
	 * 截取地址栏中的参数
	 * @param {Object} name 参数名
	 */
	BaseUtil.GetQueryString = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if(r != null) return unescape(r[2]);
		return null;
	}

	/**
	 * 时间搓转换成字符串
	 * @param {Object} time 时间戳
	 * @param {Object} format 转换格式
	 */
	BaseUtil.toDateString = function(time, format) {
		var t = new Date(time);
		var tf = function(i) {
			return(i < 10 ? '0' : '') + i
		};
		return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a) {
			switch(a) {
				case 'yyyy':
					return tf(t.getFullYear());
					break;
				case 'MM':
					return tf(t.getMonth() + 1);
					break;
				case 'mm':
					return tf(t.getMinutes());
					break;
				case 'dd':
					return tf(t.getDate());
					break;
				case 'HH':
					return tf(t.getHours());
					break;
				case 'ss':
					return tf(t.getSeconds());
					break;
			}
		})
	}

	//全局ajax
	BaseUtil.ajaxSetup = function() {
		var index;
		$.ajax({
			"beforeSend": function() {
				index=layer.load(1, {
					shade: [0.5, "black"]
					
				});
			},
			"complete": function() {
				layer.close(index);
			},
			"error": function() {
				layer.confirm("服务器连接错误,请重试", function() {
					layer.closeAll("dialog");

				});
			}
		});

	}
})(window)