package de.ollie.musicman.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import de.ollie.musicman.service.MediaService;
import de.ollie.musicman.service.so.MediaSO;
import de.ollie.musicman.service.so.MediaTypeSO;

/**
 * An implementation of the media service.
 *
 * @author ollie (07.04.2020)
 */
@Named
public class MediaServiceImpl implements MediaService {

	@Override
	public List<MediaSO> getMedia(MediaSO root) throws IOException {
		try {
			List<MediaSO> media = new ArrayList<>();
			for (File file : new File(root.getPath()).listFiles()) {
				System.out.println(file);
				if (file.isDirectory() || file.getAbsolutePath().toLowerCase().endsWith(".mp3")) {
					media.add(MediaSO.of((file.isDirectory() ? MediaTypeSO.FOLDER : MediaTypeSO.MP3), file.getName(),
							file.getAbsolutePath()));
				}
			}
			media.sort((m0, m1) -> m0.getName().compareTo(m1.getName()));
			return media;
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(
					"something went wrong while getting media from: " + root + ", message=" + e.getMessage());
		}
	}

}