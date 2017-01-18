$(function() {
    var user = {
        username: "",
        password: ""
    }
    function GetJsonData(data) {
        var json = {

        }
    }
    var signinBtn = $("#signin");
    signinBtn.on('click', function() {
        user.username = $('#username').val();
        user.password = $('#password').val();
        console.log(user);
        var host = window.location.href;
        $.ajax({
            type: "post",
            url: host+"/servlet/signin",
            data: user,
            success: function(message) {
                if( message.status == 1) {
                    location.href = host + '/main';
                }
                else {
                    alert("登录失败"+result.msg);
                }
            },
            error: function() {
                alert("服务器异常");
            }
        })
    })
});
