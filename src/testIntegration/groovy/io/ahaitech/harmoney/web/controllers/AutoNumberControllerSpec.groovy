package io.ahaitech.harmoney.web.controllers;

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

public class AutoNumberControllerSpec extends Specification {

	@Shared
	RestTemplate restTemplate

	@Shared
	List<String> urls

	@Shared
	List<Long> vals

	def setupSpec() {
		restTemplate = new RestTemplate();
		String urlPrefix = 'http://localhost:8181/api/autoNumbers'
		urls = []
		urls << urlPrefix + '/testId' + '/nextNumber'
		urls << urlPrefix + '/customerId'  + '/nextNumber'
		vals = []
		urls.each { url ->
			def val = restTemplate.getForObject(url, Long.class)
			vals << val+1
		}
		println urls
		println vals
	}

	def cleanupSpec() {
	}

	@Unroll
	void "testId's nextNumber is "() {

		when:
		ResponseEntity<Long> entity = restTemplate.getForEntity(url, Long.class)

		then:
		entity.statusCode == HttpStatus.OK
		entity.body == val

		where:
		url << urls
		val << vals
	}
}
