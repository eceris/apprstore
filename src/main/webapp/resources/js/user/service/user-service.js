/**
 * @author vigor8787
 */
'use strict'

App.factory('userService', ['$http', '$q', function($http, $q) {

    return {

        createUser: function(user) {
            return $http.post('/user', user).then(
                function(response) {
                    alert('creating user success');
                    console.log('creating user success');
                    return response.data;
                },
                function(errResponse) {
                    console.error('Error while creating user');
                    return $q.reject(errResponse)
                }
            );
        }

    };
}]);