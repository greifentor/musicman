package de.ollie.musicman.service.so;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A service object class for playlists.
 *
 * @author rest-acf
 */
@Accessors(chain = true)
@Data
public class PlayListSO {

	private Long id;
	private String name;
	private List<PlayListEntrySO> entries = new ArrayList<>();

	public PlayListSO addEntries(PlayListEntrySO... entries) {
		for (PlayListEntrySO entry : entries) {
			this.entries.add(entry);
		}
		return this;
	}

	public PlayListSO removeEntries(PlayListEntrySO... entries) {
		for (PlayListEntrySO entry : entries) {
			this.entries.remove(entry);
		}
		return this;
	}

}