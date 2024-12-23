package co.com.entrando.dto;

import co.com.entrando.enumeration.EventStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventDto {

    private Long id;
    private String name;
    private String place;
    private LocalDate date;
    private String time;
    private int minimumAge;
    private String responsible;
    private String nit;
    private String address;
    private Long cityCode;
    private Long departmentCode;
    private EventStatus eventStatus;
    private Long categoryEventId;
}
