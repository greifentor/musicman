package de.ollie.musicman.service;

import de.ollie.musicman.service.so.PlayerStatsSO;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * A container for player service event data.
 *
 * @author ollie (05.04.2020)
 */
@Accessors(chain = true)
@EqualsAndHashCode
@Getter
@Generated
@Setter(value = AccessLevel.PRIVATE)
@ToString
public class PlayerServiceEvent {

	private PlayerStatsSO currentStats;

	private PlayerServiceEvent() {
		super();
	}

	public static PlayerServiceEvent of(PlayerStatsSO currentStats) {
		return new PlayerServiceEvent().setCurrentStats(currentStats);
	}

}