$(function () {
    $('.weui-tabbar__item').on('click', function () {
        $(this).addClass('weui-bar__item_on').siblings().removeClass('weui-bar__item_on');
        var tabId = $(this).attr('href');
        $('.index_weui-tab__panel').find(tabId).show().siblings().hide();
        // $('.index_weui-tab__panel').find(tabId).removeClass('index_weui__panel_display_none')
        //     .siblings().addClass('index_weui__panel_display_none');
    });
});
// 主页面切换：tabbar 显示：
let phoneFlag = false;
let passwordFlag = false;
let rePasswordFlag = false;
let submitId = $("#registerCustomer");

// 验证前端输入的手机号的格式:
function checkPhone() {
    // noinspection JSJQueryEfficiency
    let phone = $("#phone").val();
    if (!(/^1([3456789])\d{9}$/.test(phone))) {
        $.alert("请输入正确的手机号", "警告");
        $("#phone").val("")
        submitId.addClass('weui-btn_disabled')
    } else {
        phoneFlag = true;
        submitId.removeClass('weui-btn_disabled')
    }
}

// 验证前端密码强度：
function passwordStrong() {
    let pattern = /^(?![^a-zA-Z]+$)(?!\D+$)/;
    let password = $("#password").val();

    if ('' === password || !pattern.test(password)) {
        $.alert("密码由字母数字组成", "警告")
        submitId.addClass('weui-btn_disabled')
    } else {
        passwordFlag = true;
        submitId.removeClass('weui-btn_disabled')
    }
}

// 前端数据验证：
function checkPassword() {
    let password = $("#password").val();
    let rePassword = $("#rePassword").val();
    console.info("密码" + password, "确认密码" + rePassword);
    if (password !== rePassword) {
        $.alert("两次密码不一致", "警告");
        rePasswordFlag = false;
        submitId.addClass('weui-btn_disabled')
    } else {
        rePasswordFlag = true;
        submitId.removeClass('weui-btn_disabled')
    }
}

// 注册客户信息
function doRegister() {
    let type = $("#type").val();
    let phone = $("#phone").val();
    let password = $("#password").val();
    let smsCode = $("#smsCode").val();
    let data = {phone: phone, password: password, smsCode: smsCode, type: type};
    if (phoneFlag && passwordFlag && rePasswordFlag) {
        $.ajax({
            url: '/wechat/manage/doRegister',
            method: 'post',
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: 'JSON',
            async: false,
            success: function (result) {
                if (10000 === result.subCode) {
                    $.toast(result.subMessage);
                    window.location.href = "/wechat/common/center/login";
                } else {
                    $.toast(result.subMessage, "cancel");
                }
            },
            error: function (data) {
            }
        })
    } else {
        $.alert("输入信息有误", "警告")
    }
}

// 获取短信验证码
function requestCode() {
    let phone = $("#phone").val();
    let type = $("#type").val();
    let data = {phone: phone, type: type};

    $.ajax({
        url: '/wechat/common/requestCode',
        method: 'post',
        data: JSON.stringify(data),
        contentType: "application/json",
        dataType: 'JSON',
        async: false,
        success: function (result) {
            if (10000 === result.subCode) {
                $.toast(result.data);
                countDown(60);
            } else {
                $.toast(result.data, "cancel");
            }
        },
        error: function (data) {
        }
    })
}

// 短信倒计时
function countDown(s) {
    if (s <= 0) {
        $("#requestSm").html("重新获取");
        $("#requestSm").attr("disabled", false);
        return;
    }
    $("#requestSm").html(s + "秒");
    setTimeout("countDown(" + (s - 1) + ")", 1000);
}


// weui 上传和预览图片

