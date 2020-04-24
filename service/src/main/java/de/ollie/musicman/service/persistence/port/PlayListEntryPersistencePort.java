package de.ollie.musicman.service.persistence.port;

import java.util.List;
import java.util.Optional;

import de.ollie.musicman.service.persistence.exception.PersistenceException;
import de.ollie.musicman.service.so.PlayListEntrySO;

/**
 * An interface for playlistentry persistence ports.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
public interface PlayListEntryPersistencePort {

	boolean delete(Long id) throws PersistenceException;

	List<PlayListEntrySO> findAll() throws PersistenceException;

	Optional<PlayListEntrySO> findById(long id) throws PersistenceException;

	void save(PlayListEntrySO so) throws PersistenceException;

	List<PlayListEntrySO> findPlayListEntriesForPlayList(Long playListId) throws PersistenceException;

}