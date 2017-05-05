(function() {

  var app = angular.module("App");

  var ConfirmModalController = function($modalInstance, data, $scope) {
	
	$scope.properties = data;  
	  
	$scope.ok = function(){
		$modalInstance.close();
	}  
	
	$scope.cancel = function(){
		$modalInstance.dismiss();
	}  
	  
	  
	  
	 
	  
	 

      
  };

  app.controller("confirmModalController",  ConfirmModalController);

}());