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
        .avatar {
            width: 25px;
            height: 25px;
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
                <a class="nav-link active" href="/home">用户管理</a>
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
                    <a class="dropdown-item" href="/password">修改密码</a>
                    <a class="dropdown-item" href="/sign_out">退出登陆</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/sign_out"></a>
            </li>
        </ul>
    </div>
</nav>
<div class="container-fluid">
    <div class="row" style="margin-top: 15px">
        <h2 class="col-6">用户列表</h2>
<#--        <div class="col-6">-->
<#--            <a href="/textbook_add" class="btn btn-primary float-right">录入新教材信息</a>-->
<#--        </div>-->
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>OpenID</th>
                    <th>昵称</th>
                    <th>头像</th>
                    <th>邮箱</th>
                    <th>创建时间</th>
                    <th>更新时间</th>
                    <th>操作</th>
                </tr>
                <tbody>
                <#list users as user>
                    <tr>
                        <td>${user_index+1}</td>
                        <td>${user.openId!}</td>
                        <td>${user.nickName!}</td>
                        <td><img class="img-fluid avatar" src="${user.avatarUrl!}"></td>
                        <td>${user.email!}</td>
                        <td>${user.createTime!}</td>
                        <td>${user.updateTime}</td>

                        <td>
                            <a class="btn btn-success btn-sm" href="/todo?openid=${user.openId!}">查看</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
                </thead>
            </table>
        </div>
    </div>
</div>
</body>
</html>