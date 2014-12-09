package io.ahaitech.harmoney.service.impl;

import io.ahaitech.harmoney.model.Branch;
import io.ahaitech.harmoney.model.Role;
import io.ahaitech.harmoney.model.User;
import io.ahaitech.harmoney.repository.BranchRepository;
import io.ahaitech.harmoney.repository.RoleRepository;
import io.ahaitech.harmoney.repository.UserRepository;
import io.ahaitech.harmoney.service.InvalidCredentialException;
import io.ahaitech.harmoney.service.ModelNotFoundException;
import io.ahaitech.harmoney.service.SessionService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

@Slf4j
public class DefaultSessionServicePrd extends AbstractService implements
		SessionService {

	@Resource
	BranchRepository branchRepository;

	@Resource
	RoleRepository roleRepository;

	@Resource
	UserRepository userRepository;
	
	@Transactional(readOnly = true)
	@Override
	public User login(User user, HttpSession session)
			throws InvalidCredentialException {

		String userId = Strings.nullToEmpty(user.getId());
		// if (userId.startsWith("bvu-")) {
		// throw new InvalidCredentialException();
		// }
		String passwd = Strings.nullToEmpty(user.getPassword());
		User sessionUser = this.userRepository.findOne(userId);
		if (sessionUser == null || !sessionUser.getPassword().equals(passwd)) {
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

	@Override
	public void logout(User user, HttpSession session) {

		User sessionUser = (User) session.getAttribute(SESSION_USER_KEY);
		if (sessionUser != null) {
			session.removeAttribute(SESSION_USER_KEY);
		}

	}

	@Override
	public void changeDetails(User user, User sessionUser)
			throws ModelNotFoundException {

		User userDb = this.userRepository.findOne(sessionUser.getId());
		if (userDb == null) {
			throw new ModelNotFoundException();
		}
		userDb.setEmailId(user.getEmailId());
		userDb.setFirstName(user.getFirstName());
		userDb.setLastName(user.getLastName());
		user.setUpdateBy(sessionUser.getUpdateBy());
		this.userRepository.save(userDb);

		sessionUser.setFirstName(user.getFirstName());
		sessionUser.setLastName(user.getLastName());
		sessionUser.setEmailId(user.getEmailId());
	}

	@Override
	public void changePassword(User user, User sessionUser)
			throws ModelNotFoundException, InvalidCredentialException {

		User userDb = this.userRepository.findOne(sessionUser.getId());
		if (userDb == null) {
			throw new ModelNotFoundException();
		}

		if (!userDb.getPassword().equals(user.getPassword())) {
			throw new InvalidCredentialException();
		}
		userDb.setPassword(user.getNewPassword());

		userDb.setUpdateBy(sessionUser.getUpdateBy());
		this.userRepository.save(userDb);
	}

	@Override
	public void resetPassword(String userId) throws ModelNotFoundException {
	}

	@Override
	public void onUserActivity(User user, String sessionId, String activity) {
	}
}
