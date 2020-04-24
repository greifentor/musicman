package de.ollie.musicman.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.ollie.musicman.persistence.dbo.PlayListDBO;

/**
 * A CRUD repository for playlist access.
 *
 * @author rest-acf
 *
 *         GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Repository
public interface PlayListRepository extends CrudRepository<PlayListDBO, Long> {
}