package by.it_academy.jd2.classifierService.controller;


import by.it_academy.jd2.classifierService.DTO.PaginationDTO;
import by.it_academy.jd2.classifierService.DTO.operationCategory.OperationCategoryPagedResponseDTO;
import by.it_academy.jd2.classifierService.DTO.operationCategory.OperationCategoryRequestDTO;
import by.it_academy.jd2.classifierService.service.api.IOperationCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1/classifier/operation/category")
public class OperationCategoryController {
    private final IOperationCategoryService operationCategoryService;

    public OperationCategoryController(IOperationCategoryService operationCategoryService) {
        this.operationCategoryService = operationCategoryService;
    }

    @GetMapping
    public OperationCategoryPagedResponseDTO get(@Valid PaginationDTO paginationDTO)  {
        return operationCategoryService.findAll(paginationDTO);
    }
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@Valid @RequestBody OperationCategoryRequestDTO operationCategoryRequestDTO) {
        operationCategoryService.save(operationCategoryRequestDTO);
    }
}
