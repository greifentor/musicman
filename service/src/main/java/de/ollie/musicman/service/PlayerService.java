package de.ollie.musicman.service;

/**
 * An interface for the player service.
 *
 * @author ollie (02.04.2020)
 */
public interface PlayerService {

	void play(String url);

	void pause();

	void stop();

}