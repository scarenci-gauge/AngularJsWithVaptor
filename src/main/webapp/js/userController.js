function userController($scope, $window, $http) {

    var init = function () {
	$http.get('/AngularJsWithVaptor/users').success(function(data){
		$scope.users = data;
	});
    }();

    $scope.postUser = function () {
    	var user = angular.toJson({users : $scope.user});
    	$http.post('/AngularJsWithVaptor/users', user).success(function(data){
    		$scope.users.unshift(data);
		reset();
	});
    };

    $scope.putUser = function () {
    	var url = '/AngularJsWithVaptor/users/' + $scope.user.id,
       	    user = angular.toJson({users : $scope.user});
    	
    	$http.put(url, user).success(function(data){
		reset();
		$(".btn-primary").attr("disabled", "disabled");
		$(".btn-success").removeAttr("disabled");
	});
    };
    
    $scope.deleteUser = function(user){
	var confirm = $window.confirm('Remove user ' + user.login + '?');
	if(confirm){
		var url = '/AngularJsWithVaptor/users/' + user.id;

		$http.delete(url).success(function(data){
			var index = $scope.users.indexOf(user);
			$scope.users.splice(index, 1);
		});
	}
    };
	
    $scope.edit = function(user){
	$scope.user = user;
	$(".btn-success").attr("disabled", "disabled");
	$(".btn-primary").removeAttr("disabled");
    };
	
    var reset = function(){
	$scope.user = {id : 0, login : "", name : "", password: ""};
    };
}
