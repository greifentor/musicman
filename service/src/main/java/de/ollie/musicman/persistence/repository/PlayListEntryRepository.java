package de.ollie.musicman.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.ollie.musicman.persistence.dbo.PlayListEntryDBO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

/**
 * A CRUD repository for playlistentry access.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Repository
public interface PlayListEntryRepository extends CrudRepository<PlayListEntryDBO, Long> {

	@Query("SELECT p FROM PlayListEntry p WHERE p.playList.id=?1")
	List<PlayListEntryDBO> findPlayListEntriesForPlayList(Long playListId);

}