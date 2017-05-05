(function(){
	
	var toastService = function(toaster){
		
	
		
		var showToast = function(data){
			if(data.success){
				toaster.pop('success', "", data.message);
			}
			else{
				toaster.pop('error',"",data.message);
			}
		};
		
		var showCustomError = function(msg){
			toaster.pop('error', "", msg);
		};
		
		return {
			showToast: showToast,
			showCustomError: showCustomError
		};
	};
	
	var app = angular.module("App");
	app.factory("toastService", toastService);
	
}());