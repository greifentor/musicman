package de.ollie.musicman.service;

import java.util.Optional;

import de.ollie.musicman.service.persistence.exception.PersistenceException;
import de.ollie.musicman.service.so.PlayListEntrySO;
import de.ollie.musicman.service.so.ResultPageSO;

/**
 * An interface for a playlistentry service.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
public interface PlayListEntryService {

	boolean delete(Long id) throws PersistenceException;

	ResultPageSO<PlayListEntrySO> findAll() throws PersistenceException;

	Optional<PlayListEntrySO> findById(long id) throws PersistenceException;

	void save(PlayListEntrySO playListEntry) throws PersistenceException;

	ResultPageSO<PlayListEntrySO> findPlayListEntriesForPlayList(Long playListId) throws PersistenceException;

}