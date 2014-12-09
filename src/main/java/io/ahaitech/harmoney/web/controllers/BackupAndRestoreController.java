package io.ahaitech.harmoney.web.controllers;

import io.ahaitech.harmoney.dto.MessageDto;
import io.ahaitech.harmoney.service.BackupAndRestoreService;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/_admin/backupAndRestore")
@Slf4j
public class BackupAndRestoreController {

	@Resource
	BackupAndRestoreService backupAndRestoreService;

	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public @ResponseBody MessageDto reset() {
		String s = "Data reset successfully started...";
		System.out.println(this.backupAndRestoreService.isInProgress());
		if (this.backupAndRestoreService.isInProgress()) {
			s = "Data reset is already in progress...";
		} else {
			this.backupAndRestoreService.reset();
		}

		return MessageDto.createWithMessage(s);
	}

}
