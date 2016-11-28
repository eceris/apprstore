/**
 * @author vigor8787
 */

'use strict'

App.factory('categoryService', [ '$http', '$q', function($http, $q) {

	return {
		createCategory : function(category) {
			return $http.post('/category/create', category).then(
				  function(response) {					
					console.log('creating category success');
					return response;
				  }, function(errResponse) {
					console.error('Error while creating category');
					return $q.reject(errResponse)
				  }
			);
		},
		
		deleteCategory : function(categoryId) {
			return $http.delete('/category/delete', {params:{categoryId: categoryId}}).then(
				  function(response) {		
					console.log('deleting category success');
					return response.status;
				  }, function(errResponse) {
					console.error('Error while deleting category');
					return $q.reject(errResponse)
				  }
			);
		},
		
		
		selectCategories : function() {
			return $http.get('/category/list').then(
				  function(response) {
					return response.data;
				  }, function(errResponse) {
					console.error('Error while listing category');
					return $q.reject(errResponse)
				  }
			);
		}

	};
} ]);
