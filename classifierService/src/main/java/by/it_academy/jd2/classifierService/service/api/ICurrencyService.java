package by.it_academy.jd2.classifierService.service.api;


import by.it_academy.jd2.classifierService.DTO.currency.CurrencyPagedResponseDTO;
import by.it_academy.jd2.classifierService.DTO.currency.CurrencyRequestDTO;
import by.it_academy.jd2.classifierService.DTO.PaginationDTO;

public interface ICurrencyService {
    void save(CurrencyRequestDTO currencyRequestDTO);

    CurrencyPagedResponseDTO findAll(PaginationDTO paginationDTO);
}

