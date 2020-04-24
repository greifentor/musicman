package de.ollie.musicman.service.so;

import javafx.util.Duration;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A container for current player stats.
 *
 * @author ollie (05.04.2020)
 */
@Accessors(chain = true)
@Data
@Generated
public class PlayerStatsSO {

	private String entryName;
	private double balance;
	private int currentSongIndex;
	private Duration currentTime;
	private Duration totalDuration;
	private double percent;
	private double volume;

	private PlayerStatsSO() {
		super();
	}

	public static PlayerStatsSO of(String entryName, int currentSongIndex, double balance, Duration currentTime,
			Duration totalDuration, double percent, double volume) {
		return new PlayerStatsSO() //
				.setBalance(balance) //
				.setCurrentSongIndex(currentSongIndex) //
				.setCurrentTime(currentTime) //
				.setEntryName(entryName) //
				.setPercent(percent) //
				.setTotalDuration(totalDuration) //
				.setVolume(volume) //
		;
	}

}