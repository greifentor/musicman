package de.ollie.musicman.persistence.dbo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A ORM mapping and database access class for playlistentrys.
 *
 * @author rest-acf
 */
@Accessors(chain = true)
@Data
@Entity(name = "PlayListEntry")
@Table(name = "PLAY_LIST_ENTRY")
public class PlayListEntryDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "PLAY_LIST")
	private Long playList;
	@Column(name = "NAME")
	private String name;
	@Column(name = "PATH")
	private String path;

}