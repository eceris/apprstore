/**
 * @author vigor8787
 */
'use strict'

App.controller('userController', [
    '$scope',
    'userService',

    function($scope, userService) {
        var self = this;
        self.user = {
            email: '',
            password: '',
            nickname: '',
        };
        self.createUser = function(user) {
            userService.createUser(user);
        };

        self.submit = function() {
            console.log('Saving new user', self.user);
            self.createUser(self.user);
            self.reset();
        };

        self.reset = function() {
            self.user = {
                email: '',
                password: '',
                nickname: ''
            };
            $scope.register.$setPristine(); // reset Form
        };

    }
]);