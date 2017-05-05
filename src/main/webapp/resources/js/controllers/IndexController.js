(function() {


  var app = angular.module("App");

  var IndexController = function($scope, $http, userService, $sessionStorage, toastService) {
    
    
	//$scope.currentUser = $sessionStorage.currentUser;
	  userService.getLoggedUser().then(function(response){
			 // $sessionStorage.currentUser = response;
		  $sessionStorage.currentUser = response;
		  $scope.loggedUser = response;
		  console.log($sessionStorage.currentUser);
		 // });
		  //$window.location.href = "/Thesis/";
	  });
	  
	  $scope.passwords = {};
	  $scope.validOldPassword = null;
	  $scope.arePasswordsEquals = null;
	  
	  var onCheckOldPasswordSuccess = function(response){
		  if(response.success)
		  $scope.validOldPassword = true;
		  else $scope.validOldPassword = false;
	  }
	  
	  $scope.dynamicPopover = {
			    templateUrl: 'myPopoverTemplate.html',
			    title: 'Zmiana hasła'
			  };
	  
	  $scope.resetPasswords = function(){
		  $scope.validOldPassword = null;
		  $scope.arePasswordsEquals = null;
		  $scope.passwords = {oldPassword:null,
				  			  newPassword:null,
				  			  newPasswordRepeat:null};
	  }
	
	  $scope.checkOldPassword = function(){
		  userService.checkPassword($scope.passwords.oldPassword).then(onCheckOldPasswordSuccess, function(){});
	  }
	  
	  $scope.checkPasswordsEquality = function(){
		  $scope.arePasswordsEquals = $scope.passwords.newPassword === $scope.passwords.newPasswordRepeat;
	  }
	  
	  var onChangePasswordSuccess = function(response){
		  toastService.showToast(response);
		  $scope.resetPasswords();
	  }
	  
	  $scope.changePassword = function(){
		  var user = {id: $sessionStorage.currentUser.id,
				  	  password: $scope.passwords.newPassword};
		  userService.changePassword(user).then(onChangePasswordSuccess);
	  }
    
    
   // $http.get("http://localhost:8080/Thesis/api/me");
  };

  app.controller("IndexController",  IndexController);

}());