appServices.factory('mappingsService', function($http) {
	var service = {};

	service.getMappings = function() {
		return $http.get('../_admin/mappings');
	}

	return service;
});

function rootController($scope, $log, mappingsService) {

	mappingsService.getMappings().success(function(data) {
		$scope.mappings = data;
	});

	$log.info('root...');
}
appControllers.controller('rootController', rootController);

var dependents = [];
// dependents.push('ui.bootstrap');
dependents.push('green.inputmask4angular');
dependents.push('app.filters');
dependents.push('app.directives');
dependents.push('app.services');
dependents.push('app.controllers');
var app = angular.module('app', dependents);

function appInit($log, $rootScope) {
	$log.info('Initialization started...');

	_.mixin(_.str.exports());

	$log.info('Initialization finished...');
}
app.run([ '$log', '$rootScope', appInit ]);
