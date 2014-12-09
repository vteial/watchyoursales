package io.ahaitech.harmoney.service.impl;

import io.ahaitech.harmoney.dto.MessageDto;
import io.ahaitech.harmoney.service.AccountService;
import io.ahaitech.harmoney.service.AddressService;
import io.ahaitech.harmoney.service.BackupAndRestoreService;
import io.ahaitech.harmoney.service.BranchService;
import io.ahaitech.harmoney.service.CountryService;
import io.ahaitech.harmoney.service.CustomerService;
import io.ahaitech.harmoney.service.RoleService;
import io.ahaitech.harmoney.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.common.base.Throwables;

@Service
@Slf4j
public class DefaultBackupAndRestoreService implements BackupAndRestoreService {

	boolean inProgress = false;

	@Value("/topic/announcement")
	String topicAnnouncement;

	@Resource
	SimpMessagingTemplate simpleMessagingTemplate;

	@Resource
	CountryService countryService;

	@Resource
	AddressService addressService;

	@Resource
	BranchService branchService;

	@Resource
	AccountService accountService;

	@Resource
	RoleService roleService;

	@Resource
	UserService userService;

	@Resource
	CustomerService customerService;

	@Resource
	JdbcTemplate jdbcTemplateV3;

	@Resource
	JdbcTemplate jdbcTemplateV2;

	@PostConstruct
	void init() {
		log.info("Data initialization started...");
		String query = "select count(*) from customer";
		long count = jdbcTemplateV3.queryForObject(query, Long.class);
		log.info("v3 customer count : {}", count);
		count = jdbcTemplateV2.queryForObject(query, Long.class);
		log.info("v2 customer count : {}", count);
		log.info("Data initialization finished...");
	}

	@Override
	public boolean isInProgress() {
		return inProgress;
	}

	@Async
	@Override
	public void reset() {
		this.inProgress = true;
		// System.out.println(this.inProgress);
		MessageDto a = MessageDto
				.createWithMessage("Data reset started...");
		log.info(a.getMessage());
		this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

		try {

			this.countryService.reset();
			a = MessageDto
					.createWithMessage("Data Country reset finished...");
			log.info(a.getMessage());
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

			this.addressService.reset();
			a = MessageDto
					.createWithMessage("Data Address reset finished...");
			log.info(a.getMessage());
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

			this.accountService.reset();
			a = MessageDto
					.createWithMessage("Data Account reset finished...");
			log.info(a.getMessage());
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

			this.branchService.reset();
			a = MessageDto
					.createWithMessage("Data Branch reset finished...");
			log.info(a.getMessage());
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

			this.roleService.reset();
			a = MessageDto
					.createWithMessage("Data Role reset finished...");
			log.info(a.getMessage());
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

			this.userService.reset();
			a = MessageDto
					.createWithMessage("Data User reset finished...");
			log.info(a.getMessage());
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

			this.customerService.reset();
			a = MessageDto
					.createWithMessage("Data Customer reset finished...");
			log.info(a.getMessage());
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

		} catch (Throwable t) {
			String s = Throwables.getStackTraceAsString(t);
			a = MessageDto.createWithMessage(s);
			log.info("Reset failed...", t);
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);
		}

		a = MessageDto.createWithMessage("Data reset finished...");
		log.info(a.getMessage());
		this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

		this.inProgress = false;
		// System.out.println(this.inProgress);
	}

}
