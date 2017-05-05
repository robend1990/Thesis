(function() {


  var app = angular.module("App");

  var specializationModalController = function($scope, specializationService, toastService, localSpecialization, $modalInstance) {
	 
	$scope.specialization = {degree:'Inż',
							 process:'Dzienne'};
	  
	if(localSpecialization){
		$scope.specialization = _.clone(localSpecialization);
	}  
    
	$scope.ok = function () {
		specializationService.saveSpecialization($scope.specialization)
		.then(function(response){
			if(response.success)
			$modalInstance.close(response);
		});
		/*userService.saveUser($scope.user).then(function(response){
			toastService.showToast(response);
			$modalInstance.close($scope.user);
		});*/
	    
	  };

	  $scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	  };
   
	  
    
  };

  app.controller("specializationModalController",  specializationModalController);

}());