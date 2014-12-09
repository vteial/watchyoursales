appServices.factory('stompClientService', function($log) {
	var service = {
		isConnected : false
	};

	var socket = new SockJS('/stomp');
	// var socket = new WebSocket('/stomp');
	var stompClient = Stomp.over(socket);

	service.connect = function(callback) {
		if (service.isConnected) {
			callback();
			return;
		}

		stompClient.connect({}, function(frame) {
			$log.info('Connected: ' + frame);
			service.frame = frame;
			service.isConnected = true;
			callback();
		});
	};

	service.send = function(endPoint, message) {
		stompClient.send(endPoint, {}, JSON.stringify({
			'message' : message
		}));
	};

	service.subscribe = function(topicPath, callback) {
		stompClient.subscribe(topicPath, callback);
	};

	return service;
});