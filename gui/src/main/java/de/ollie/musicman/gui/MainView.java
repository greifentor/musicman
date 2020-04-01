package de.ollie.musicman.gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

import de.ollie.musicman.gui.events.Event;
import de.ollie.musicman.gui.events.EventListener;
import de.ollie.musicman.gui.events.EventProvider;
import de.ollie.musicman.gui.events.EventType;
import de.ollie.musicman.service.UserService;

/**
 * The main view of the Music Manager application.
 *
 * @author ollie (01.04.2020)
 */
@Route
@PreserveOnRefresh
@VaadinSessionScope
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout implements EventListener {

	private final EventProvider eventProvider;

	private AccordionPanel panelWarnings = new AccordionPanel();

	public MainView(EventProvider eventProvider, UserService userService) {
		super();
		this.eventProvider = eventProvider;
		this.eventProvider.addListener(this);
		LoginView loginView = new LoginView(eventProvider, userService);
		// addClassName("centered-content");
		add( //
				loginView //
		);
	}

	@Override
	public void eventDetected(Event event) {
		if (event.getType() == EventType.USER_ACCEPTED) {
			removeAll();
		}
	}

}
