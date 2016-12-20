/**
 * Created by Administrator on 2016/10/14.
 * 系统应用 统一抽取配置
 */

/** js请求参数提交contentType
 * 设置请求头信息并格式化参数
 * */


//表单格式请求
var configForm = {
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    },
    transformRequest: function (data) {
        if (!data) return undefined;
        return toBodyString(data);
    }
};
//json格式请求
var configJson = {
    headers:  {
        'Content-Type': 'application/json;charset=UTF-8'
    },
    transformRequest: function (data) {
        if (!data) return undefined;
        return JSON.stringify(data);
    }
};

function toBodyString(obj) {
    var ret = [];
    for (var key in obj) {
        var values = obj[key];
        if (values && values.constructor == Array) { //数组
            var queryValues = [];
            for (var i = 0, len = values.length, value; i < len; i++) {
                value = values[i];
                queryValues.push(toQueryPair(key, value));
            }
            ret = ret.concat(queryValues);
        } else { //字符串
            ret.push(toQueryPair(key, values));
        }
    }
    return ret.join('&');
}
function toQueryPair(key, value) {
    if (typeof value == 'undefined') {
        return key;
    }
    return key + '=' + encodeURIComponent(value === null ? '' : String(value));
}

function judgeInt(num){
    var regex =/^[0-9]*[1-9][0-9]*$/;
    if(regex.test(num)){
        return true;
    }
    return false;
// ”^\\d+$”　　//非负整数（正整数   +   0）
//“^[0-9]*[1-9][0-9]*$”　　//正整数
//“^((-\\d+)|(0+))$”　　//非正整数（负整数   +   0）
//“^-[0-9]*[1-9][0-9]*$”　　//负整数
//“^-?\\d+$”　　　　//整数
//“^\\d+(\\.\\d+)?$”　　//非负浮点数（正浮点数   +   0）
//“^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$”　　//正浮点数
//“^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$”　　//非正浮点数（负浮点数   +   0）
//“^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$”　　//负浮点数
//“^(-?\\d+)(\\.\\d+)?$”　　//浮点数
}
/**
 * 获取地址栏内指定的参数
 * @param name
 * @returns {*|string}
 */
var getSearch = function (name) {
    var reg = new RegExp('(?:^|&)' + name + '=([^&]*)(?:&|$)', 'i');
    var s = decodeURI(location.search);//解决参数含有中文问题
    return ((s.split('?')[1] || '').match(reg) || [])[1] || '';
};


var secDemoApp = angular.module("SecDemoApp",[]);

//http请求响应统一处理
secDemoApp.config(["$locationProvider", "$httpProvider", function ($locationProvider, $httpProvider) {
    $httpProvider.defaults.headers.post['Accept'] = '*/*';
    $httpProvider.interceptors.push('InterceptorHttp');
}]);

//http请求 和 响应的拦截,统一处理
secDemoApp.factory("InterceptorHttp", [function () {
    return {
        responseError: function (response) {
            //服务器响应请求失败：server status !=200
            // 请求未进入方法或者有异常
            if(response.status == 403){
                // security拦截后返回的data为异常页面内容,这里不使用data,判断server status=403直接转到静态拒绝页面
                window.location.href =  baseUrl+viewPath.denied;
            }
            //其他原样返回
            if (response.status == 500) {
                return response;
            }
            if (response.status == 404) {
                return response;
            }
            return response;
        },
        request: function (request) {
            //前端请求发起,处理request后再请求服务器
            return request;
        },
        response: function (response) {
            //服务器响应请求成功：请求进入方法 server status =200
            // 处理请求数据或者逻辑错误, 异常被catch后的响应,回送数据code值自定义
            //这里 全部原样返回,如果有需要，再单独判断并做处理
            if (response.data && response.code == 500) {
                return response;
            }
            //统一的错误处理
            if (response.data && response.code == 403) {
                return response;
            }
            return response;
        }
    };
}]);
