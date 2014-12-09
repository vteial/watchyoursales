function initController($rootScope, $scope, $log, bootstrapNotifyService,
		$http, announcementService) {
	var wydNotifyService = bootstrapNotifyService;

	$rootScope.viewName = 'Init';

	$scope.announcements = announcementService;

	$scope.reset = function() {
		var path = '_admin/backupAndRestore/reset';
		$http.get(path).success(function(response) {
			$log.info(response);
			wydNotifyService.addInfo(response.message);
		})
	};

	$scope.importCutomerFromV2 = function() {
		var path = '_admin/backupAndRestore/importCustomerFromV2';
		$http.get(path).success(function(response) {
			$log.info(response);
			wydNotifyService.addInfo(response.message);
		})
	};

	$log.debug('initController...');
}
appControllers.controller('initController', initController);
