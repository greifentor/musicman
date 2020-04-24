package de.ollie.musicman.service;

import de.ollie.musicman.service.so.PlayListSO;
import de.ollie.musicman.service.so.PlayerStatsSO;

/**
 * An interface for the player service.
 *
 * @author ollie (02.04.2020)
 */
public interface PlayerService {

	void addPlayerServiceListener(PlayerServiceListener l);

	PlayerStatsSO getStats();

	void nextSong();

	void pause();

	void play(PlayListSO playList);

	void previousSong();

	void reset();

	void stop();

}