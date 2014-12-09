package io.vteial.watchyoursales.service.impl;

import io.vteial.watchyoursales.model.Branch;
import io.vteial.watchyoursales.model.Role;
import io.vteial.watchyoursales.model.User;
import io.vteial.watchyoursales.service.InvalidCredentialException;

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
