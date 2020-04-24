package de.ollie.musicman.service;

/**
 * A interface which is to implement to listen to player service events.
 *
 * @author ollie (05.04.2020)
 */
public interface PlayerServiceListener {

	void playing(PlayerServiceEvent event);

}