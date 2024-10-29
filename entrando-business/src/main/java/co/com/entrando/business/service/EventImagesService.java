package co.com.entrando.business.service;

import co.com.entrando.dto.EventImagesDto;


public interface EventImagesService {

    EventImagesDto save(EventImagesDto eventImages);
    EventImagesDto getById(Long id);
}
