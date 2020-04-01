package de.ollie.musicman.service;

/**
 * An interface for a user service.
 *
 * @author ollie (01.04.2020)
 */
public interface UserService {

	boolean isAccepted(String userName, String password);

}
