(function(){
	var userController = function($scope, userService, $sessionStorage, $modal, $route, specializationService, dialogsService, toaster, toastService){
		
		$scope.users = [];
		var currentUser = $sessionStorage.currentUser;
		
		var onGetAllUsersSuccess = function(response){
			$scope.users = response;
		};
		
		var onGetAllUsersError = function(reason){
			console.log("bład pobierania użytkowników");
		};
		var data = {
			success:true,
			message: 'aasss'
		};
		$scope.pop = function(){
            //toaster.pop('success', "title", "text");
			toastService.showToast(data);
        };
		
		userService.getAllUsers().then(onGetAllUsersSuccess, onGetAllUsersError);
		
		$scope.open = function(user) {

		    var modalInstance = $modal.open({
		      animation: true,
		      templateUrl: 'resources/partials/userModalForm.html',
		      controller: 'userModalController',
		      //size: 'sm',
		      resolve: {
		        localUser: function () {
		          return user;
		        }
		      }
		    });

		    modalInstance.result.then(function (selectedItem) {
		      console.log(selectedItem);
		      // todo zamist reload zaminic lub dodac obiekt do listy
		      $route.reload();
		    }, function () {
		    	console.log('Modal dismissed at: ' + new Date());
		    });
		  };
		
		  $scope.openDeleteModal = function(user){
			  
			  dialogsService.confirm('Usunąć ?','Czy jesteś pewien ?',['OK','Anuluj'])
			  .then(function(){
				  userService.deleteUser(user).then(function(response){
					  toastService.showToast(response);
					  $route.reload();
				  })
			  })
			 /* var modalInstance = $modal.open({
			      animation: true,
			      templateUrl: 'resources/partials/confirmDelete.html',
			      controller: 'confirmModalController',
			      size: 'sm',
			      resolve: {
			        data: function () {
			          return {
			        	  title: 'Delete?',
			        	  message: 'Are you sure?',
			        	  buttons: ['OK', 'Anuluj']
			          }
			        }
			      }
			    });
			  
			  modalInstance.result.then(function(){
				  userService.deleteUser(user).then(function(){
					  $route.reload();
				  });
			  },function(){console.log('modal dismm')});*/
			  
		  }
		
	};
	
	var app = angular.module("App");
	app.controller("userController", userController);
	
	var userModalController = function($scope, $modalInstance, localUser, userService, specializationService, toastService){
		if(localUser){
			$scope.user = _.clone(localUser);
		}
		else{
			$scope.user = {};
		}
		$scope.roles = [];
		$scope.specializations = [];
		
		var onGetRolesSuccess = function(data){
			$scope.roles = data;
			if($scope.user.role){
				var ss = _.find($scope.roles, function(value){
					return value.name === $scope.user.role.name;
				});
				$scope.user.role = ss;
			} else{
				$scope.user.role = $scope.roles[0];
			}
			
		};
		
		var onGetRolesError = function(reason){
			console.log("błąd pobierania roli");
		};
		
		var onGetSpecializationsSuccess = function(data){
			$scope.specializations = data;
			if($scope.user.specialization){
				var spec = _.find($scope.specializations, function(value){
					return value.name === $scope.user.specialization.name 
					&& value.process === $scope.user.specialization.process
					&& value.degree === $scope.user.specialization.degree;
				});
				$scope.user.specialization = spec;
			}
		};
		
		var onGetSpecializationsError = function(reason){
			console.log("błąd pobierania kierunków");
		};
		
		userService.getAllRoles().then(onGetRolesSuccess, onGetRolesError);
		specializationService.getAllSpecializations().then(onGetSpecializationsSuccess, onGetSpecializationsError);
		
		$scope.ok = function () {
			userService.updateFields($scope.user);
			userService.saveUser($scope.user).then(function(response){
				toastService.showToast(response);
				$modalInstance.close($scope.user);
			});
		    
		  };

		  $scope.cancel = function () {
		    $modalInstance.dismiss('cancel');
		  };
	};
	app.controller("userModalController", userModalController);
	
}());