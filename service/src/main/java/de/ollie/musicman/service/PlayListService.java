package de.ollie.musicman.service;

import java.util.Optional;

import de.ollie.musicman.service.persistence.exception.PersistenceException;
import de.ollie.musicman.service.so.PlayListSO;
import de.ollie.musicman.service.so.ResultPageSO;

/**
 * An interface for a playlist service.
 *
 * @author rest-acf
 */
public interface PlayListService {

	boolean delete(Long id) throws PersistenceException;

	ResultPageSO<PlayListSO> findAll() throws PersistenceException;

	Optional<PlayListSO> findById(long id) throws PersistenceException;

	PlayListSO save(PlayListSO playList) throws PersistenceException;

}