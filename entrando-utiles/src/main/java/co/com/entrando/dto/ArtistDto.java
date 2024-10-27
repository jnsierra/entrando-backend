package co.com.entrando.dto;

import lombok.Data;

@Data
public class ArtistDto {
    private Long id;
    private String name;
    private String description;
    private Long musicBandId;
}
