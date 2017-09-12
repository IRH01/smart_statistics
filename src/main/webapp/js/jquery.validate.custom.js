// 用户名验证
jQuery.validator.addMethod("username", function(value, element) {
    return this.optional(element) || /^[a-zA-Z0-9_]{4,16}$/.test(value);
}, "只能包括英文字母、数字和下划线");
// 密码验证
jQuery.validator.addMethod("password1", function(value, element) {
    return this.optional(element) || /^[a-zA-Z0-9_]{8,16}$/.test(value);
}, "只能包括英文字母、数字和下划线");
// 文件大小
jQuery.validator.addMethod("filesize", function (value, element, param) {
    var size = getFileSize(element);
    return this.optional(element) || (size<=param);
}, "文件超过指定大小");

// 中文字两个字节
jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {
    var length = value.length;
    for(var i = 0; i < value.length; i++){
        if(value.charCodeAt(i) > 127){
            length++;
        }
    }
    return this.optional(element) || ( length >= param[0] && length <= param[1] );
}, "请确保输入的值在3-15个字节之间(一个中文字算2个字节)");

// 身份证号码验证
jQuery.validator.addMethod("isIdCardNo", function(value, element) {
    var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
//身份证正则表达式(18位)
    var isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/;
    var result = isIDCard1.test(value)||isIDCard2.test(value);
    return this.optional(element) || result;
}, "请正确输入您的身份证号码");

// 手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^1[0-9]{10}$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

// 电话号码验证
jQuery.validator.addMethod("isTel", function(value, element) {
    var tel = /^\d{3,4}-?\d{7,9}$/;    //电话号码格式010-12345678
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的电话号码");

// 联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("isPhone", function(value,element) {
    var length = value.length;
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
    var tel = /^\d{3,4}-?\d{7,9}$/;
    return this.optional(element) || (tel.test(value) || mobile.test(value));

}, "请正确填写您的联系电话");

// 邮政编码验证
jQuery.validator.addMethod("isZipCode", function(value, element) {
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的邮政编码");
jQuery.validator.addMethod("mail", function (value, element) {
    var mail = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$/;
    return this.optional(element) || (mail.test(value));
}, "邮箱格式不对");
jQuery.validator.addMethod("positiveInteger",function(value, element){
    return this.optional(element) || /^[1-9]{1}\d{0,8}$/.test(value);}, "只允许输入正整数");
jQuery.validator.addMethod("imageFile",function(value, element){return this.optional(element) || /(.jpg|.jpeg|.gif|.bmp|.png)$/i.test(value);}, "图片文件格式错误");



var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
function getFileSize(target) {
    var fileSize = 0;
    try {
        if (isIE && !target.files) {
            var filePath = target.value;
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            var file = fileSystem.GetFile(filePath);
            fileSize = file.Size;
        } else {
            fileSize = target.files[0].size;
        }
    }catch(e) {
    }
    return fileSize;
}

jQuery.extend(jQuery.validator.messages, {
    required: "此内容为必填项,请输入!",
    remote: "内容输入错误!",
    email: "E-mail格式错误,请重新输入!",
    url: "网址格式错误,请重新输入!",
    date: "日期格式错误,请重新输入!",
    dateISO: "日期格式错误,请重新输入!",
    number: "请输入合法的数字!",
    digits: "请输入零或正整数!",
    creditcard: "信用卡号格式错误,请重新输入!",
    equalTo: "两次输入不一致,请重新输入!",
    accept: "请输入拥有合法后缀名的字符串!",
    maxlength: jQuery.validator.format("字符串长度不能大于{0}!"),
    minlength: jQuery.validator.format("字符串长度不能小于{0}!"),
    rangelength: jQuery.validator.format("字符串长度只允许在{0}-{1}之间!"),
    range: jQuery.validator.format("输入的数值只允许在{0}-{1}之间!"),
    max: jQuery.validator.format("输入的数值不允许大于{0}!"),
    min: jQuery.validator.format("输入的数值不允许小于{0}!"),
    integer: "请输入合法的整数!",
    positive: "请输入合法的正数!",
    positiveInteger: "请输入合法的正整数!",
    mobile: "手机号码格式错误,请重新输入!",
    phone: "电话号码格式错误,请重新输入!",
    zipCode: "邮政编码格式错误,请重新输入!",
    requiredTo: "此内容为必填项,请输入!",
    username: "只允许包含中文、英文、数字和下划线!",
    prefix: "请输入以 {0} 开头的字符串!",
    lettersonly: "只允许包含字母!",
    alphanumeric: "只允许包含英文、数字和下划线!",
    extension: jQuery.validator.format("请选择后缀为{0}的文件!")
});