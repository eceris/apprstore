<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>양식스토어</title>
        <%@include file="../jspf/styles.jspf"%>
        <%@include file="../jspf/js.jspf"%>
    </head>
    <body ng-app="apprstore">
        <div class="container">
            <nav class="navbar navbar-default">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed">
                        <span class="sr-only">Toggle navigation</span> <span
                            class="icon-bar"></span> <span class="icon-bar"></span> <span
                            class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="/main">ApprStore</a>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
                                Account <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="/#users">Account List</a></li>
                                    <li><a href="/#user">Account Register</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
                                ApprManage <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="/#documents">Appr List</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
            </nav>
            <div class="container wrapContainer" ng-view></div>
        </div>
        <!-- /container -->
    </body>
</html>