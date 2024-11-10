package by.it_academy.jd2.classifierService.DTO.operationCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationCategoryRequestDTO {
    @NotBlank(message = "Название не может быть пустым")
    @Size(min = 3, message = "Название должно содержать хотя бы 3 символа")
    @Size(max = 100, message = "Название не должно превышать 100 символов")
    @Pattern(regexp = "^[А-Яа-я]+$", message = "Название должно содержать только русские буквы")
    private String title;
}
