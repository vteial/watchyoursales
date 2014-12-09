function customerController($rootScope, $scope, $log) {
	$rootScope.viewName = 'Info';

	$log.debug('customerController...');
}
appControllers.controller('customerController', customerController);
