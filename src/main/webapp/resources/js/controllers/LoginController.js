(function() {
  var loginUrl = "http://" + location.host + "/thesis/api/login";

  var app = angular.module("App");

  var LoginController = function($scope, $http, $location, $window, $sessionStorage, userService) {
	// var loginUrl = "http://localhost:8080/thesis/api/login";
	
	  
	  $scope.credentials = {};
	  
	  var onLoginSucces = function(response){
		 /* $( "#loginBody" ).animate({
			    opacity: 0.05
			  }, 500, function() {*/
		  if(response.status == 200){
			 
			 // userService.getLoggedUser().then(function(response){
				 // $sessionStorage.currentUser = response;
				  $window.location.href = "/thesis/#home";
			 // });
			  //$window.location.href = "/Thesis/";
		  }
				  
			 // });
		 // 
	  }
	  
	  var onLoginError = function(response){
		  $scope.error = true;
		  $scope.loginError = "Nieprawidłowy użytkownik lub hasło";
	  }
	  
	  $scope.preparePostData = function () {
      

          return 'username=' + $scope.credentials.username + '&password=' + $scope.credentials.password;
      };

      $scope.login = function (username, password) {
          var postData = $scope.preparePostData();
          
          $http.post(loginUrl, postData, {headers:{'Content-Type': 'application/x-www-form-urlencoded'}})
          .then(onLoginSucces, onLoginError);
        	
      };
  };

  app.controller("LoginController",  LoginController);

}());