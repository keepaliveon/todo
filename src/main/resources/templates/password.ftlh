<!DOCTYPE html>
<html lang="zh_cn">
<head>
    <title>后台管理系统</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/static/css/bootstrap.css">
    <style>
        th, td {
            white-space: nowrap;
            text-align: center;
        }
    </style>
    <script src="/static/js/jquery.js"></script>
    <script src="/static/js/bootstrap.js"></script>
</head>
<body>
<nav class="navbar bg-dark navbar-expand-md navbar-dark">
    <a class="navbar-brand" href="/home">后台管理系统</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/home">用户管理</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    <#if Session.current??>
                        ${Session.current.username}
                        <#else >
                        未登陆
                    </#if>
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item active" href="/password">修改密码</a>
                    <a class="dropdown-item" href="/sign_out">退出登陆</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/sign_out"></a>
            </li>
        </ul>
    </div>
</nav>
<div class="container" style="padding-top: 15px">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h2>密码修改</h2>
            <form method="post" action="/taoshelf/8088/do_password_mod" onsubmit="return validate()">
                <div class="form-group">
                    <label>旧密码：</label>
                    <input type="password" class="form-control" id="old_password" name="oldPassword"
                           placeholder="请输入原密码" required autocomplete="off">
                </div>
                <div class="form-group">
                    <label>新密码：</label>
                    <input type="password" class="form-control" id="new_password" name="newPassword"
                           onkeyup="validate()" placeholder="请输入新密码" required autocomplete="off">
                </div>
                <div class="form-group">
                    <label>新密码：</label>
                    <input type="password" class="form-control" id="new_password_" onkeyup="validate()"
                           placeholder="请再次输入新密码" autocomplete="off">
                </div>
                <input type="submit" class="form-control btn btn-primary" value="修改密码">
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
<script>
    let password_1 = $("#new_password");
    let password_2 = $("#new_password_");
    let info = $("#info_message");
    function validate() {
        if (password_1.val() !== password_2.val()) {
            info.text("两次密码输入不一致");
            return false;
        } else {
            info.text("");
            return true;
        }
    }
</script>
</html>