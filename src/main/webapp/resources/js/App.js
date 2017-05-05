(function(){
  
  var app = angular.module("App",['ngRoute','ui.bootstrap','ngStorage','toaster','ngAnimate']);
  
  app.config(function($routeProvider){
	  $routeProvider.when('/home', {
			templateUrl : 'resources/partials/home.html'
		}).when('/userList',{
			templateUrl : 'resources/partials/userList.html',
			controller: 'userController'
		}).when('/thesisList',{
			templateUrl : 'resources/partials/thesisList.html',
			controller: 'thesisController'
		}).when('/myThesis',{
			templateUrl : 'resources/partials/myThesis.html',
			controller: 'myThesisController'
		}).when('/specializationList',{
			templateUrl : 'resources/partials/specializationList.html',
			controller: 'specializationController'
		});
  });
  
  
    
}());