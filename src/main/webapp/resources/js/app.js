'use strict';

var App = angular.module('apprstore', ['ui.bootstrap', 'ngRoute', 'ngJsTree', 'ngTagsInput', 'kendo.directives']);

App.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
        when('/users', {
            templateUrl : 'resources/template/users.html'
        }).
        when('/user', {
            templateUrl : 'resources/template/user.html'
        }).
        when('/documents', {
            templateUrl: 'resources/template/documents.html'
        }).
        when('/document', {
            templateUrl: 'resources/template/document.html'
        }).
        otherwise({
            redirectTo: '/'
        });
    }
]);