package de.ollie.musicman.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.RouterLayout;

import de.ollie.musicman.gui.events.Event;
import de.ollie.musicman.gui.events.EventListener;
import de.ollie.musicman.gui.events.EventProvider;
import de.ollie.musicman.gui.events.EventType;
import de.ollie.musicman.service.PlayListService;
import de.ollie.musicman.service.PlayerService;
import de.ollie.musicman.service.so.PlayListSO;
import de.ollie.musicman.service.so.PlayerStatsSO;

/**
 * A view for the media player.
 *
 * @author ollie (02.04.2020)
 */
public class PlayerView extends VerticalLayout implements EventListener, RouterLayout {

	private ComboBox<PlayListSO> comboBoxPlayList;
	private EventProvider eventProvider;
	private PlayerService playerService;
	private PlayListService playListService = null;
	private ProgressBar progressBar = new ProgressBar(0.0, 100.0);
	private Label labelCurrentPosition = new Label("0:00");
	private Label labelEntryName = new Label("-");

	public PlayerView(EventProvider eventProvider, PlayerService playerService, PlayListService playListService) {
		super();
		this.playerService = playerService;
		this.playListService = playListService;
		eventProvider.addListener(this);
		getStyle().set("border", "1px solid LightGray");
		comboBoxPlayList = new ComboBox<>();
		comboBoxPlayList.setItemLabelGenerator(PlayListSO::getName);
		comboBoxPlayList.addValueChangeListener(
				event -> event.getHasValue().getOptionalValue().ifPresent(playList -> playListChanged(playList)));
		comboBoxPlayList.setWidthFull();
		update();
		Button buttonNext = new Button("Next >>");
		buttonNext.addClickListener(event -> playerService.nextSong());
		Button buttonPlay = new Button("Play");
		buttonPlay.addClickListener(event -> comboBoxPlayList.getOptionalValue().ifPresent(playList -> {
			try {
				playerService.play(playListService.findById(playList.getId()).get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));
		Button buttonPause = new Button("Pause");
		buttonPause.addClickListener(event -> playerService.pause());
		Button buttonPrevious = new Button("<< Previous");
		buttonPrevious.addClickListener(event -> playerService.previousSong());
		Button buttonStop = new Button("Stop");
		buttonStop.addClickListener(event -> playerService.stop());
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.add( //
				buttonPrevious, //
				buttonPlay, //
				buttonPause, //
				buttonStop, //
				buttonNext //
		);
		HorizontalLayout labelLayout = new HorizontalLayout();
		labelLayout.add( //
				labelCurrentPosition, //
				labelEntryName //
		);
		setWidthFull();
		add( //
				comboBoxPlayList, //
				buttonLayout, //
				progressBar, //
				labelLayout //
		);
		playerService.addPlayerServiceListener(event -> {
			if ((event != null) && (event.getCurrentStats() != null)) {
				PlayerStatsSO stats = event.getCurrentStats();
				getUI().ifPresentOrElse( //
						ui -> ui.access(() -> {
							progressBar.setValue(stats.getPercent());
							labelCurrentPosition
									.setText(String.format("%02d:%02d", (int) (stats.getCurrentTime().toSeconds() / 60),
											(int) (stats.getCurrentTime().toSeconds() % 60)));
							labelEntryName.setText(stats.getEntryName());
						}), //
						() -> System.out.println("War nix!"));
			}
		});
	}

	private void update() {
		try {
			comboBoxPlayList.setItems(playListService.findAll().getResults());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void playListChanged(PlayListSO playList) {
		playerService.reset();
		labelCurrentPosition.setText("0:00");
		labelEntryName.setText("-");
		progressBar.setValue(0.0D);
	}

	@Override
	public void eventDetected(Event event) {
		if (event.getType() == EventType.PLAY_LIST_UPDATED) {
			update();
		}
	}

}