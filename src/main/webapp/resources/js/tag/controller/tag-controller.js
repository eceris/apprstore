/*
 * *  Tag 등록 Controller
 * *
 * */

'use strict'

App.controller('tagController', [
                  '$scope',
                  '$http',
				  'tagService',
		function($scope, $http, tagService) {
	
				self.tag = {		
					name : '',
					documentId: ''
				};
				
				self.createTag = function(tag) {
					tagService.createTag(tag).then(
							console.log('tag Creating'), 
							function(errResponse) {
								console.error('Error while creating Tag');
							});
				};
					
				
			  $scope.tags = [];			  
			  
			  //태그 추가 엔터 이벤트
			  $scope.onTagAddedForRegister = function(param) {
				  tag.name = param.name;
				  self.createTag(tag);
			  }
			  
			  //태그 추가 엔터 이벤트
			  $scope.onTagUpdateForList = function(param) {
				  $scope.updateTag();
			  }

			  //태그 텍스트 입력하는 동안에 발생하는 이벤트
			  $scope.loadTags = function($query) {
					return tagService.selectTags($query);				  
			  };
}]);