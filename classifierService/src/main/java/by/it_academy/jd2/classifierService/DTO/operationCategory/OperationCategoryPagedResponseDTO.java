package by.it_academy.jd2.classifierService.DTO.operationCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationCategoryPagedResponseDTO {
    private List<OperationCategoryResponseDTO> content;

    private long total_elements;
    private int total_pages;
    private int number;
    private int size;

    private boolean first;
    private int number_of_elements;
    private boolean last;
}