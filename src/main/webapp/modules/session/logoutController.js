function logoutController($rootScope, $scope, $log, $location) {
	$rootScope.viewName = 'SignOut';

	$rootScope.isLoggedIn = false;
	$rootScope.homeView = '/index';
	$location.path($rootScope.homeView);

	$log.debug('logoutContoller...');
}
appControllers.controller('logoutController', logoutController);