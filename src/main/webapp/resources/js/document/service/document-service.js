/**
 * @author vigor8787
 */

'use strict'

App.factory('documentService', [ '$http', '$q', function($http, $q) {

	return {

		createDocument : function(document) {			
			return $http.post('/document/create', document).then(
				  function(response) {
					alert('creating document success');
					console.log('creating document success');
					return response;
				  }, function(errResponse) {
					console.error('Error while creating document');
					return $q.reject(errResponse)
				  }
			);
		},
		
		selectDocuments : function(document) {		
			return $http.post('/document/list', document).then(
				  function(response) {
					//console.log('creating document success');
					return response.data;
				  }, function(errResponse) {
					console.error('Error while creating document');
					return $q.reject(errResponse)
				  }
			);
		}

	};
} ]);
