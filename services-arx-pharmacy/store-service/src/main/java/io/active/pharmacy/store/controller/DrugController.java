package io.active.pharmacy.store.controller;

import io.active.pharmacy.base.dto.DrugDto;
import io.active.pharmacy.base.dto.ListResponse;
import io.active.pharmacy.store.service.DrugService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static io.active.pharmacy.base.constant.RestConstants.*;
import static io.active.pharmacy.base.constant.RestConstants.PAGE_SIZE_DEFAULT;

@Slf4j
@RestController
public class DrugController {

    private final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping("test")
    public ResponseEntity<Boolean> test() {
        log.info("[STORE] GET:/test");
        Boolean testResult = this.drugService.test();
        return ResponseEntity.status(HttpStatus.OK).body(testResult);
    }


    @GetMapping("category/{categoryId}/class/{classId}/drug/list")
    public ResponseEntity<ListResponse<DrugDto>> listDrugs(
            @PathVariable Long categoryId,
            @PathVariable Long classId,
            @RequestParam(name = PAGE_INDEX, defaultValue = PAGE_INDEX_DEFAULT, required = false) int index,
            @RequestParam(name = PAGE_SIZE, defaultValue = PAGE_SIZE_DEFAULT, required = false) int size,
            @RequestParam(name = "like", defaultValue = "", required = false) String drugName

    ) {
        log.info("[STORE] GET:/listDrugs");

        //System.out.println("POST : /category/" + categoryId + "/class/" + classId + "/drug/list like=/" + drugName);

        ListResponse<DrugDto> response = this.drugService.listDrugs(categoryId, classId, drugName, index, size);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
