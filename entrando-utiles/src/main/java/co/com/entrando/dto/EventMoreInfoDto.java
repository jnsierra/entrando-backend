package co.com.entrando.dto;

import co.com.entrando.enumeration.EventStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventMoreInfoDto {

    private Long id;
    private String name;
    private String place;
    private LocalDate date;
    private String time;
    private int minimumAge;
    private String responsible;
    private String nit;
    private String address;
    private String cityName;
    private String departmentName;
    private String countryName;
    private EventStatus eventStatus;
    private String categoryEventName;
    private List<PresentationDto> presentation;
    private List<ConfigEventDto> configEvents;
}
