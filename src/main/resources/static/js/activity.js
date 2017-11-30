
function loadActivities ($scope, $http){
	return $http({
		 method : 'GET',
		 /*
		 url: (window.location.hostname === 'localhost' ?
				'http://localhost:8080/activity' :
				'https://activityexample.herokuapp.com/activity')
		 */
		 url: 'activity'
				
		}).then(function (response) {
			 $scope.activities = response.data;
	});
}

function loadActivity (activityId, $scope, $http){
	return $http({
		 method : 'GET',
		url: 'activity/' + $scope.activity.id
		}).then(function (response) {
			 $scope.activity = response.data;
	});
}


var viewActivityDialogOptions = {
	controller: 'ViewActivityCtrl',
	templateUrl: './templates/activityDetails.html'
};
function viewActivity_bare(activity, $scope, $http, $dialog) {
	var activityToView = activity;
	return $dialog.dialog(angular.extend(viewActivityDialogOptions, {resolve: {activity: angular.copy(activityToView)}})).open().then(function (){
		loadActivities($scope, $http);
	});
};

var editDialogOptions = {
	controller: 'EditActivityCtrl',
	templateUrl: './templates/activityEdit.html',
};
function editActivity_bare(activity, $scope, $http, $dialog) {
	var activityToEdit = activity;
	return $dialog.dialog(angular.extend(editDialogOptions, {resolve: {activity: angular.copy(activityToEdit)}})).open().then(function (){
		loadActivities($scope, $http);
	});
};

function deleteActivity_bare(activity, $scope, $http, $dialog) {
		var deleteRequest = {
			method : 'DELETE',
			url: 'activity/' + activity.id
		};
		
		return $http(deleteRequest).then(function() {
			loadActivities($scope, $http);
		});
		//todo handle error
	};


//function initiateApp() {
	var app = angular.module('ActivityMeterApp', ['ui.bootstrap']);

app.controller('ActivityCtrl', function ($scope, $http, $dialog) {
	loadActivities($scope, $http);
	
	$scope.viewActivity = function(activity){
		viewActivity_bare(activity, $scope, $http, $dialog);
	};
	
	var addDialogOptions = {
		controller: 'AddActivityCtrl',
		templateUrl: './templates/activityAdd.html'
	};
	$scope.add = function(activity){
		$dialog.dialog(angular.extend(addDialogOptions, {})).open().then(function (){
			loadActivities($scope, $http);
		}) ;
	};

	$scope.edit = function(activity){
		editActivity_bare(activity, $scope, $http, $dialog);
	};
		
	$scope.delete = function(activity) {
		deleteActivity_bare(activity, $scope, $http, $dialog);
	};
});

app.controller('ViewActivityCtrl', function ($scope, $http, activity, $dialog, dialog) {
	$scope.activity = activity;
	
	$scope.edit = function(activity){
		editActivity_bare(activity, $scope, $http, $dialog).then(function () {
			loadActivity (activity.id, $scope, $http);
		});
		
	};
	
	$scope.delete = function(activity) {
		deleteActivity_bare(activity, $scope, $http, $dialog).then(function () {
		dialog.close();
		});
	};

	$scope.close = function(){
		loadActivities($scope, $http);
		dialog.close();
	};
});
	
app.controller('AddActivityCtrl', function($scope, $http, dialog){
	$scope.save = function(Activity) {
		var postRequest = {
		method : 'POST',
		url: 'activity' ,
		data: {
				text: $scope.activity.text,
				tags: $scope.activity.tags,
				title: $scope.activity.title
			  }
		}  
		
		$http(postRequest).then(function (response) {
			$scope.activities = response.data;
		}).then(function () {
			$scope.close();
		});
	};

	$scope.close = function(){;
		dialog.close(undefined);
	};
});

app.controller('EditActivityCtrl', function ($scope, $http, activity, dialog) {
	$scope.activity = activity;
	$scope.save = function($activity) {
		var putRequest = {
		method : 'PUT',
		url: 'activity/' + $scope.activity.id,
		data: {
				text: $scope.activity.text,
				tags: $scope.activity.tags,
				title: $scope.activity.title
			  }
		}  
		
		$http(putRequest).then(function (response) {
			$scope.activities = response.data;
		}).then(function () {
			//todo handle error
			$scope.close();
		});
	};

	$scope.close = function(){
		loadActivities($scope, $http);
		dialog.close();
	};
});
	
//	return app;
//}