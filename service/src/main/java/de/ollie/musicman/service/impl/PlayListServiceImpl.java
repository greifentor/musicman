package de.ollie.musicman.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.ollie.musicman.service.PlayListService;
import de.ollie.musicman.service.persistence.exception.PersistenceException;
import de.ollie.musicman.service.persistence.port.PlayListPersistencePort;
import de.ollie.musicman.service.so.PlayListSO;
import de.ollie.musicman.service.so.ResultPageSO;

/**
 * An implementation of the playlist service interface.
 *
 * @author rest-acf
 */
@Service
public class PlayListServiceImpl implements PlayListService {

	private final PlayListPersistencePort playListPersistencePort;

	public PlayListServiceImpl(PlayListPersistencePort playListPersistencePort) {
		super();
		this.playListPersistencePort = playListPersistencePort;
	}

	@Override
	public boolean delete(Long id) throws PersistenceException {
		return this.playListPersistencePort.delete(id);
	}

	@Override
	public ResultPageSO<PlayListSO> findAll() throws PersistenceException {
		List<PlayListSO> l = this.playListPersistencePort.findAll();
		return new ResultPageSO<PlayListSO>().setCurrentPage(0).setResultsPerPage(l.size()).setResults(l)
				.setTotalResults(l.size());
	}

	@Override
	public Optional<PlayListSO> findById(long id) throws PersistenceException {
		return this.playListPersistencePort.findById(id);
	}

	@Override
	public PlayListSO save(PlayListSO playList) throws PersistenceException {
		return this.playListPersistencePort.save(playList);
	}

}