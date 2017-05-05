(function(){
	
	var thesisService = function($http){
		
		var allThesisesUrl = "http://" + location.host + "/thesis/api/thesis/all";
		var saveThesisUrl = "http://" + location.host + "/thesis/api/thesis/save";
		var deleteThesisUrl = "http://" + location.host + "/thesis/api/thesis/delete";
		var makeReservationUrl = "http://" + location.host + "/thesis/api/thesis/makeReservation";
		var cancelReservationUrl = "http://" + location.host + "/thesis/api/thesis/cancelReservation/";
		var setDefenseDateUrl = "http://" + location.host + "/thesis/api/thesis/setDefenseDate";
		var getMyThesisUrl = "http://" + location.host + "/thesis/api/thesis/myThesis/";
		
		var getAllThesises = function(){
			return $http.get(allThesisesUrl).then(function(response){
				return response.data;
			});
		};
		
		var saveThesis = function(thesis){
			return $http.post(saveThesisUrl, thesis).then(function(response){
				return response.data;
			});
		};
		
		var deleteThesis = function(thesis){
			return $http.post(deleteThesisUrl, thesis).then(function(response){
				return response.data;
			});
		};
		
		var makeReservation = function(thesisDTO){
			return $http.post(makeReservationUrl, thesisDTO).then(function(response){
				return response.data;
			});
		};
		
		var cancelReservation = function(thesis){
			var url = cancelReservationUrl + thesis.id;
			return $http.post(url).then(function(response){
				return response.data;
			});
		};
		
		var setDefenseDate = function(thesisDTO){
			return $http.post(setDefenseDateUrl, thesisDTO).then(function(response){
				return response.data;
			});
		};
		
		var getMyThesis = function(studentId){
			var url = getMyThesisUrl + studentId;
			return $http.get(url).then(function(response){
				return response.data;
			});
		};
		
		return {
			getAllThesises: getAllThesises,
			saveThesis: saveThesis,
			deleteThesis: deleteThesis,
			makeReservation: makeReservation,
			cancelReservation: cancelReservation,
			setDefenseDate: setDefenseDate,
			getMyThesis: getMyThesis
		};
	};
	
	var app = angular.module("App");
	app.factory("thesisService", thesisService);
	
}());