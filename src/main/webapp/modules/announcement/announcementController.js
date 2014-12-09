function announcementController($rootScope, $scope, $log,
		bootstrapNotifyService, announcementService) {
	var wydNotifyService = bootstrapNotifyService;

	$rootScope.viewName = 'Announcement';

	$scope.announcements = announcementService;

	$scope.message = 'Test Message...';

	$scope.announce = function() {
		announcementService.announce($scope.message);
		wydNotifyService.addInfo('Successfully sent...');
	};

	$log.debug('announcementController...');
}
appControllers.controller('announcementController', announcementController);
