package by.it_academy.jd2.classifierService.DTO.currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyResponseDTO {

    private String uuid;
    private Long dt_create;
    private Long dt_update;
    private String title;
    private String description;
}
