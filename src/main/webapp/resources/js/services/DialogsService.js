(function(){
	
	var dialogsService = function($http, $modal){
		
	
		
		var confirm = function(title, message, buttons){
			 var modalInstance = $modal.open({
			      animation: true,
			      templateUrl: 'resources/partials/confirmDelete.html',
			      controller: 'confirmModalController',
			      size: 'sm',
			      resolve: {
			        data: function () {
			          return {
			        	  title: title,
			        	  message: message,
			        	  buttons: buttons
			          }
			        }
			      }
			    });
			 return modalInstance.result;
		}
		
		
		return {
			confirm: confirm,
		};
	};
	
	var app = angular.module("App");
	app.factory("dialogsService", dialogsService);
	
}());