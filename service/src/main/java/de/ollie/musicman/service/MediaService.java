package de.ollie.musicman.service;

import java.io.IOException;
import java.util.List;

import de.ollie.musicman.service.so.MediaSO;

/**
 * A service for media selection.
 *
 * @author ollie (07.04.2020)
 */
public interface MediaService {

	List<MediaSO> getMedia(MediaSO root) throws IOException;

}