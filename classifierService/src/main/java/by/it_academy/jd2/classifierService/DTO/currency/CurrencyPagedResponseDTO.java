package by.it_academy.jd2.classifierService.DTO.currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyPagedResponseDTO {

    private List<CurrencyResponseDTO> content;

    private long total_elements;
    private int total_pages;
    private int number;
    private int size;

    private boolean first;
    private int number_of_elements;
    private boolean last;
}
