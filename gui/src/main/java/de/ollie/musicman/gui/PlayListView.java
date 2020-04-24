package de.ollie.musicman.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import de.ollie.musicman.gui.events.Event;
import de.ollie.musicman.gui.events.EventProvider;
import de.ollie.musicman.gui.events.EventType;
import de.ollie.musicman.service.MediaService;
import de.ollie.musicman.service.PlayListService;
import de.ollie.musicman.service.so.PlayListSO;

/**
 * A view for play list maintenance.
 *
 * @author ollie (05.04.2020)
 */
public class PlayListView extends VerticalLayout {

	private Button buttonSave = new Button("Save");
	private EventProvider eventProvider;
	private PlayListService playListService;
	private PlayListSO playList;
	private SelectMediaLayout selectMediaLayout;
	private TextField textFieldName = new TextField("Name");

	public PlayListView(EventProvider eventProvider, MediaService mediaService, PlayListService playListService) {
		super();
		this.eventProvider = eventProvider;
		this.playListService = playListService;
		getStyle().set("border", "1px solid LightGray");

		Button buttonCreateExampleLists = new Button("Create Example Lists");
		buttonCreateExampleLists.addClickListener(event -> {
//			PlayListSO pl = new PlayListSO().setName("Tom Jones - Best of");
//			pl.addEntries(new PlayListEntrySO().setName("Sexbomb").setPath("Sexbomb.mp3").setPlayList(pl));
//			pl.addEntries(
//					new PlayListEntrySO().setName("It's Not Unusual").setPath("ItsNotUnusual.mp3").setPlayList(pl));
//			try {
//				pl = playListService.save(pl);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			pl = new PlayListSO().setName("Star Trek Sounds");
//			pl.addEntries(new PlayListEntrySO().setName("Door Bell").setPath("DoorBell.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Cloaking Device On").setPath("CloakingDeviceOn.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Cloaking Device Off").setPath("CloakingDeviceOff.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Klingon Beamer").setPath("KlingonBeamer.mp3").setPlayList(pl));
//			try {
//				pl = playListService.save(pl);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			//
//			pl = new PlayListSO().setName("Glenn Miller - In the Glenn Miller Mood");
//			String path = "/home/ollie/Musik/Glenn Miller/In the Glenn Miller Mood/";
//			pl.addEntries(new PlayListEntrySO().setName("In the Mood").setPath(path + "01 - In the Mood.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Moonlight Serenade")
//					.setPath(path + "02 - Moonlight Serenade.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("String of Pearls").setPath(path + "03 - String of Pearls.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Pennsilvania 6-5000")
//					.setPath(path + "04 - Pennsylvania 6-5000.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("American Patrol").setPath(path + "05 - American Patrol.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Tuxedo Junction").setPath(path + "06 - Tuxedo Junction.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Little Brown Jug").setPath(path + "08 - Little Brown Jug.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("I've Got a Gal in Kalamazoo")
//					.setPath(path + "11 - Ive Got a Gal in Kalamazoo.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("At Last").setPath(path + "14 - At Last.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Sun Valley Jump").setPath(path + "24 - Sun Valley Jump.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Jeep Jockey Jump").setPath(path + "29 - Jeep Jockey Jump.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Sliphorn Jive").setPath(path + "30 - Sliphorn Jive.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Frenesi").setPath(path + "31 - Frenesi.mp3").setPlayList(pl));
//			try {
//				pl = playListService.save(pl);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			//
//			pl = new PlayListSO().setName("Avengers - Endgame");
//			path = "/home/ollie/Musik/Avengers- Endgame (Original Motion Picture Soundtr/";
//			pl.addEntries(new PlayListEntrySO().setName("Totally Fine").setPath(path + "01 - Totally Fine.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Arrival").setPath(path + "02 - Arrival.mp3").setPlayList(pl));
//			pl.addEntries(
//					new PlayListEntrySO().setName("No Trust").setPath(path + "03 - No Trust.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Where Are They").setPath(path + "04 - Where Are They_.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Becoming Whole Again")
//					.setPath(path + "05 - Becoming Whole Again.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("I Figured It Out").setPath(path + "06 - I Figured It Out.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Perfectly Not Confusing")
//					.setPath(path + "07 - Perfectly Not Confusing.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("You Shouldn't Be Here")
//					.setPath(path + "08 - You Shouldn't Be Here.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("The How Works").setPath(path + "09 - The How Works.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Snap Out of It").setPath(path + "10 - Snap Out of It.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("So Many Stairs").setPath(path + "11 - So Many Stairs.mp3")
//					.setPlayList(pl));
//			pl.addEntries(
//					new PlayListEntrySO().setName("One Shot").setPath(path + "12 - One Shot.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Watch Each Other's Six")
//					.setPath(path + "13 - Watch Each Other's Six.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("I Can't Risk This")
//					.setPath(path + "14 - I Can't Risk This.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("He Gave It Away").setPath(path + "15 - He Gave It Away.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("The Tool of a Thief")
//					.setPath(path + "16 - The Tool of a Thief.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("The Measure of a Hero")
//					.setPath(path + "17 - The Measure of a Hero.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Destiny Fulfilled")
//					.setPath(path + "18 - Destiny Fulfilled.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("In Plain Sight.").setPath(path + "19 - In Plain Sight.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("How Do I Look").setPath(path + "20 - How Do I Look_.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Whatever It Takes")
//					.setPath(path + "21 - Whatever It Takes.mp3").setPlayList(pl));
//			pl.addEntries(
//					new PlayListEntrySO().setName("Not Good").setPath(path + "22 - Not Good.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Gotta Get Out").setPath(path + "23 - Gotta Get Out.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("I Was Made for This")
//					.setPath(path + "24 - I Was Made for This.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Tres Amigos").setPath(path + "25 - Tres Amigos.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Tunnel Scape").setPath(path + "26 - Tunnel Scape.mp3")
//					.setPlayList(pl));
//			pl.addEntries(
//					new PlayListEntrySO().setName("Worth It").setPath(path + "27 - Worth It.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Portals").setPath(path + "28 - Portals.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Get This Thing Started")
//					.setPath(path + "29 - Get This Thing Started.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("The One").setPath(path + "30 - The One.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("You Did Good").setPath(path + "31 - You Did Good.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("The Real Hero").setPath(path + "32 - The Real Hero.mp3")
//					.setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Five Seconds").setPath(path + "33 - Five Seconds.mp3")
//					.setPlayList(pl));
//			pl.addEntries(
//					new PlayListEntrySO().setName("Go Ahead").setPath(path + "34 - Go Ahead.mp3").setPlayList(pl));
//			pl.addEntries(new PlayListEntrySO().setName("Main on End").setPath(path + "35 - Main on End.mp3")
//					.setPlayList(pl));
//			try {
//				pl = playListService.save(pl);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			//
//			eventProvider.fireEvent(new Event(EventType.PLAY_LIST_UPDATED, pl.getId()));
		});

		playList = new PlayListSO();
		buttonSave.addClickListener(event -> savePlayList());
		buttonSave.setWidthFull();
		selectMediaLayout = new SelectMediaLayout(mediaService, playList);
		textFieldName.setWidthFull();
		setWidthFull();
		add( //
				buttonCreateExampleLists, //
				textFieldName, //
				selectMediaLayout, //
				buttonSave //
		);
	}

	private void savePlayList() {
		try {
			playList.setName(textFieldName.getValue());
			playList = playListService.save(playList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("#" + eventProvider);
		System.out.println("#" + playList);
		System.out.println("#" + playList.getId());
		eventProvider.fireEvent(new Event(EventType.PLAY_LIST_UPDATED, playList.getId()));
		textFieldName.setValue("");
		playList = new PlayListSO();
		selectMediaLayout.setPlayList(playList);
	}

}