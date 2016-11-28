/**
 * @author vigor8787
 */

'use strict'

App.controller('categoryController', [
		'$scope',		
		'categoryService',
		'$timeout',
		function($scope,categoryService,$timeout) {		
			
			var self = this;
			
			self.category = {
					id:'',
					name : '',
					parentid : '',
					type : ''
			};					
			
			$scope.categories = [
                            	 /*{ id : 'root', parent : '#', text : '전체', state: { opened: true, disabled:true} }*/
                            	 { id : 'root', text : '전체' , parent : '#', type: 'root' , state: { opened: true} }
            ];
			
			$scope.treeConfig = {
			        core : {
			        multiple : false,
			        animation: 0,
			        themes : { "stripes" : true },
			        error : function(error) {
			            $log.error('treeCtrl: error from js tree - ' + angular.toJson(error));
			        },
			        types : {
			            "root" : { "max_depth" : 2},
			            "folder" : { "max_depth" : 1, "valid_children" : ["root"] }
			        },
			        check_callback : true,
			        worker : true
			        },
			        plugins : ['search','contextmenu','arrPlugins','types','sort','state','dnd']
			};
			
			//Category Create,Update Call in Service
			self.createCategory = function(category) {
				categoryService.createCategory(category).then(
						console.log('create  Category'), 
						function(errResponse) {							
							console.error('Error while creating Category');
						});
			};
			
			//Category Delete Call in Service
			self.deleteCategory = function(categoryId) {		
				if(categoryId == "root") {
					alert("전체 카테고리는 삭제할 수 없습니다.");
					return ;
				}
				if(confirm("해당 카테고리를 삭제하시겠습니까?") == true) {
					return categoryService.deleteCategory(categoryId).then(
							function(response) {					
								return response;
							},
							function(errResponse) {								
								return errResponse;
							}
					);	
				}
			};
			
			
			//Category List Select Call in Service
			self.selectCategories = function() {
				categoryService.selectCategories().then(
						function(response) {
							//$scope.documents.push(response);							
							for(var i=0; i < response.length ; i++) {
								$scope.categories.push({ id: response[i].id, text: response[i].name, parent: response[i].parentId, type: 'folder', state:{opened: false} ,done:false});
							}
						});
			};
			
			//should apply
			$scope.ac = function(){
			    return true;
			}

			//Node ready Event
			$scope.initNode = function() {
			    self.selectCategories(self.category);
			}

			//Node close Event
			$scope.closeNode = function() {
			    alert('close Node');
			};

			//Node select Event
			$scope.selectNode = function() {
			    var ref = $scope.treeInstance.jstree(true);
			    var sel = ref.get_selected();
			    if(!sel.length) {
			        return false;
			    }

			    self.category.id = sel[0];
			    $scope.selectDocuments(self.category);
			};

			//Node create Event
			$scope.createNode = function() {
			    var ref = $scope.treeInstance.jstree(true);
			    var sel = ref.get_selected();
			    if(!sel.length) {
			        return false;
			    }
			    sel = sel[0];
			    var created_sel = ref.create_node(sel, {"parent":"#"});
			    if(created_sel) {
			        ref.edit(created_sel, 'New Node',function(node,status) {
			            self.category.id = node.id;
			            self.category.name = node.text;
			            self.category.parentId = node.parent;
			            self.createCategory(self.category);
			        });
			    }
			}

			//Node rename Event	         
			$scope.renameNode = function() {
			    var ref = $scope.treeInstance.jstree(true);
			    var sel = ref.get_selected();
			    if(!sel.length) {
			        return false;
			    }
			    ref.edit(sel, null ,function(node,status) {
			        self.category.id = node.id;
			        self.category.name = node.text;
			        self.category.parentId = node.parent;
			        self.createCategory(self.category);
			    });
			}

			//Node remove Event        
			$scope.removeNode = function() {
			    var ref = $scope.treeInstance.jstree(true);
			    var sel = ref.get_selected();
			    if(!sel.length) {
			        return false;
			    }
			    self.category.id = sel[0];			    
			    self.deleteCategory(self.category.id).then(
						function(response) {
						    if(response == "200") {
						    	ref.delete_node(sel);
						    }else{
						    	alert('카테고리 삭제 실패');
						    }
						}
				);			    			    			 
			};
			
			
		}					
]);



