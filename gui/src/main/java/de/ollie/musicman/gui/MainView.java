package de.ollie.musicman.gui;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

import de.ollie.musicman.gui.events.Event;
import de.ollie.musicman.gui.events.EventListener;
import de.ollie.musicman.gui.events.EventProvider;
import de.ollie.musicman.gui.events.EventType;
import de.ollie.musicman.service.MediaService;
import de.ollie.musicman.service.PlayListService;
import de.ollie.musicman.service.PlayerService;
import de.ollie.musicman.service.UserService;

/**
 * The main view of the Music Manager application.
 *
 * @author ollie (01.04.2020)
 */
@Route()
@PreserveOnRefresh
@VaadinSessionScope
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout implements EventListener, RouterLayout {

	private final EventProvider eventProvider;
	private final MediaService mediaService;
	private final PlayerService playerService;
	private final PlayListService playListService;

	public MainView(EventProvider eventProvider, MediaService mediaService, PlayerService playerService,
			PlayListService playListService, UserService userService) {
		super();
		this.eventProvider = eventProvider;
		this.eventProvider.addListener(this);
		this.mediaService = mediaService;
		this.playerService = playerService;
		this.playListService = playListService;
		LoginView loginView = new LoginView(eventProvider, userService);
		add( //
				loginView //
		);
	}

	@Override
	public void eventDetected(Event event) {
		if (event.getType() == EventType.USER_ACCEPTED) {
			removeAll();
			Accordion accordion = new Accordion();
			accordion.add("Play", new PlayerView(eventProvider, playerService, playListService));
			accordion.add("Playlists", new PlayListView(eventProvider, mediaService, playListService));
			accordion.setWidthFull();
			add( //
					accordion //
			);
		}
	}

}
