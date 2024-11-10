package by.it_academy.jd2.classifierService.service.api;


import by.it_academy.jd2.classifierService.DTO.PaginationDTO;
import by.it_academy.jd2.classifierService.DTO.operationCategory.OperationCategoryPagedResponseDTO;
import by.it_academy.jd2.classifierService.DTO.operationCategory.OperationCategoryRequestDTO;

public interface IOperationCategoryService {
    void save(OperationCategoryRequestDTO operationCategoryRequestDTO);

    OperationCategoryPagedResponseDTO findAll(PaginationDTO paginationDTO);
}
