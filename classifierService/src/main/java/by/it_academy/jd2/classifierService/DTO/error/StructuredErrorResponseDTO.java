package by.it_academy.jd2.classifierService.DTO.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StructuredErrorResponseDTO {

    private String logref;
    private List<ErrorDetailDTO> errors;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ErrorDetailDTO {
        private String message;
        private String field;
    }
}
