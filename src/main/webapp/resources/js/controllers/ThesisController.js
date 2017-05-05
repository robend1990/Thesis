(function() {


  var app = angular.module("App");

  var thesisController = function($scope, $http, $modal, thesisService, toastService, $route, $sessionStorage, dialogsService) {
    
   $scope.loggedUser = $sessionStorage.currentUser;  
	  
   $scope.thesises = [];
   
   $scope.allThesis = []; // to restore this table after switching my thesis filter 
   
   $scope.searchFilter;
   
   $scope.myThesisFilterValue = false;
   
   $scope.isLoggedUserAnOwner = function(thesis){
	   return $scope.loggedUser.id === thesis.advisor.id;
   };
   
   $scope.myThesisFilter = function(){
	   console.log($scope.myThesisFilterValue);
	   if($scope.myThesisFilterValue){
		   $scope.allThesis = $scope.thesises;
		   $scope.thesises =  _.filter($scope.thesises, function(value){
			   return value.advisor.lastname === $scope.loggedUser.lastname;
		   });
	   } else{
		   $scope.thesises = $scope.allThesis;
	   }
	   
   };
   
   var onGetAllThesisesSuccess = function(data){
	   $scope.thesises = data;
   };
   
   var onGetAllThesisesError = function(reason){
	   console.log("Błąd pobierania tematów prac");
   };
   
    thesisService.getAllThesises().then(onGetAllThesisesSuccess, onGetAllThesisesError);
    
    
    $scope.edit = function(thesis) {

	    var modalInstance = $modal.open({
	      animation: true,
	      templateUrl: 'resources/partials/editThesisModalForm.html',
	      controller: 'thesisModalController',
	      //size: 'sm',
	      resolve: {
	        localThesis: function () {
	          return thesis;
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
	  
	  $scope.makeReservation = function(thesis) {

		    var modalInstance = $modal.open({
		      animation: true,
		      templateUrl: 'resources/partials/thesisReservationModalForm.html',
		      controller: 'thesisReservationModalController',
		      resolve: {
			        localThesis: function () {
			          return thesis;
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
   
	  $scope.openDeleteModal = function(thesis){
		  
		  dialogsService.confirm('Usuwanie','Czy jesteś pewien ?',['OK','Anuluj'])
		  .then(function(){
			  thesisService.deleteThesis(thesis).then(function(response){
				  toastService.showToast(response);
				  $route.reload();
			  })
		  })  
	  };
	  
	  $scope.cancelReservation = function(thesis){
		  
		  dialogsService.confirm('Anulowanie rezerwacji','Czy jesteś pewien ?',['OK','Anuluj'])
		  .then(function(){
			  thesisService.cancelReservation(thesis).then(function(response){
				  toastService.showToast(response);
				  $route.reload();
			  })
		  })  
	  };
	  
	  $scope.openDefenseTermModal = function(thesis) {

		    var modalInstance = $modal.open({
		      animation: true,
		      templateUrl: 'resources/partials/defenseTermModal.html',
		      controller: 'defenseTermModalController',
		      //size: 'sm',
		      resolve: {
		        localThesis: function () {
		          return thesis;
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

  app.controller("thesisController",  thesisController);
  
  
  var thesisReservationModalController = function($scope, toastService, thesisService, localThesis, userService, $modalInstance){
	  $scope.thesis = localThesis;
	  $scope.albumNumber;
	  $scope.thesisDTO = {};
	  $scope.thesisDTO.thesis = $scope.thesis;
	  
	  $scope.userCheckingError;
	  $scope.userInfo;
	  
	  var onUserCheckSuccess = function(response){
		  if(response.success){
			  $scope.userCheckingError = null;
			  $scope.thesisDTO.albumNumber =  $scope.albumNumber;
			  $scope.userInfo = response.message;				  
		  } else{
			  $scope.userInfo = null;
			  $scope.userCheckingError = response.message;
		  }
	  };
	  
	  var onMakeReservationSuccess = function(response){
		  if(response.success){
			  $modalInstance.close(response);
		  } else{
			  toastService.showToast(response);
		  }
	  };
	  
	  $scope.doReservation = function(){
		  thesisService.makeReservation($scope.thesisDTO).then(onMakeReservationSuccess, function(){});
	  };
	  
	  $scope.checkForUser = function(){
		  userService.checkIfUserHasReservation($scope.albumNumber).then(onUserCheckSuccess, function(){});
	  };
	  
	  $scope.cancel = function () {
		    $modalInstance.dismiss('cancel');
		  };
  };
  
  app.controller("thesisReservationModalController",  thesisReservationModalController);
  
  var defenseTermModalController = function($scope, toastService, thesisService, localThesis, userService, $modalInstance){
	  
	 /* $scope.thesis = {};
	  if(localThesis){*/
		  $scope.thesis = _.clone(localThesis);
		  //$scope.date = $scope.thesis.defenseDate;
		  if($scope.thesis.defenseDate){
			  var dateString = moment($scope.thesis.defenseDate, 'DD-MM-YYYY HH:mm').format('DD-MM-YYYY');
			  var date = moment(dateString ,'DD-MM-YYYY').toDate();
			  $scope.date = date;
			  var timeString = moment($scope.thesis.defenseDate, 'DD-MM-YYYY HH:mm').format('HH:mm');
			  var time = moment(timeString, 'HH:mm').toDate();
			  $scope.time = time;
			  
		  }else{
			  var stringDate = moment().format('DD-MM-YYYY');
			  $scope.date = moment(stringDate, 'DD-MM-YYYY').toDate();
			  $scope.time = moment('10:00', 'HH:mm').toDate();
		  }
		  
	  $scope.opened = false;
	  
	  var onSetDefenseDateSuccess = function(response){
		  $modalInstance.close(response);
	  }
	  
	  var onSetDefenseDateError = function(response){
		  toastService.showCustomError("Błąd podczas ustalania terminu obrony");
		  $modalInstance.dismiss();
	  }
	  
	  $scope.save = function(){
		  var dateString = moment($scope.date).format('DD-MM-YYYY');
		  var timeString = moment($scope.time).format('HH:mm');
		  var timestampString = dateString + ' ' + timeString;
		  var miliseconds = moment(timestampString, 'DD-MM-YYYY HH:mm').valueOf();
		  var thesisDTO = {miliseconds: miliseconds, thesisId: $scope.thesis.id};
		  thesisService.setDefenseDate(thesisDTO).then(onSetDefenseDateSuccess, onSetDefenseDateError);
	  };
	  
	  $scope.cancel = function(){
		  $modalInstance.dismiss();
	  };
	  
	  $scope.openDatePicker = function($event) {
		    $event.preventDefault();
		    $event.stopPropagation();

		    $scope.opened = true;
		  };
	  
  };
  
  app.controller("defenseTermModalController",  defenseTermModalController);

}());