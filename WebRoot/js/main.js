$(function() {
    var user = {
        act: "signin",
        username: "",
        password: ""
    }
    var info = {
        act: "set",
        telephone: "",
        nickname: ""
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
                    alert("登录成功");
                    sessionStorage.token = message.data.token;
                    $('.my-container').html(['<form class="form-horizontal" role="form" id="signin_form">',
'    <div class="form-group header">',
'        <h1>Set</h1>',
'    </div>',
'    <div class="form-group">',
'        <label for="telephone" class="col-sm-2 control-label">Telephone</label>',
'        <div class="col-sm-10">',
'            <input type="text" class="form-control" id="telephone" placeholder="Telephone">',
'        </div>',
'    </div>',
'    <div class="form-group">',
'        <label for="nickname" class="col-sm-2 control-label">Nickname</label>',
'        <div class="col-sm-10">',
'          <input type="text" class="form-control" id="nickname" placeholder="Nickname">',
'        </div>',
'    </div>',
'    <div class="form-group">',
'        <div class="col-sm-offset-2 col-sm-10">',
'          <button type="button" id="set" class="btn btn-default">Set</button>',
'        </div>',
'    </div>',
'    <div class="form-group">',
'        <div class="col-sm-offset-2 col-sm-10">',
'          <button type="button" id="signout" class="btn btn-default">Sign out</button>',
'        </div>',
'    </div>',
'</form>'].join(""));
                    $('#set').bind('click','li',function() {
                        alert(555);
                        info.telephone = $('#telephone').val();
                        info.nickname = $('#nickname').val();
                        var token = sessionStorage.token;
                        console.log(info);
                        $.ajax({
                            type: "post",
                            url: http+"/testToken/servlet/signin",
                            data: info,
                            headers: {
                                "Authorization": token
                            },
                            dataType: "json",
                            success: function(message) {
                                if(message.status == 1) {
                                    alert("添加成功");
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
