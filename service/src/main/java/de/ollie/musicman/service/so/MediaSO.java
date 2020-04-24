package de.ollie.musicman.service.so;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A container for file information.
 *
 * @author ollie (07.04.2020)
 */
@Accessors(chain = true)
@Data
@Generated
public class MediaSO {

	private String name;
	private String path;
	private MediaTypeSO type;

	private MediaSO() {
		super();
	}

	public static MediaSO of(MediaTypeSO type, String name, String path) {
		return new MediaSO() //
				.setName(name) //
				.setPath(path) //
				.setType(type) //
		;
	}

}