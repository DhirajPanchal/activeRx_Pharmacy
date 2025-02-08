package io.active.pharmacy.store.controller;

import io.active.pharmacy.base.dto.DrugDto;
import io.active.pharmacy.base.dto.ListResponse;
import io.active.pharmacy.base.entity.DrugCategory;
import io.active.pharmacy.store.service.DrugService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.active.pharmacy.base.constant.RestConstants.*;

@Slf4j
@RestController
public class DrugController {

    private final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping("test")
    public ResponseEntity<DrugCategory> test() {
        // log.info("GET:/test");

        DrugCategory drugCategory = this.drugService.getDrugCategory();

        return ResponseEntity.status(HttpStatus.OK).body(drugCategory);

    }


    @GetMapping("category/{categoryId}/class/{classId}/drug/list")
    public ResponseEntity<ListResponse<DrugDto>> listDrugs(
            @PathVariable Long categoryId,
            @PathVariable Long classId,
            @RequestParam(name = PAGE_INDEX, defaultValue = PAGE_INDEX_DEFAULT, required = false) int index,
            @RequestParam(name = PAGE_SIZE, defaultValue = PAGE_SIZE_DEFAULT, required = false) int size,
            @RequestParam(name = "like", defaultValue = "", required = false) String drugName

    ) {
        log.info("GET:/listDrugs");

        ListResponse<DrugDto> response = this.drugService.listDrugs(categoryId, classId, drugName, index, size);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
