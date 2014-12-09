function rootController($scope, $log, $window, $rootScope) {

	$scope.viewSource = function() {
		var s = 'view-source:localhost:8181/' + $rootScope.currentViewSrcUrl;
		$log.info(s);
		$window.open(s);
	};

	$log.info('root...');
}
appControllers.controller('rootController', rootController);

var dependents = [ 'ngRoute', 'ngSanitize' ];
dependents.push('ngStorage');
// dependents.push('green.inputmask4angular');
// dependents.push('ngInputDate');
// dependents.push('ngNotify');
// dependents.push('ui.select');
dependents.push('ui.bootstrap');
dependents.push('app.filters');
dependents.push('app.directives');
dependents.push('app.services');
dependents.push('app.controllers');
var app = angular.module('app', dependents);

// app.config(function(uiSelectConfig) {
// uiSelectConfig.theme = 'select2';
// });

app.config(function($httpProvider) {
	$httpProvider.interceptors.push('generalHttpInterceptor');
});

app.config(function($routeProvider, $locationProvider) {
	$routeProvider.when('/', {
		templateUrl : 'modules/home/index-d.html',
		controller : 'indexController'
	});

	$routeProvider.when('/notFound', {
		templateUrl : 'modules/zgeneral/notFound-d.html'
	});

	$routeProvider.when('/index', {
		templateUrl : 'modules/home/index-d.html',
		controller : 'indexController'
	});

	$routeProvider.when('/signin', {
		templateUrl : 'modules/session/login-d.html',
		controller : 'loginController'
	});

	$routeProvider.when('/signout', {
		templateUrl : 'modules/session/logout-d.html',
		controller : 'logoutController'
	});

	$routeProvider.when('/home', {
		templateUrl : 'modules/home/index-d.html',
		controller : 'indexController'
	});

	$routeProvider.when('/customer', {
		templateUrl : 'modules/customer/d.html',
		controller : 'customerController'
	});

	$routeProvider.when('/customerSignUp', {
		templateUrl : 'modules/customer/signup.html',
		controller : 'customerController'
	});

	$routeProvider.when('/init', {
		templateUrl : 'modules/init/d.html',
		controller : 'initController'
	});

	$routeProvider.when('/info', {
		templateUrl : 'modules/info/d.html',
		controller : 'infoController'
	});

	$routeProvider.when('/autoNumber', {
		templateUrl : 'modules/autoNumber/d.html',
		controller : 'autoNumberController'
	});

	$routeProvider.when('/announcement', {
		templateUrl : 'modules/announcement/d.html',
		controller : 'announcementController'
	});

	$routeProvider.otherwise({
		redirectTo : '/notFound'
	});

	// $locationProvider.html5Mode(true);
});

function appInit($log, $rootScope, $location, $sessionStorage) {
	$log.info('Initialization started...');

	_.mixin(_.str.exports());

	$rootScope.$on("$routeChangeStart", function(event, next, current) {
		// $log.info('Location : ', $location.path());
		var curLocPath = $location.path();
		$log.info('Before Current Location : ', curLocPath);
		if (curLocPath == '/notFound' || curLocPath == '/signin'
				|| curLocPath == '/signout') {
			return;
		}
		$sessionStorage.currentLocationPath = curLocPath;
		// $log.info('Stored Location : ', $sessionStorage.currentLocationPath);

		var srcUrl = $location.absUrl().indexOf('index.html');
		srcUrl = $location.absUrl().substring(0, srcUrl);
		srcUrl = srcUrl + next.templateUrl
		$rootScope.currentViewSrcUrl = srcUrl;
		$log.info('srcUrl = ' + srcUrl);
	});

	$rootScope.$on("$routeChangeSuccess", function(event, next, current) {
		// $log.info('Location : ', $location.path());
		var curLocPath = $location.path();
		// $log.info('After Current Location : ', curLocPath);
	});

	$rootScope.isLoggedIn = false;
	$rootScope.homeView = '/index';

	// var path = $sessionStorage.currentLocationPath;
	// if (!path) {
	// path = '/index';
	// }
	// $location.path(path);

	$location.path('/signin');

	// $.material.init();

	$log.info('Initialization finished...');
}
app.run([ '$log', '$rootScope', '$location', '$sessionStorage', appInit ]);
