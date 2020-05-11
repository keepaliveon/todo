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
        <h2 class="col-6">事项列表</h2>
<#--        <div class="col-6">-->
<#--            <a href="/textbook_add" class="btn btn-primary float-right">录入新教材信息</a>-->
<#--        </div>-->
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>ID</th>
                    <th>分类</th>
                    <th>标题</th>
                    <th>日期</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <tbody>
                <#list todoList as todo>
                    <tr>
                        <td>${todo_index+1}</td>
                        <td>${todo.id!}</td>
                        <td>${todo.category!}</td>
                        <td>${todo.text!}</td>
                        <td>${todo.date?date}</td>
                        <td>${todo.done?string("已完成","未完成")}</td>
                        <td>
                            <a class="btn btn-success btn-sm" href="/send?id=${todo.id!}">提醒</a>
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