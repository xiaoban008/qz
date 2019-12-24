var ab = function () {
};
jQuery.extend(ab.prototype,
    jQuery.extend(new AjaxPro.AjaxClass(), {
        sessionValue: function (roomno) {
            return this.invoke("sessionValue", {"roomno": roomno},
                null);
        }
        , url: '/ajaxpro/YuYue.Web.kyApplyLog.Choose,YuYue.Web.ashx'
    }));
var a = new ab();
a.sessionValue('00000001-3');
window.location.reload();




//-----------------------------
//访问 http://seat.heuet.edu.cn:8091/yjxvalidate.aspx?Lib_UserOpenID=odsLhsuuYKLsmu0BInyXNjDul-Zc
//访问 http://seat.heuet.edu.cn:8091/kyApplyLog/Seats.aspx

// class  = mainbox



// 1. 创建XMLHttpRequest对象
var xmlHttp = new XMLHttpRequest();
// 2. 调用open方法获取跟服务器的连接
/*
    method: 请求方式  get post
    url：请求路径
    async：表示同步请求还是异步请求  true:异步
    */
xmlHttp.open("GET",
    "http://seat.heuet.edu.cn:8091/yjxvalidate.aspx?Lib_UserOpenID=odsLhsuuYKLsmu0BInyXNjDul-Zc", true);

// 3. 调用send方法, 向服务器发起请求
// 如果是post请求, 那么需要在send方法中带请求参数
// 如果是get请求, 那么参数为null
xmlHttp.send(null);
// 4. 注册一个监听器
/*
    不断地去监听请求的过程返回的一个状态码

    在这个时候我们只需要关注readyState == 4的情况，这个时候说明服务器完成了响应
    我们还需要关注另外一个状态码  status是服务器响应过来的, 需要status == 200
 */
xmlHttp.onreadystatechange = function () {
//            alert(xmlHttp.readyState);
    if (xmlHttp.readyState==4&&xmlHttp.status == 200) {
        //此时说明响应完毕  我们可以通过xml.responseText 来获取服务器的响应内容(文本内容)
        $("div").html(xmlHttp.responseText);
    }
};


var Issf=YuYue.Web.kyApplyLog.button.PdSf().value;
var IsYx=YuYue.Web.kyApplyLog.button.Pdyx().value;
var Ish=YuYue.Web.kyApplyLog.button.Pdh().value;
var IsYh=YuYue.Web.kyApplyLog.button.Pdyh().value;
console.log("永久黑名单"+IsYh);
console.log("黑名单"+Ish);
console.log("释放"+Issf);
console.log("已有"+IsYx);