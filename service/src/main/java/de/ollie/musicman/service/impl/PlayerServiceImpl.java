package de.ollie.musicman.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sun.javafx.application.PlatformImpl;

import de.ollie.musicman.service.PlayerService;
import de.ollie.musicman.service.PlayerServiceEvent;
import de.ollie.musicman.service.PlayerServiceListener;
import de.ollie.musicman.service.so.PlayListEntrySO;
import de.ollie.musicman.service.so.PlayListSO;
import de.ollie.musicman.service.so.PlayerStatsSO;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

/**
 * An implementation of the player service interface.
 *
 * @author ollie (02.04.2020)
 */
@Component
public class PlayerServiceImpl implements PlayerService {

	private MediaPlayer mediaPlayer;
	private List<PlayerServiceListener> listeners = new ArrayList<>();
	private List<PlayListEntrySO> entries;
	private int currentEntryIndex;

	@Override
	public void addPlayerServiceListener(PlayerServiceListener l) {
		if (l != null) {
			listeners.add(l);
		}
	}

	@Override
	public PlayerStatsSO getStats() {
		if ((mediaPlayer != null) && (currentEntryIndex >= 0) && (currentEntryIndex < entries.size())) {
			double balance = mediaPlayer.getBalance();
			Duration currentTime = mediaPlayer.getCurrentTime();
			Duration totalDuration = mediaPlayer.getTotalDuration();
			double percent = currentTime.toMillis() / totalDuration.toMillis() * 100;
			double volume = mediaPlayer.getVolume();
			PlayListEntrySO currentEntry = entries.get(currentEntryIndex);
			return PlayerStatsSO.of((currentEntry != null ? currentEntry.getName() : "-"), currentEntryIndex, balance,
					currentTime, totalDuration, percent, volume);
		} else {
			System.out.println("Unmatching parameters");
			System.out.println("mediaPlayer = " + mediaPlayer);
			if (entries != null) {
				System.out
						.println("currentEntryIndex = " + currentEntryIndex + " id not in 0.." + (entries.size() - 1));
			}
		}
		return null;
	}

	@Override
	public void nextSong() {
		if (currentEntryIndex < entries.size() - 1) {
			stop();
			currentEntryIndex++;
			playSong();
		}
	}

	@Override
	public void pause() {
		if (mediaPlayer != null) {
			if (mediaPlayer.getStatus() == Status.PAUSED) {
				mediaPlayer.play();
			} else {
				mediaPlayer.pause();
			}
		} else {
			System.out.println("No player started!");
		}
	}

	@Override
	public void play(PlayListSO playList) {
		entries = new ArrayList<>(playList.getEntries());
		playSong();
	}

	private void playSong() {
		if (currentEntryIndex < entries.size()) {
			PlatformImpl.startup(() -> {
			});
			Platform.runLater(() -> {
				try {
					PlayListEntrySO currentEntry = entries.get(currentEntryIndex);
					Media media = new Media(new File(currentEntry.getPath()).toURI().toString());
					mediaPlayer = new MediaPlayer(media);
					mediaPlayer.play();
					mediaPlayer.setOnEndOfMedia(() -> {
						if (currentEntryIndex < entries.size()) {
							currentEntryIndex++;
							this.playSong();
						}
					});
					mediaPlayer.setOnPlaying(() -> System.out.println("playing: " + currentEntry.getName()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}

	@Override
	public void previousSong() {
		if (currentEntryIndex > 0) {
			stop();
			currentEntryIndex--;
			playSong();
		}
	}

	@Override
	public void reset() {
		if (entries != null) {
			entries.clear();
		}
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer = null;
		}
		currentEntryIndex = 0;
	}

	@Override
	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		} else {
			System.out.println("No player started!");
		}
	}

	@Scheduled(fixedRate = 1000)
	private void printStats() {
		try {
			Thread.sleep(1000);
			firePlayerServiceEvent(PlayerServiceEvent.of(getStats()));
		} catch (InterruptedException ie) {
			System.out.println("firing event service interrupted.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void firePlayerServiceEvent(PlayerServiceEvent event) {
		listeners.forEach(listener -> {
			try {
				listener.playing(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}