package de.ollie.musicman.service.impl;

import java.io.File;

import javax.inject.Named;

import com.sun.javafx.application.PlatformImpl;

import de.ollie.musicman.service.PlayerService;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

/**
 * An implementation of the player service interface.
 *
 * @author ollie (02.04.2020)
 */
@Named
public class PlayerServiceImpl implements PlayerService {

	private MediaPlayer mediaPlayer;

	@Override
	public void play(String url) {
		PlatformImpl.startup(() -> {
		});
		Platform.runLater(() -> {
			try {
				Media media = new Media(new File("Sexbomb.mp3").toURI().toString());
				mediaPlayer = new MediaPlayer(media);
				mediaPlayer.play();
			} catch (Exception e) {
			}
		});
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
	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		} else {
			System.out.println("No player started!");
		}
	}

}