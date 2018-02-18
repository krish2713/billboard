package com.altmedia.billboard.service;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminGetUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.ListUsersRequest;
import com.amazonaws.services.cognitoidp.model.ListUsersResult;
import com.amazonaws.services.cognitoidp.model.UserType;

public class CredentialService {
	private static final String EMAIL = "email";
	private static final String ADMIN_USER_POOL = "us-east-2_OtqLwFZBG";
	private static final String VENDOR_USER_POOL = "us-east-2_SWy1QxBTP";
	private static final String USER_POOL = "us-east-2_H9Fkk0ebH";
	private AWSCognitoIdentityProvider identityClient;
	private String adminUserPoolId;
	private String vendorUserPoolId;
	private String userPoolId;

	private static final CredentialService instance = new CredentialService();

	public static CredentialService getInstance() {
		return instance;
	}

	private CredentialService() {
		identityClient = AWSCognitoIdentityProviderClientBuilder.defaultClient();
		adminUserPoolId = System.getenv("ADMIN_USER_POOL") != null ? System.getenv("ADMIN_USER_POOL") : ADMIN_USER_POOL;
		vendorUserPoolId = System.getenv("VENDOR_USER_POOL") != null ? System.getenv("VENDOR_USER_POOL")
				: VENDOR_USER_POOL;
		userPoolId = System.getenv("USER_POOL") != null ? System.getenv("USER_POOL") : USER_POOL;
	}

	public List<String> getAdminEmailIds() {
		List<String> adminEmailIds = new ArrayList<String>();
		ListUsersRequest listUserRequest = new ListUsersRequest().withUserPoolId(adminUserPoolId);
		ListUsersResult result = identityClient.listUsers(listUserRequest);
		if (result != null) {
			List<UserType> users = result.getUsers();
			for (UserType user : users) {
				List<AttributeType> attrs = user.getAttributes();
				String email = getEmail(attrs);
				if (email != null) {
					adminEmailIds.add(email);
				}
			}
		}
		return adminEmailIds;
	}

	public String getVendorEmailId(String username) {
		AdminGetUserRequest getUserRequest = new AdminGetUserRequest().withUsername(username)
				.withUserPoolId(vendorUserPoolId);
		AdminGetUserResult user = identityClient.adminGetUser(getUserRequest);
		if (user != null) {
			List<AttributeType> attrs = user.getUserAttributes();
			return getEmail(attrs);

		}
		return null;

	}

	private String getEmail(List<AttributeType> attrs) {
		for (AttributeType type : attrs) {
			if (type.getName().equals(EMAIL)) {
				return type.getValue();
			}

		}
		return null;
	}

	public String getUserEmailId(String username) {
		AdminGetUserRequest getUserRequest = new AdminGetUserRequest().withUsername(username)
				.withUserPoolId(userPoolId);
		AdminGetUserResult user = identityClient.adminGetUser(getUserRequest);
		if (user != null) {
			List<AttributeType> attrs = user.getUserAttributes();
			return getEmail(attrs);

		}
		return null;
	}

}
