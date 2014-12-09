appServices.factory('autoNumberService', function($log, $http,
		stompClientService) {

	var service = {
		items : [],
		itemsMap : {}
	};

	service.nextNumber = function(id) {
		var path = '../api/autoNumbers/nextNumber/' + id;
		$http.get(path).success(function(response) {
			$log.info(response);
		})
	};

	function update(payLoad) {
		var aan = JSON.parse(payLoad.body);
		var ean = service.itemsMap[aan.id];
		if (!ean) {
			service.items.push(aan)
			service.itemsMap[aan.id] = aan
		} else {
			_.assign(ean, aan);
		}
	}

	stompClientService.connect(function() {
		stompClientService.subscribe('/topic/autoNumber', update);
	});

	return service;
});
