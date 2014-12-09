package io.vteial.watchyoursales.service;

import io.vteial.watchyoursales.model.User;

import javax.servlet.http.HttpSession;

public interface SessionService {

	static String SESSION_USER_KEY = "user";

	static String SESSION_BRANCH_KEY = "branch";

	void resetPassword(String userId) throws ModelNotFoundException;

	User login(User user, HttpSession session)
			throws InvalidCredentialException;

	void logout(User user, HttpSession session);

	void changeDetails(User user, User sessionUser)
			throws ModelNotFoundException;

	void changePassword(User user, User sessionUser)
			throws ModelNotFoundException, InvalidCredentialException;

	void onUserActivity(User user, String sessionId, String activity);
}
