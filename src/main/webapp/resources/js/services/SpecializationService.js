(function(){
	
	var specializationService = function($http){
		
		
		var allSpecializationsUrl = "http://" + location.host + "/thesis/api/spec/all";
		var saveSpecializationUrl = "http://" + location.host + "/thesis/api/spec/save";
		var deleteSpecializationUrl = "http://" + location.host + "/thesis/api/spec/delete";
		
		
		var getAllSpecializations = function(){
			return $http.get(allSpecializationsUrl).then(function(response){
				return response.data;
			});
		};
		
		var saveSpecialization = function(spec){
			return $http.post(saveSpecializationUrl, spec).then(function(response){
				return response.data;
			});
		};
		
		var deleteSpecialization = function(spec){
			return $http.post(deleteSpecializationUrl, spec).then(function(response){
				return response.data;
			});
		};
		
		
		
		
		return {
			getAllSpecializations: getAllSpecializations,
			saveSpecialization: saveSpecialization,
			deleteSpecialization: deleteSpecialization
		};
	};
	
	var app = angular.module("App");
	app.factory("specializationService", specializationService);
	
}());