package co.com.entrando.pub.service;

import co.com.entrando.dto.EventImagesDto;
import co.com.entrando.enumeration.TypeImages;

import java.util.Set;

public interface EventImagesService {
    Set<EventImagesDto> findByEventAndTypeImages(Long idEvent, TypeImages typeImages);
}
