appServices.factory('announcementService', function($log, $http,
		stompClientService) {
	var service = {
		message : '',
		items : []
	};

	service.announce = function(message) {
		stompClientService.send("/app/announcement", message);
	};

	function update(payLoad) {
		var a = JSON.parse(payLoad.body);
		service.items.push(a);
		service.message += '\n' + a.message;
	}

	stompClientService.connect(function() {
		stompClientService.subscribe('/topic/announcement', update);
	});

	return service;
});
