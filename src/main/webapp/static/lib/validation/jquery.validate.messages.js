
/*
 * 自定义验证方法
 * */
$.validator.addMethod("phone", function(value, element) {
	return this.optional(element) || /^((1[34578]\d{9})|(\d{3,}-?\d{6,}))$/.test(value);
}, "请输入正确的联系电话");
$.validator.addMethod("password", function(value, element) {
	return this.optional(element) || /[a-zA-Z0-9]{6,}/.test(value);
}, "请输入6位及以上字符，区分大小写");



/*自定义验证提示文本
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
$.extend($.validator.messages, {
	required: "这是必填字段",
	remote: "请修正此字段",
	email: "请输入有效的电子邮件地址",
	url: "请输入有效的网址",
	date: "请输入有效的日期",
	dateISO: "请输入有效的日期 (YYYY-MM-DD)",
	number: "请输入有效的数字",
	digits: "只能输入数字",
	creditcard: "请输入有效的信用卡号码",
	equalTo: "你的输入不相同",
	extension: "请输入有效的后缀",
	maxlength: $.validator.format("最多可以输入 {0} 个字符"),
	minlength: $.validator.format("最少要输入 {0} 个字符"),
	rangelength: $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
	range: $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
	max: $.validator.format("请输入小于 {0} 元的金额"),
	min: $.validator.format("请输入大于 {0} 元的金额")
});


