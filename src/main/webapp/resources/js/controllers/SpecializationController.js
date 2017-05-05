(function() {


  var app = angular.module("App");

  var specializationController = function($scope, $route , dialogsService, specializationService, toastService, $modal) {
    
    $scope.specializations = [];
    
    var onGetSpecializationSucces = function(data){
    	$scope.specializations = data;
    }
    
    onGetSpecializationError = function(){
    	toastService.showCustomToast('Błąd pobierania kierunków');
    }
    
    specializationService.getAllSpecializations().then(onGetSpecializationSucces, onGetSpecializationError);
    
    $scope.openDeleteModal = function(spec){
		  
		  dialogsService.confirm('Usuwanie','Czy jesteś pewien ?',['OK','Anuluj'])
		  .then(function(){
			  specializationService.deleteSpecialization(spec).then(function(response){
				  toastService.showToast(response);
				  $route.reload();
			  })
		  })  
	  };
    
    $scope.edit = function(specialization) {

	    var modalInstance = $modal.open({
	      animation: true,
	      templateUrl: 'resources/partials/specializationModalForm.html',
	      controller: 'specializationModalController',
	      //size: 'sm',
	      resolve: {
	        localSpecialization: function () {
	          return specialization;
	        }
	      }
	    });

	    modalInstance.result.then(function (response) {
	      toastService.showToast(response);
	      // todo zamist reload zaminic lub dodac obiekt do listy
	      $route.reload();
	    }, function () {
	    	console.log('Modal dismissed at: ' + new Date());
	    });
	  };
    
  };

  app.controller("specializationController",  specializationController);

}());