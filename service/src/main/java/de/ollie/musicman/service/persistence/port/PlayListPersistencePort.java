package de.ollie.musicman.service.persistence.port;

import java.util.List;
import java.util.Optional;

import de.ollie.musicman.service.persistence.exception.PersistenceException;
import de.ollie.musicman.service.so.PlayListSO;

/**
 * An interface for playlist persistence ports.
 *
 * @author rest-acf
 *
 *         GENERATED CODE!!! DO NOT CHANGE!!!
 */
public interface PlayListPersistencePort {

	boolean delete(Long id) throws PersistenceException;

	List<PlayListSO> findAll() throws PersistenceException;

	Optional<PlayListSO> findById(long id) throws PersistenceException;

	PlayListSO save(PlayListSO so) throws PersistenceException;

}