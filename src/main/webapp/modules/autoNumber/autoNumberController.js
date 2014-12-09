function autoNumberController($rootScope, $scope, $log, bootstrapNotifyService,
		autoNumberService) {
	var wydNotifyService = bootstrapNotifyService;

	$rootScope.viewName = 'AutoNumber';

	$scope.ans = autoNumberService.items;

	$scope.anId = 'tranCounterId';

	$scope.nextNumber = function() {
		autoNumberService.nextNumber($scope.anId);
		wydNotifyService.addSuccess('Successfully sent...');
	};

	$log.debug('autoNumberController...');
}
appControllers.controller('autoNumberController', autoNumberController);
