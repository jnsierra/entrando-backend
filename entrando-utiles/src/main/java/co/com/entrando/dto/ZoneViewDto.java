package co.com.entrando.dto;

import lombok.Data;

@Data
public class ZoneViewDto {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private String categoryName;
}
