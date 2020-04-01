package de.ollie.musicman.gui.events;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A container for the event data.
 *
 * @author ollie (01.04.2020)
 */
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class Event {

	private EventType type;
	private long id;

}
