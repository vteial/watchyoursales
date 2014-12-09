package io.ahaitech.harmoney.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.ahaitech.harmoney.dto.AnnouncementDto;
import io.ahaitech.harmoney.service.AccountService;
import io.ahaitech.harmoney.service.AddressService;
import io.ahaitech.harmoney.service.BackupAndRestoreService;
import io.ahaitech.harmoney.service.BranchService;
import io.ahaitech.harmoney.service.CountryService;
import io.ahaitech.harmoney.service.CustomerService;
import io.ahaitech.harmoney.service.RoleService;
import io.ahaitech.harmoney.service.UserService;
import io.ahaitech.harmoney.v2.model.CustomerHelper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
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
	JdbcTemplate jdbcTemplateV2;

	@PostConstruct
	void init() {
		log.info("Data initialization started...");
		String query = "select count(*) from customer";
		long count = jdbcTemplateV2.queryForObject(query, Long.class);
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
		AnnouncementDto a = AnnouncementDto
				.createWithMessage("Data reset started...");
		log.info(a.getMessage());
		this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

		try {

			this.countryService.reset();
			a = AnnouncementDto
					.createWithMessage("Data Country reset finished...");
			log.info(a.getMessage());
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

			this.addressService.reset();
			a = AnnouncementDto
					.createWithMessage("Data Address reset finished...");
			log.info(a.getMessage());
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

			this.accountService.reset();
			a = AnnouncementDto
					.createWithMessage("Data Account reset finished...");
			log.info(a.getMessage());
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

			this.branchService.reset();
			a = AnnouncementDto
					.createWithMessage("Data Branch reset finished...");
			log.info(a.getMessage());
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

			this.roleService.reset();
			a = AnnouncementDto
					.createWithMessage("Data Role reset finished...");
			log.info(a.getMessage());
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

			this.userService.reset();
			a = AnnouncementDto
					.createWithMessage("Data User reset finished...");
			log.info(a.getMessage());
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

			this.customerService.reset();
			a = AnnouncementDto
					.createWithMessage("Data Customer reset finished...");
			log.info(a.getMessage());
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

		} catch (Throwable t) {
			String s = Throwables.getStackTraceAsString(t);
			a = AnnouncementDto.createWithMessage(s);
			log.info("Reset failed...", t);
			this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);
		}

		a = AnnouncementDto.createWithMessage("Data reset finished...");
		log.info(a.getMessage());
		this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

		this.inProgress = false;
		// System.out.println(this.inProgress);
	}

	@Resource
	CustomerHelper customerHelper;

	@Async
	@Override
	public void importCustomerFromV2() {
		this.inProgress = true;
		// System.out.println(this.inProgress);

		AnnouncementDto a = AnnouncementDto
				.createWithMessage("Data import customer started...");
		log.info(a.getMessage());
		this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

		RowCallbackHandler rowCallbackHandler = this.customerHelper;
		
		String queryC = "select * from customer";
		this.jdbcTemplateV2.query(queryC, null, null, rowCallbackHandler);

		a = AnnouncementDto
				.createWithMessage("Data import customer finished...");
		log.info(a.getMessage());
		this.simpleMessagingTemplate.convertAndSend(topicAnnouncement, a);

		this.inProgress = false;
		// System.out.println(this.inProgress);
	}
}
