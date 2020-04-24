package de.ollie.musicman.persistence.dbo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A ORM mapping and database access class for playlists.
 *
 * @author rest-acf
 */
@Accessors(chain = true)
@Data
@Entity(name = "PlayList")
@Table(name = "PLAY_LIST")
public class PlayListDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "PLAY_LIST")
	private List<PlayListEntryDBO> entries = new ArrayList<>();

	public PlayListDBO addEntries(PlayListEntryDBO... entries) {
		for (PlayListEntryDBO entry : entries) {
			this.entries.add(entry);
		}
		return this;
	}

	public PlayListDBO removeEntries(PlayListEntryDBO... entries) {
		for (PlayListEntryDBO entry : entries) {
			this.entries.remove(entry);
		}
		return this;
	}
}