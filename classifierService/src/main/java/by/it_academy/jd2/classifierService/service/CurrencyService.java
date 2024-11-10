package by.it_academy.jd2.classifierService.service;

import by.it_academy.jd2.classifierService.DTO.currency.CurrencyPagedResponseDTO;
import by.it_academy.jd2.classifierService.DTO.currency.CurrencyResponseDTO;
import by.it_academy.jd2.classifierService.DTO.currency.CurrencyRequestDTO;
import by.it_academy.jd2.classifierService.DTO.PaginationDTO;
import by.it_academy.jd2.classifierService.repository.api.ICurrencyRepository;
import by.it_academy.jd2.classifierService.repository.entity.CurrencyEntity;
import by.it_academy.jd2.classifierService.service.api.ICurrencyService;
import by.it_academy.jd2.classifierService.service.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CurrencyService implements ICurrencyService {

    private final ICurrencyRepository currencyRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public CurrencyService(ICurrencyRepository currencyRepository,
                           ModelMapper modelMapper,
                           Validator validator) {
        this.currencyRepository = currencyRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public void save(CurrencyRequestDTO currencyRequestDTO) {
        validator.validateExistence(
                currencyRepository.existsByTitle(currencyRequestDTO.getTitle()),
                currencyRequestDTO.getTitle(),
                "Валюта");

        CurrencyEntity currencyEntity = CurrencyEntity.builder()
                .uuid(UUID.randomUUID())
                .title(currencyRequestDTO.getTitle())
                .description(currencyRequestDTO.getDescription())
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .build();

        currencyRepository.save(currencyEntity);
    }

    @Override
    public CurrencyPagedResponseDTO findAll(PaginationDTO paginationDTO) {
        System.out.println("Finding all currencies with pagination: " + paginationDTO);
        Pageable pageable = PageRequest.of(paginationDTO.getPage(), paginationDTO.getSize());
        Page<CurrencyEntity> pageResult = currencyRepository.findAll(pageable);

        List<CurrencyResponseDTO> currencyDTOs = pageResult.getContent().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());

        return buildPagedResponse(pageResult, currencyDTOs);
    }

    private CurrencyResponseDTO convertToResponseDTO(CurrencyEntity currencyEntity) {
        CurrencyResponseDTO dto = modelMapper.map(currencyEntity, CurrencyResponseDTO.class);
        dto.setDt_create(convertToUnixTime(currencyEntity.getDtCreate()));
        dto.setDt_update(convertToUnixTime(currencyEntity.getDtUpdate()));
        return dto;
    }

    private Long convertToUnixTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.toEpochSecond(ZoneOffset.UTC) : null;
    }

    private CurrencyPagedResponseDTO buildPagedResponse(Page<CurrencyEntity> pageResult,
                                                        List<CurrencyResponseDTO> currencyDTOs) {
        return CurrencyPagedResponseDTO.builder()
                .content(currencyDTOs)
                .total_elements(pageResult.getTotalElements())
                .total_pages(pageResult.getTotalPages())
                .number(pageResult.getNumber())
                .size(pageResult.getSize())
                .first(pageResult.isFirst())
                .number_of_elements(pageResult.getNumberOfElements())
                .last(pageResult.isLast())
                .build();
    }
}
