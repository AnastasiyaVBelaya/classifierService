package by.it_academy.jd2.classifierService.controller;


import by.it_academy.jd2.classifierService.DTO.currency.CurrencyPagedResponseDTO;
import by.it_academy.jd2.classifierService.DTO.currency.CurrencyRequestDTO;
import by.it_academy.jd2.classifierService.DTO.PaginationDTO;
import by.it_academy.jd2.classifierService.service.api.ICurrencyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1/classifier/currency")
public class CurrencyController {

    private final ICurrencyService currencyService;

    public CurrencyController(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public CurrencyPagedResponseDTO get(@Valid PaginationDTO paginationDTO) {
        return currencyService.findAll(paginationDTO);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@Valid @RequestBody CurrencyRequestDTO currencyRequestDTO) {
        currencyService.save(currencyRequestDTO);
    }
}
