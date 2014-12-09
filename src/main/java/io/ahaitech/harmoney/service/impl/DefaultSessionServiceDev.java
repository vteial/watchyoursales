package io.ahaitech.harmoney.service.impl;

import io.ahaitech.harmoney.model.Branch;
import io.ahaitech.harmoney.model.Role;
import io.ahaitech.harmoney.model.User;
import io.ahaitech.harmoney.service.InvalidCredentialException;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

@Slf4j
public class DefaultSessionServiceDev extends DefaultSessionServicePrd {

	@Transactional(readOnly = true)
	@Override
	public User login(User user, HttpSession session)
			throws InvalidCredentialException {

		String userId = Strings.nullToEmpty(user.getId());
		// if (userId.startsWith("bvu-")) {
		// throw new InvalidCredentialException();
		// }
		User sessionUser = this.userRepository.findOne(userId);
		if (sessionUser == null) {
			throw new InvalidCredentialException();
		}
		Role role = this.roleRepository.findOne(sessionUser.getRoleId());
		sessionUser.setRole(role);

		Branch branch = this.branchRepository
				.findOne(sessionUser.getBranchId());
		sessionUser.setBranch(branch);
		session.setAttribute(SESSION_USER_KEY, sessionUser);

		return sessionUser;
	}
}
