(function(){
	
	var userService = function($http){
		
		var loggedUserUrl = "http://" + location.host + "/thesis/api/user/me";
		var allUsersUrl = "http://" + location.host + "/thesis/api/user/all";
		var allRolesUrl = "http://" + location.host + "/thesis/api/user/roles";
		var saveUserUrl = "http://" + location.host + "/thesis/api/user/save";
		var deleteUserUrl = "http://" + location.host + "/thesis/api/user/delete";
		var changePasswordUrl = "http://" + location.host + "/thesis/api/user/changePassword";
		var checkPasswordUrl = "http://" + location.host + "/thesis/api/user/checkPassword/";
		var checkIfUserHasReservationUrl = "http://" + location.host + "/thesis/api/user/checkIfUserHasReservation/";
		
		
		var updateFields = function(user){
			if(user.role.name == 'ADMIN'){
				user.specialization = null;
				user.albumNumber = null
			} else if(user.role.name == 'PROMOTOR'){
				user.specialization = null;
				user.albumNumber = null;
			}
		}
		
		var getLoggedUser = function(){
			return $http.get(loggedUserUrl).then(function(response){
				return response.data;
			});
		};
		
		var getAllUsers = function(){
			return $http.get(allUsersUrl).then(function(response){
				return response.data;
			});
		};
		
		var getAllRoles = function(){
			return $http.get(allRolesUrl).then(function(response){
				return response.data;
			});
		};
		
		var saveUser = function(user){
			return $http.post(saveUserUrl, user).then(function(response){
				return response.data;
			});
		};
		
		var deleteUser = function(user){
			return $http.post(deleteUserUrl, user).then(function(response){
				return response.data;
			});
		};
		
		var changePassword = function(user){
			return $http.post(changePasswordUrl, user).then(function(response){
				return response.data;
			});
		};
		
		var checkIfUserHasReservation = function(albumNumber){
			var url = checkIfUserHasReservationUrl + albumNumber;
			return $http.get(url).then(function(response){
				return response.data;
			});
		};
		
		var checkPassword = function(password){
			var url = checkPasswordUrl + password;
			return $http.get(url).then(function(response){
				return response.data;
			});
		};
		
		return {
			getLoggedUser: getLoggedUser,
			getAllUsers: getAllUsers,
			getAllRoles: getAllRoles,
			saveUser: saveUser,
			updateFields: updateFields,
			deleteUser: deleteUser,
			checkIfUserHasReservation: checkIfUserHasReservation,
			checkPassword: checkPassword,
			changePassword: changePassword
		};
	};
	
	var app = angular.module("App");
	app.factory("userService", userService);
	
}());