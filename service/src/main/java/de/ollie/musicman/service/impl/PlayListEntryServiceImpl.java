package de.ollie.musicman.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.ollie.musicman.service.PlayListEntryService;
import de.ollie.musicman.service.persistence.exception.PersistenceException;
import de.ollie.musicman.service.persistence.port.PlayListEntryPersistencePort;
import de.ollie.musicman.service.so.PlayListEntrySO;
import de.ollie.musicman.service.so.ResultPageSO;

/**
 * An implementation of the playlistentry service interface.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Service
public class PlayListEntryServiceImpl implements PlayListEntryService {

	private final PlayListEntryPersistencePort playListEntryPersistencePort;

	public PlayListEntryServiceImpl(PlayListEntryPersistencePort playListEntryPersistencePort) {
		super();
		this.playListEntryPersistencePort = playListEntryPersistencePort;
	}

	@Override
	public boolean delete(Long id) throws PersistenceException {
		return this.playListEntryPersistencePort.delete(id);
	}

	@Override
	public ResultPageSO<PlayListEntrySO> findAll() throws PersistenceException {
		List<PlayListEntrySO> l = this.playListEntryPersistencePort.findAll();
		return new ResultPageSO<PlayListEntrySO>().setCurrentPage(0).setResultsPerPage(l.size()).setResults(l).setTotalResults(l.size());
	}

	@Override
	public Optional<PlayListEntrySO> findById(long id) throws PersistenceException {
		return this.playListEntryPersistencePort.findById(id);
	}

	@Override
	public void save(PlayListEntrySO playListEntry) throws PersistenceException {
		this.playListEntryPersistencePort.save(playListEntry);
	}

	@Override
	public ResultPageSO<PlayListEntrySO> findPlayListEntriesForPlayList(Long playListId) throws PersistenceException {
		List<PlayListEntrySO> l = this.playListEntryPersistencePort.findPlayListEntriesForPlayList(playListId);
		return new ResultPageSO<PlayListEntrySO>().setCurrentPage(0).setResultsPerPage(l.size()).setResults(l).setTotalResults(l.size());
	}

}