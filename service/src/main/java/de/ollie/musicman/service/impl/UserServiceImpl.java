package de.ollie.musicman.service.impl;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

import de.ollie.musicman.service.UserService;

/**
 * An implementation for the user service.
 *
 * @author ollie (01.04.2020)
 */
@Named
public class UserServiceImpl implements UserService {

	@Value("${musicman.password}")
	private String password;

	@Override
	public boolean isAccepted(String userName, String password) {
		return userName.equals("user") && password.equals(this.password);
	}

}