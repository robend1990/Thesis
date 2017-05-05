(function() {
  

  var app = angular.module("App");

  var myThesisController = function($scope, $sessionStorage, userService, thesisService, toastService) {
	
	  $scope.myThesis = {};
	  
	  
	  $scope.me = $sessionStorage.currentUser;
	  
	  var onGetMyThesisSuccess = function(response){
		  $scope.myThesis = response;
	  }
	  
	  var onGetMyThesisError = function(){
		  toastService.showCustomError("Błąd pobierania tematu");
	  }
	  
	  thesisService.getMyThesis($scope.me.id).then(onGetMyThesisSuccess, onGetMyThesisError);
	 
  };

  app.controller("myThesisController",  myThesisController);

}());