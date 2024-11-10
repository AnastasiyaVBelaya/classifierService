package by.it_academy.jd2.classifierService.service;

import by.it_academy.jd2.classifierService.DTO.operationCategory.OperationCategoryPagedResponseDTO;
import by.it_academy.jd2.classifierService.DTO.operationCategory.OperationCategoryResponseDTO;
import by.it_academy.jd2.classifierService.DTO.operationCategory.OperationCategoryRequestDTO;
import by.it_academy.jd2.classifierService.DTO.PaginationDTO;
import by.it_academy.jd2.classifierService.repository.api.IOperationCategoryRepository;
import by.it_academy.jd2.classifierService.repository.entity.OperationCategoryEntity;
import by.it_academy.jd2.classifierService.service.api.IOperationCategoryService;
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
public class OperationCategoryService implements IOperationCategoryService {

    private final IOperationCategoryRepository operationCategoryRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public OperationCategoryService(IOperationCategoryRepository operationCategoryRepository,
                                    ModelMapper modelMapper,
                                    Validator validator) {
        this.operationCategoryRepository = operationCategoryRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public void save(OperationCategoryRequestDTO operationCategoryRequestDTO) {
        validator.validateExistence(
                operationCategoryRepository.existsByTitle(operationCategoryRequestDTO.getTitle()),
                operationCategoryRequestDTO.getTitle(),
                "Категория");


        OperationCategoryEntity operationCategoryEntity = OperationCategoryEntity.builder()
                .uuid(UUID.randomUUID())
                .title(operationCategoryRequestDTO.getTitle())
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .build();

        operationCategoryRepository.save(operationCategoryEntity);
    }

    @Override
    public OperationCategoryPagedResponseDTO findAll(PaginationDTO paginationDTO) {

        Pageable pageable = PageRequest.of(paginationDTO.getPage(), paginationDTO.getSize());
        Page<OperationCategoryEntity> pageResult = operationCategoryRepository.findAll(pageable);

        List<OperationCategoryResponseDTO> categoryDTOs = pageResult.getContent().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());

        return buildPagedResponse(pageResult, categoryDTOs);
    }

    private OperationCategoryResponseDTO convertToResponseDTO(OperationCategoryEntity operationCategoryEntity) {

        OperationCategoryResponseDTO dto = modelMapper.map(operationCategoryEntity, OperationCategoryResponseDTO.class);

        dto.setDt_create(convertToUnixTime(operationCategoryEntity.getDtCreate()));
        dto.setDt_update(convertToUnixTime(operationCategoryEntity.getDtUpdate()));


        dto.setUuid(operationCategoryEntity.getUuid().toString());

        return dto;
    }

    private Long convertToUnixTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.toEpochSecond(ZoneOffset.UTC) : null;
    }

    private OperationCategoryPagedResponseDTO buildPagedResponse(Page<OperationCategoryEntity> pageResult,
                                                                 List<OperationCategoryResponseDTO> categoryDTOs) {
        return OperationCategoryPagedResponseDTO.builder()
                .content(categoryDTOs)
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
