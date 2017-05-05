(function() {


  var app = angular.module("App");

  var thesisModalController = function($scope, specializationService, thesisService, toastService, localThesis, $modalInstance) {
	 
	$scope.specializations = [];
	
	$scope.thesis = {};
	  
	if(localThesis){
		$scope.thesis = _.clone(localThesis);
	}
	
	var onGetSpecializationsSuccess = function(data){
		$scope.specializations = data;
		if($scope.thesis.specialization){
			var spec = _.find($scope.specializations, function(value){
				return value.id === $scope.thesis.specialization.id;
			});
			$scope.thesis.specialization = spec;
		} else {
			$scope.thesis.specialization = $scope.specializations[0];
		}
	};
	
	var onGetSpecializationsError = function(data){
		console.log("Bła pobierania specljalizacji");
	};
	
	specializationService.getAllSpecializations().then(onGetSpecializationsSuccess, onGetSpecializationsError);
	
	$scope.ok = function () {
		thesisService.saveThesis($scope.thesis)
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

  app.controller("thesisModalController",  thesisModalController);

}());