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
            <h4>${todo.id}待办事项提醒成功</h4>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>