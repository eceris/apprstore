/**
 * @author vigor8787
 */

'use strict'

App.controller("documentListViewCtrl",[
		'$scope',
		'$http',
		'$modal',		
		'documentService',
		'categoryService',
		function($scope,$http,$modal,documentService,categoryService){		  
	
	var self = this;
	
	//결재양식 Data
	self.document = {
			name : '',
			category:{},
			tags:[],		
			version : '',
			html : '',
			htmlWithComponent : '',
			description: ''			
		};
	
	self.category = {
			id:'',
			name:''
	};	
		
	//Category List Select Call in Service
	self.selectDocuments = function(document) {		
		$scope.documents = []; //초기화
		
		documentService.selectDocuments(document).then(
				function(response) {
					for(var i=0; i < response.length ; i++) {
						$scope.documents.push({ id: response[i].id, name: response[i].name, thumbNailImage: 'http://localhost:9000/getImage?path='+response[i].thumbNailImage});
					}					
					self.dataSetInList();
				});
	};
	
	self.dataSetInList = function() {
	    $scope.source = new kendo.data.DataSource({
	        data: $scope.documents ,
	        pageSize: 8
	    });
	};	
	

	$scope.documents = [];
	
	$scope.init = function() {
		self.category.id = 'root';
		self.document.category = self.category;
		self.selectDocuments(self.document);
	};
		
	$scope.selectDocuments = function(category) {
		self.document.category = category;
		self.selectDocuments(self.document);
	};
	
	$scope.updateTag = function() {				
		self.selectDocuments(self.document);
	};
			
	//구현예정
    $scope.select = function() {
    	alert('상세조회');
    }        	
    
    //Tag loading Event
    $scope.loadTags = function(query) {
    	//return $http.get('/tags?query=' + query);
    }
        
	//양식 추가 팝업
	$scope.createDocument = function() {
		console.log('opening popup');
		var modalInstance = $modal.open({
			templateUrl: '/resources/template/document.html',
			controller: 'documentRegisterController as dcRegisterCtrl',
			size:'lg'				
		});		
		
		modalInstance.result.then(function() {		
			//alert('닫힐때');
		});			
	}	
	
	
	//양식 클릭
	$scope.imageClick = function(ClickedDocumentId) {
		alert(ClickedDocumentId);
	};
	
	}
]);





App.controller('documentRegisterController', [
	          		'$scope',          
	          		'$modalInstance',	          		
	          		'documentService',
	          		'categoryService',
	          		function($scope,$modalInstance,documentService,categoryService) {
	          			var self = this;
	          		          		          
	          			//결재양식 model
	          			self.document = {
	          				name : '',
	          				category: {},
	          				tags:[],
	          				version : '',
	          				html : '',
	          				htmlWithComponent : '',
	          				thumNailImage: '',
	          				description: '',
	          				useCount:''
	          			};	          			
	          			//$scope.documents = [];
	          			
	          			$scope.categories = [];
	          			
	          			//결재양식 추가 화면 open Event
	          			$modalInstance.opened.then(function() {
	          				//등록된 카테고리 항목 조회
	          				self.selectCategories();
	          			});
		          				  
	        			//Category List Select Call in Service
	        			self.selectCategories = function() {
	        				categoryService.selectCategories().then(
	        						function(response) {
	        							$scope.categories.push({ id: 'root', name: '전체'});	
	        							for(var i=0; i < response.length ; i++) {
	        								$scope.categories.push({ id: response[i].id, name: response[i].name });
	        							}
	        						});
	        			};
	          			
	          			self.createDocument = function(document) {
	          				documentService.createDocument(document).then(
	          						console.log('usuario creado'), 
	          						function(errResponse) {
	          							console.error('Error while creating Document');
	          						});
	          				//$modalInstance.close();
	          			};
	          			
	          			self.close = function() {
          					$modalInstance.close();
	          			}
	
	          			self.submit = function() {
	          				console.log('Saving new document', self.document);
	          				self.createDocument(self.document);
	          				self.reset();
	          			};
	
	          			self.reset = function() {
		          			self.document = {
			          				name : '',
			          				tags: '',
			          				version : '',
			          				html : '',
			          				htmlWithComponent : '',
			          				description: ''
			          			};	    
	          				//$scope.register.$setPristine(); // reset Form				
	          			};

  }]);