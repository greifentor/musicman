package de.ollie.musicman.gui;

import java.io.File;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import de.ollie.musicman.service.MediaService;
import de.ollie.musicman.service.so.MediaSO;
import de.ollie.musicman.service.so.MediaTypeSO;
import de.ollie.musicman.service.so.PlayListEntrySO;
import de.ollie.musicman.service.so.PlayListSO;

/**
 * A layout with controls for media selection.
 *
 * @author ollie (07.04.2020)
 */
public class SelectMediaLayout extends HorizontalLayout {

	private Button buttonAdd = new Button("Add >>");
	private Button buttonRemove = new Button("Remove");
	private Button buttonSave = new Button("Save");
	private Grid<MediaSO> gridMedia = new Grid<>(15);
	private Grid<PlayListEntrySO> gridSelected = new Grid<>(15);
	private PlayListSO playList;

	private MediaService mediaService;

	public SelectMediaLayout(MediaService mediaService, PlayListSO playList) {
		super();
		this.mediaService = mediaService;
		this.playList = playList;
		gridMedia.addColumn(MediaSO::getType).setHeader("Type").setFlexGrow(5);
		gridMedia.addColumn(MediaSO::getName).setHeader("Name").setFlexGrow(95);
		gridMedia.addItemDoubleClickListener(event -> {
			if (event.getItem().getType() == MediaTypeSO.FOLDER) {
				updateItems(event.getItem().getPath());
			}
		});
		gridMedia.addItemClickListener(event -> buttonAdd.setEnabled(true));
		gridMedia.setWidthFull();
		gridMedia.setSelectionMode(SelectionMode.MULTI);
		gridSelected.addColumn(PlayListEntrySO::getName).setHeader("Name");
		gridSelected.setWidthFull();
		gridSelected.addItemClickListener(event -> buttonRemove.setEnabled(true));
		gridSelected.setItems(playList.getEntries());
		gridSelected.setSelectionMode(SelectionMode.MULTI);
		setWidthFull();
		buttonAdd.setEnabled(false);
		buttonAdd.addClickListener(event -> copyToSelected());
		buttonRemove.setEnabled(false);
		buttonRemove.addClickListener(event -> removeFromSelected());
		VerticalLayout buttons = new VerticalLayout();
		buttons.add( //
				buttonAdd, //
				buttonRemove //
		);
		buttons.setWidth("10%");
		add( //
				gridMedia, //
				buttons, //
				gridSelected //
		);
		updateItems("/home/ollie/Musik");
	}

	private void updateItems(String path) {
		try {
			List<MediaSO> media = mediaService.getMedia(MediaSO.of(null, null, path));
			if (!path.equals("/")) {
				File f = new File(path + "/..");
				media.add(0, MediaSO.of(MediaTypeSO.FOLDER, "..", f.getCanonicalPath()));
			}
			gridMedia.setItems(media);
		} catch (Exception e) {
			System.out.println("error while loading media: " + e.getMessage());
		}
	}

	private void copyToSelected() {
		for (MediaSO media : gridMedia.getSelectedItems()) {
			playList.addEntries(new PlayListEntrySO() //
					.setName(media.getName()) //
					.setPath(media.getPath()) //
			);
		}
		gridSelected.setItems(playList.getEntries());
		gridMedia.deselectAll();
		buttonAdd.setEnabled(false);
	}

	private void removeFromSelected() {
		for (PlayListEntrySO entry : gridSelected.getSelectedItems()) {
			playList.removeEntries(entry);
		}
		gridSelected.deselectAll();
		gridSelected.setItems(playList.getEntries());
		buttonRemove.setEnabled(false);
	}

	public void setPlayList(PlayListSO playList) {
		this.playList = playList;
		buttonAdd.setEnabled(false);
		buttonRemove.setEnabled(false);
		gridMedia.deselectAll();
		gridSelected.setItems(this.playList.getEntries());
		gridSelected.deselectAll();
	}

}