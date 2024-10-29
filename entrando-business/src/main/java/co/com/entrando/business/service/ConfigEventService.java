package co.com.entrando.business.service;

public interface ConfigEventService {
    Long getNumberOfTicketsEventAndPresentation(Long idEvent, Long idPresentation);
    boolean existsConfigEvent(Long idEvent, Long idPresentation);
}
