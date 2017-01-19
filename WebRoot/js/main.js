$(function() {
    var user = {
        act: "signin",
        username: "",
        password: ""
    }
    var check = {
        act: "check"
    }
    var signinBtn = $("#signin");
    signinBtn.on('click', function() {
        user.username = $('#username').val();
        user.password = $('#password').val();
        var http = "http://"+window.location.host;
        $.ajax({
            type: "post",
            url: http+"/testToken/servlet/signin",
            data: user,
            dataType: 'json',
            success: function(message,textStatus,xhr) {
                if( message.status == 1) {
                    alert("登录成功");
                    sessionStorage.token = xhr.getResponseHeader("Authorization");
                    $('.my-container').html(['<form class="form-horizontal" role="form" id="signin_form">',
'    <div class="form-group header">',
'        <h2>Hi,Nice to meet you</h2>',
'    </div>',
'    <div class="form-group">',
'        <div class="col-sm-offset-2 col-sm-10">',
'          <button type="button" id="token" class="btn btn-primary">check token</button>',
'        </div>',
'    </div>',
'    <div class="form-group">',
'        <div class="col-sm-offset-2 col-sm-10">',
'          <button type="button" id="signout" class="btn btn-danger">signout</button>',
'        </div>',
'    </div>',
'</form>'].join(""));
                    $('#token').bind('click','li',function() {
                        var token = sessionStorage.token;
                        $.ajax({
                            type: "post",
                            url: http+"/testToken/servlet/signin",
                            data: check,
                            headers: {
                                "Authorization": token
                            },
                            dataType: "json",
                            success: function(message) {
                                if(message.status == 1) {
                                    alert("验证成功，您的token是正确的");
                                }else if(message.status == -1 && message.msg == "timeout") {
                                    alert("您的登录已超时，请重新登录");
                                    sessionStorage.removeItem("token");
                                    window.location.href = http + "/testToken";
                                } else {
                                    alert("添加失败");
                                }
                            },
                            error: function() {
                                alert("服务器异常");
                            }
                        });
                    });
                    $('#signout').bind('click',function() {
                        sessionStorage.removeItem("token");
                        window.location.href = http + "/testToken";
                    })
                }
                else {
                    alert("登录失败："+message.msg);
                }
            },
            error: function() {
                alert("服务器异常");
            }
        })
    });
});
