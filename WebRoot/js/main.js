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
        var http = "http://"+window.location.host;
        $.ajax({
            type: "post",
            url: http+"/testToken/servlet/signin",
            data: user,
            dataType: 'json',
            success: function(message) {
                console.log(message.status)
                if( message.status == 1) {
                    alert(111);
                }
                else {
                    alert("登录失败"+message.msg);
                }
            },
            error: function() {
                alert("服务器异常");
            }
        })
    })
});
