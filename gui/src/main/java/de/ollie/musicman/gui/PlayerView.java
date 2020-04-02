package de.ollie.musicman.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import de.ollie.musicman.service.PlayerService;

/**
 * A view for the media player.
 *
 * @author ollie (02.04.2020)
 */
public class PlayerView extends VerticalLayout {

	public PlayerView(PlayerService playerService) {
		super();
		Button buttonPlay = new Button("Play");
		buttonPlay.addClickListener(event -> playerService.play(";op"));
		Button buttonPause = new Button("Pause");
		buttonPause.addClickListener(event -> playerService.pause());
		Button buttonStop = new Button("Stop");
		buttonStop.addClickListener(event -> playerService.stop());
		add( //
				buttonPlay, //
				buttonPause, //
				buttonStop //
		);
	}

}