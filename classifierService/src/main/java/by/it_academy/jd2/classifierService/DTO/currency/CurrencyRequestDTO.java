package by.it_academy.jd2.classifierService.DTO.currency;

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
public class CurrencyRequestDTO {
    @NotBlank(message = "Название не может быть пустым")
    @Size(min = 2, message = "Название должно содержать хотя бы 2 символа")
    @Size(max = 100, message = "Название не должно превышать 100 символов")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Название должно содержать только латинские буквы")
    String title;

    @NotBlank(message = "Описание не может быть пустым")
    @Size(min = 1, message = "Название должно содержать хотя бы 1 символ")
    @Size(max = 255, message = "Описание не должно превышать 255 символов")
    String description;
}
