package de.ollie.musicman.persistence.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.ollie.musicman.persistence.dbo.PlayListEntryDBO;
import de.ollie.musicman.service.so.PlayListEntrySO;

/**
 * A converter for playlistentry DBO's.
 *
 * @author rest-acf
 *
 *         GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Component
public class PlayListEntryDBOConverter {

	@Autowired
	private PlayListDBOConverter playListDBOConverter;

	public PlayListEntrySO convertDBOToSO(PlayListEntryDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new PlayListEntrySO().setId(dbo.getId()).setName(dbo.getName()).setPath(dbo.getPath());
	}

	public PlayListEntryDBO convertSOToDBO(PlayListEntrySO so) {
		if (so == null) {
			return null;
		}
		return new PlayListEntryDBO().setId(so.getId()).setName(so.getName()).setPath(so.getPath());
	}

}