package de.ollie.musicman.persistence.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import de.ollie.musicman.persistence.dbo.PlayListDBO;
import de.ollie.musicman.persistence.dbo.PlayListEntryDBO;
import de.ollie.musicman.service.so.PlayListEntrySO;
import de.ollie.musicman.service.so.PlayListSO;

/**
 * A converter for playlist DBO's.
 *
 * @author rest-acf
 */
@Component
public class PlayListDBOConverter {

	private final PlayListEntryDBOConverter playListEntryDBOConverter;

	public PlayListDBOConverter(PlayListEntryDBOConverter playListEntryDBOConverter) {
		super();
		this.playListEntryDBOConverter = playListEntryDBOConverter;
	}

	public PlayListSO convertDBOToSO(PlayListDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new PlayListSO() //
				.setId(dbo.getId()) //
				.setName(dbo.getName()) //
				.addEntries(getEntrySOs(dbo.getEntries()));
	}

	public PlayListDBO convertSOToDBO(PlayListSO so) {
		if (so == null) {
			return null;
		}
		return new PlayListDBO() //
				.setId(so.getId()) //
				.setName(so.getName()) //
				.addEntries(getEntryDBOs(so));
	}

	private PlayListEntryDBO[] getEntryDBOs(PlayListSO playList) {
		return playList.getEntries() //
				.stream() //
				.map(entry -> playListEntryDBOConverter.convertSOToDBO(entry).setPlayList(playList.getId())) //
				.collect(Collectors.toList()) //
				.toArray(new PlayListEntryDBO[playList.getEntries().size()]) //
		;
	}

	private PlayListEntrySO[] getEntrySOs(List<PlayListEntryDBO> entries) {
		return entries //
				.stream() //
				.map(entry -> playListEntryDBOConverter.convertDBOToSO(entry)) //
				.collect(Collectors.toList()) //
				.toArray(new PlayListEntrySO[entries.size()]) //
		;
	}

}