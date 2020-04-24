package de.ollie.musicman.persistence.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.ollie.musicman.persistence.converter.PlayListEntryDBOConverter;
import de.ollie.musicman.persistence.dbo.PlayListEntryDBO;
import de.ollie.musicman.persistence.repository.PlayListEntryRepository;
import de.ollie.musicman.service.persistence.exception.PersistenceException;
import de.ollie.musicman.service.persistence.port.PlayListEntryPersistencePort;
import de.ollie.musicman.service.so.PlayListEntrySO;

/**
 * An implementation of the playlistentry persistence port interface for RDBMS.
 *
 * @author rest-acf
 *
 *         GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Service
public class PlayListEntryRDBMSPersistenceAdapter implements PlayListEntryPersistencePort {

	private final PlayListEntryDBOConverter playListEntryDBOConverter;
	private final PlayListEntryRepository playListEntryRepository;

	public PlayListEntryRDBMSPersistenceAdapter(PlayListEntryDBOConverter playListEntryDBOConverter,
			PlayListEntryRepository playListEntryRepository) {
		super();
		this.playListEntryDBOConverter = playListEntryDBOConverter;
		this.playListEntryRepository = playListEntryRepository;
	}

	@Override
	public boolean delete(Long id) throws PersistenceException {
		boolean result = false;
		try {
			Optional<PlayListEntryDBO> dbo = this.playListEntryRepository.findById(id);
			if (dbo.isPresent()) {
				this.playListEntryRepository.delete(dbo.get());
				result = true;
			}
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.WriteError,
					"error while deleting playListEntry with id: " + id, e);
		}
		return result;
	}

	@Override
	public List<PlayListEntrySO> findAll() throws PersistenceException {
		try {
			List<PlayListEntrySO> sos = new ArrayList<>();
			for (PlayListEntryDBO dbo : this.playListEntryRepository.findAll()) {
				sos.add(this.playListEntryDBOConverter.convertDBOToSO(dbo));
			}
			return sos;
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.ReadError,
					"error while finding all playListEntrys.", e);
		}
	}

	@Override
	public Optional<PlayListEntrySO> findById(long id) throws PersistenceException {
		try {
			Optional<PlayListEntryDBO> dbo = this.playListEntryRepository.findById(id);
			if (dbo.isEmpty()) {
				return Optional.empty();
			}
			return Optional.of(this.playListEntryDBOConverter.convertDBOToSO(dbo.get()));
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.ReadError, "error while finding by id: " + id, e);
		}
	}

	@Override
	public void save(PlayListEntrySO so) throws PersistenceException {
		try {
			PlayListEntryDBO dbo = this.playListEntryDBOConverter.convertSOToDBO(so);
			this.playListEntryRepository.save(dbo);
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.WriteError, "error while saving: " + so, e);
		}
	}

	@Override
	public List<PlayListEntrySO> findPlayListEntriesForPlayList(Long playListId) throws PersistenceException {
		try {
			List<PlayListEntrySO> sos = new ArrayList<>();
			for (PlayListEntryDBO dbo : this.playListEntryRepository.findPlayListEntriesForPlayList(playListId)) {
				sos.add(this.playListEntryDBOConverter.convertDBOToSO(dbo));
			}
			return sos;
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.ReadError,
					"error while finding all playListEntries for playList:" + playListId, e);
		}
	}

}