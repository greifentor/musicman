package de.ollie.musicman.persistence.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.ollie.musicman.persistence.converter.PlayListDBOConverter;
import de.ollie.musicman.persistence.converter.PlayListEntryDBOConverter;
import de.ollie.musicman.persistence.dbo.PlayListDBO;
import de.ollie.musicman.persistence.dbo.PlayListEntryDBO;
import de.ollie.musicman.persistence.repository.PlayListEntryRepository;
import de.ollie.musicman.persistence.repository.PlayListRepository;
import de.ollie.musicman.service.persistence.exception.PersistenceException;
import de.ollie.musicman.service.persistence.port.PlayListPersistencePort;
import de.ollie.musicman.service.so.PlayListEntrySO;
import de.ollie.musicman.service.so.PlayListSO;

/**
 * An implementation of the playlist persistence port interface for RDBMS.
 *
 * @author rest-acf
 *
 *         GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Service
public class PlayListRDBMSPersistenceAdapter implements PlayListPersistencePort {

	private final PlayListDBOConverter playListDBOConverter;
	private final PlayListEntryDBOConverter playListEntryDBOConverter;
	private final PlayListRepository playListRepository;
	private final PlayListEntryRepository playListEntryRepository;

	public PlayListRDBMSPersistenceAdapter(PlayListDBOConverter playListDBOConverter,
			PlayListEntryDBOConverter playListEntryDBOConverter, PlayListRepository playListRepository,
			PlayListEntryRepository playListEntryRepository) {
		super();
		this.playListDBOConverter = playListDBOConverter;
		this.playListEntryDBOConverter = playListEntryDBOConverter;
		this.playListRepository = playListRepository;
		this.playListEntryRepository = playListEntryRepository;
	}

	@Override
	public boolean delete(Long id) throws PersistenceException {
		boolean result = false;
		try {
			Optional<PlayListDBO> dbo = this.playListRepository.findById(id);
			if (dbo.isPresent()) {
				this.playListRepository.delete(dbo.get());
				result = true;
			}
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.WriteError,
					"error while deleting playList with id: " + id, e);
		}
		return result;
	}

	@Override
	public List<PlayListSO> findAll() throws PersistenceException {
		try {
			List<PlayListSO> sos = new ArrayList<>();
			for (PlayListDBO dbo : this.playListRepository.findAll()) {
				sos.add(this.playListDBOConverter.convertDBOToSO(dbo));
			}
			return sos;
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.ReadError, "error while finding all playLists.",
					e);
		}
	}

	@Override
	public Optional<PlayListSO> findById(long id) throws PersistenceException {
		try {
			Optional<PlayListDBO> dbo = this.playListRepository.findById(id);
			if (dbo.isEmpty()) {
				return Optional.empty();
			}
			System.out.println(dbo);
			return Optional.of(this.playListDBOConverter.convertDBOToSO(dbo.get()));
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.ReadError, "error while finding by id: " + id, e);
		}
	}

	@Override
	public PlayListSO save(PlayListSO so) throws PersistenceException {
		try {
			if (so.getId() == null) {
				List<PlayListEntrySO> ples = so.getEntries();
				so.setEntries(new ArrayList<>());
				PlayListDBO dbo = this.playListDBOConverter.convertSOToDBO(so);
				so.setId(this.playListDBOConverter.convertDBOToSO(this.playListRepository.save(dbo)).getId());
				so.setEntries(ples);
			}
			if (so.getId() != null) {
				so.getEntries().forEach(playListEntry -> {
					PlayListEntryDBO dbo = this.playListEntryRepository
							.save(this.playListEntryDBOConverter.convertSOToDBO(playListEntry).setPlayList(so.getId()));
					playListEntry.setId(dbo.getId());
				});
			}
			PlayListDBO dbo = this.playListDBOConverter.convertSOToDBO(so);
			return this.playListDBOConverter.convertDBOToSO(this.playListRepository.save(dbo));
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.WriteError, "error while saving: " + so, e);
		}
	}

}