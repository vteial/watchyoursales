appServices.factory('autoNumberService', function($log) {
	var service = {
		messages : [],
		autoNumbers : [],
		autoNumbersMap : {}
	};

	var socket = new SockJS('/stomp');
	// var socket = new WebSocket('/stomp');
	var stompClient = Stomp.over(socket);
	var headers = {
		login : 'mylogin',
		passcode : 'mypasscode',
		'clientId' : 'my-client-id'
	};

	stompClient.connect({}, function(frame) {
		$log.info('Connected: ' + frame);
		stompClient.subscribe('/topic/autoNumber', function(payLoad) {
			var aan = JSON.parse(payLoad.body);
			var ean = service.autoNumbersMap[aan.id];
			if (!ean) {
				service.autoNumbers.push(aan)
				service.autoNumbersMap[aan.id] = aan
			} else {
				_.assign(ean, aan);
			}
		});

		stompClient.subscribe('/topic/announcement', function(payLoad) {
			var msg = JSON.parse(payLoad.body);
			service.messages.push(msg)
			$log.info(msg);
		});
	});

	service.send = function(message) {
		stompClient.send("/app/announcement", message);
	}

	return service;
});

function rootController($scope, $log, $http, autoNumberService) {

	$scope.ans = autoNumberService.autoNumbers;

	$scope.anId = 'tranCounterId';

	$scope.sendingMsg = 'test message';
	$scope.arrivedMsgs = autoNumberService.messages;

	$scope.nextNumber = function() {
		var path = '../api/autoNumbers/nextNumber/' + $scope.anId;
		$http.get(path).success(function(response) {
			$log.info(response);
		}).error(function(error, status) {
			$log.error(error);
		});
	};

	$scope.announce = function() {
		autoNumberService.send($scope.sendingMsg);
	}

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
