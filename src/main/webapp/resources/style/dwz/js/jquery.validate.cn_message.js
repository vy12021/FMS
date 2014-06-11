jQuery.extend(jQuery.validator.messages, {
    required: "<span style='font-size: 13px;color: red;'>必填</span>",
    remote: "<span style='font-size: 13px;color: red;'>请修正该字段</span>",
    email: "<span style='font-size: 13px;color: red;'>格式不正确</span>",
    url: "<span style='font-size: 13px;color: red;'>格式不正确</span>",
    date: "<span style='font-size: 13px;color: red;'>格式不正确</span>",
    dateISO: "<span style='font-size: 13px;color: red;'>格式不正确(ISO)</span>",
    number: "<span style='font-size: 13px;color: red;'>只能输入数字</span>",
    digits: "<span style='font-size: 13px;color: red;'>只能输入整数</span>",
    creditcard: "<span style='font-size: 13px;color: red;'>格式不正确</span>",
    equalTo: "<span style='font-size: 13px;color: red;'>两次值不匹配</span>",
    accept: "<span style='font-size: 13px;color: red;'>后缀不正确</span>",
    maxlength: jQuery.validator.format("<span style='font-size: 13px;color: red;'>长度最长为{0}</span>"),
    minlength: jQuery.validator.format("<span style='font-size: 13px;color: red;'>长度最少为{0}</span>"),
    rangelength: jQuery.validator.format("<span style='font-size: 13px;color: red;'>长度{0}到{1}之间</span>"),
    range: jQuery.validator.format("<span style='font-size: 13px;color: red;'>大小{0}到{1}之间</span>"),
    max: jQuery.validator.format("<span style='font-size: 13px;color: red;'>最大为{0}</span>"),
    min: jQuery.validator.format("<span style='font-size: 13px;color: red;'>最小为{0}</span>")
});