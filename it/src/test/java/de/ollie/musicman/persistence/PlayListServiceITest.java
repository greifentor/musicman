package de.ollie.musicman.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.ollie.musicman.service.PlayListService;
import de.ollie.musicman.service.so.PlayListEntrySO;
import de.ollie.musicman.service.so.PlayListSO;

/**
 * An integration test for the play list service.
 *
 * @author ollie (23.04.2020)
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PlayListServiceITest {

	private static final String PLAY_LIST_NAME = "PlayListName";
	private static final String PLAY_LIST_ENTRY_NAME = "PlayListEntryName";
	private static final String PLAY_LIST_ENTRY_PATH = "PlayListEntryPath";

	@Autowired
	private PlayListService unitUnderTest;

	@DisplayName("Save operations")
	@Nested
	class TestsForSaveOperation {

		@DisplayName("Save a new play list without any entries in the repository.")
		@Test
		void saveANewPlayListWithOutAnyEntries_AppearsInTheDatabase() throws Exception {
			PlayListSO playListCreated = new PlayListSO().setName(PLAY_LIST_NAME);
			PlayListSO playListRead = unitUnderTest.save(playListCreated);
			assertNotNull(playListRead.getId());
			assertEquals(PLAY_LIST_NAME, playListRead.getName());
			assertTrue("entry list should be empty but wasn't", playListRead.getEntries().isEmpty());
		}

		@DisplayName("Save a new play list with a new play list entry appended after first save in the repository.")
		@Test
		void saveANewPlayListWithANewEntryAfterFirstSave_AppearsInTheDatabase() throws Exception {
			PlayListEntrySO playListEntry = new PlayListEntrySO() //
					.setName(PLAY_LIST_ENTRY_NAME) //
					.setPath(PLAY_LIST_ENTRY_PATH);
			PlayListSO playListCreated = new PlayListSO().setName(PLAY_LIST_NAME);
			PlayListSO playListReturned = unitUnderTest.save(playListCreated);
			System.out.println(playListReturned);
			playListReturned.addEntries(playListEntry);
			System.out.println(playListReturned);
			playListReturned = unitUnderTest.save(playListReturned);
			assertNotNull(playListReturned.getId());
			assertEquals(PLAY_LIST_NAME, playListReturned.getName());
			assertEquals(1, playListReturned.getEntries().size());
		}

		@DisplayName("Save a new play list with a new play list entry in the repository.")
		@Test
		void saveANewPlayListWithANewEntry_AppearsInTheDatabase() throws Exception {
			PlayListEntrySO playListEntry = new PlayListEntrySO() //
					.setName(PLAY_LIST_ENTRY_NAME) //
					.setPath(PLAY_LIST_ENTRY_PATH);
			PlayListSO playListCreated = new PlayListSO().setName(PLAY_LIST_NAME);
			playListCreated.addEntries(playListEntry);
			System.out.println(playListCreated);
			PlayListSO playListReturned = unitUnderTest.save(playListCreated);
			assertNotNull(playListReturned.getId());
			assertEquals(PLAY_LIST_NAME, playListReturned.getName());
			assertEquals(1, playListReturned.getEntries().size());
		}

	}

	@DisplayName("Find operations")
	@Nested
	class TestsForFindOperation {

		@DisplayName("Finds a play list by Id.")
		@Test
		void findsAPlayListById() throws Exception {
			PlayListSO playListCreated = new PlayListSO().setName(PLAY_LIST_NAME);
			Long id = unitUnderTest.save(playListCreated).getId();
			PlayListSO playList = unitUnderTest.findById(id).get();
			assertNotNull(playList.getId());
			assertEquals(PLAY_LIST_NAME, playList.getName());
			assertTrue("entry list should be empty but wasn't", playList.getEntries().isEmpty());
		}

		@DisplayName("Find a play list by Id with entries.")
		@Test
		void findsAPlayListByIdWithEntries() throws Exception {
			PlayListEntrySO playListEntry = new PlayListEntrySO() //
					.setName(PLAY_LIST_ENTRY_NAME) //
					.setPath(PLAY_LIST_ENTRY_PATH);
			PlayListSO playListCreated = new PlayListSO().setName(PLAY_LIST_NAME);
			playListCreated.addEntries(playListEntry);
			Long id = unitUnderTest.save(playListCreated).getId();
			PlayListSO playList = unitUnderTest.findById(id).get();
			assertNotNull(playList.getId());
			assertEquals(PLAY_LIST_NAME, playList.getName());
			assertEquals(1, playList.getEntries().size());
		}

	}

}