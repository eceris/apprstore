/**
 * @author vigor8787
 */

'use strict'

App.factory('tagService', [ '$http', '$q', function($http, $q) {

	return {
		createTag : function(tag) {
			return $http.post('/tag/create', tag).then(
				  function(response) {					
					console.log('creating tag success');
					return response;
				  }, function(errResponse) {
					console.error('Error while creating tag');
					return $q.reject(errResponse)
				  }
			);
		},
		
		deleteTag : function(tag) {
			return $http.post('/tag/delete', tag).then(
				  function(response) {
					alert('deleting Tag success');
					console.log('deleting tag success');
					return response;
				  }, function(errResponse) {
					console.error('Error while deleting tag');
					return $q.reject(errResponse)
				  }
			);
		},
		
		
		selectTags : function($query) {
			return $http.post('/tag/list',{ cache:true }).then(
				  function(response) {
					var tags = response.data;					  				

					return tags.filter(function(tag) {
						return tag.name.toLowerCase().indexOf($query.toLowerCase()) != -1;
			        });				    
				  	}, function(errResponse) {
				  		console.error('Error while autocomplete listing tag');
				  		return $q.reject(errResponse)
				  	}
			);
		}

	};
} ]);
