package io.ahaitech.harmoney.service.impl;

import io.ahaitech.harmoney.model.AutoNumber;
import io.ahaitech.harmoney.repository.AutoNumberRepository;
import io.ahaitech.harmoney.service.AutoNumberService;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DefaultAutoNumberService implements AutoNumberService {

	@Resource
	AutoNumberRepository autoNumberRepository;

	@Resource
	SimpMessagingTemplate simpleMessagingTemplate;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public synchronized long getNextNumber(String key) {
		AutoNumber an = null;

		an = this.autoNumberRepository.findOne(key);
		if (an == null) {
			an = new AutoNumber();
			an.setId(key);
			// an.prePersist();
		} else {
			// an.preUpdate();
		}
		an.setValue(an.getValue() + 1);
		an = this.autoNumberRepository.save(an);

		this.simpleMessagingTemplate.convertAndSend("/topic/autoNumber", an);

		return an.getValue();
	}

}
