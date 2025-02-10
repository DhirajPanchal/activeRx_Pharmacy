package io.active.pharmacy.inventory.controller;

import io.active.pharmacy.base.dto.DrugDto;
import io.active.pharmacy.base.dto.ListItem;
import io.active.pharmacy.base.dto.ListResponse;
import io.active.pharmacy.base.entity.Drug;
import io.active.pharmacy.base.entity.DrugCategory;
import io.active.pharmacy.base.entity.DrugClass;
import io.active.pharmacy.inventory.service.CategoryService;
import io.active.pharmacy.inventory.service.ClassService;
import io.active.pharmacy.inventory.service.DrugService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.active.pharmacy.base.constant.RestConstants.*;

@Slf4j
@RestController
public class InventoryController {


    private final CategoryService categoryService;

    private final ClassService classService;

    private final DrugService drugService;

    public InventoryController(CategoryService categoryService,
                               ClassService classService,
                               DrugService drugService) {
        this.categoryService = categoryService;
        this.classService = classService;
        this.drugService = drugService;
    }


    @PostMapping("category")
    public ResponseEntity<DrugCategory> addDrugCategory(@RequestBody DrugCategory payload) {
        log.info("POST:/category");

        DrugCategory drugCategory = this.categoryService.addDrugCategory(payload);

        return ResponseEntity.status(HttpStatus.CREATED).body(drugCategory);

    }

    @Retry(name = "getDrugCategory", fallbackMethod = "getDrugCategoryFallback")
    @GetMapping("category/{categoryId}")
    public ResponseEntity<DrugCategory> getDrugCategory(@PathVariable Long categoryId) {
        log.info("GET:/category/{}", categoryId);

        DrugCategory drugCategory = this.categoryService.getDrugCategory(categoryId);

        return ResponseEntity.status(HttpStatus.OK).body(drugCategory);

    }

    public ResponseEntity<DrugCategory> getDrugCategoryFallback(Long categoryId, Throwable throwable) {
        log.info("<FALLBACK> GET:/category/{}", categoryId);
        DrugCategory drugCategory = new DrugCategory(0L, "DUMMY");
        return ResponseEntity.status(HttpStatus.OK).body(drugCategory);
    }


    @GetMapping("category/list")
    public ResponseEntity<List<ListItem>> listCategories(
            @RequestParam(name = "like", required = false, defaultValue = "") String categoryName) {
        log.info("GET:/category/list");

        List<ListItem> list = this.categoryService.listCategories(categoryName);

        return ResponseEntity.status(HttpStatus.OK).body(list);

    }


    @PostMapping("category/{categoryId}/class")
    public ResponseEntity<DrugClass> addDrugClass(@PathVariable Long categoryId,
                                                  @RequestBody DrugClass payload) {
        log.info("POST:/category/{}/class", categoryId);

        DrugClass drugClass = this.classService.addDrugClass(categoryId, payload);

        return ResponseEntity.status(HttpStatus.CREATED).body(drugClass);

    }


    @GetMapping("category/{categoryId}/class/{classId}")
    public ResponseEntity<DrugClass> getDrugClass(@PathVariable Long categoryId,
                                                  @PathVariable Long classId) {
        log.info("GET:/category/{}/class/{}", categoryId, classId);

        DrugClass drugClass = this.classService.getDrugClass(classId);

        return ResponseEntity.status(HttpStatus.OK).body(drugClass);

    }


    @GetMapping("category/{categoryId}/class/list")
    public ResponseEntity<List<ListItem>> listClasses(
            @PathVariable Long categoryId,
            @RequestParam(name = "like", required = false, defaultValue = "") String className) {
        log.info("GET:/category/{}/class/list", categoryId);

        List<ListItem> list = this.classService.listClasses(categoryId, className);


        return ResponseEntity.status(HttpStatus.OK).body(list);

    }


    @PostMapping("category/{categoryId}/class/{classId}/drug")
    public ResponseEntity<DrugDto> addDrug(@PathVariable Long categoryId,
                                           @PathVariable Long classId,
                                           @RequestBody Drug payload) {
        log.info("POST:/category/{}/class/{}/drug", categoryId, classId);

        DrugDto drugDto = this.drugService.addDrug(categoryId, classId, payload);

        return ResponseEntity.status(HttpStatus.CREATED).body(drugDto);

    }

    @GetMapping("category/{categoryId}/class/{classId}/drug/{drugId}")
    public ResponseEntity<DrugDto> getDrug(@PathVariable Long categoryId,
                                           @PathVariable Long classId,
                                           @PathVariable Long drugId) {
        log.info("GET:/category/{}/class/{}/drug/{}", categoryId, classId, drugId);

        DrugDto drugDto = this.drugService.getDrug(drugId);

        return ResponseEntity.status(HttpStatus.OK).body(drugDto);
    }


    @GetMapping("category/{categoryId}/class/{classId}/drug/list")
    public ResponseEntity<ListResponse<DrugDto>> listDrugs(
            @PathVariable Long categoryId,
            @PathVariable Long classId,
            @RequestParam(name = PAGE_INDEX, defaultValue = PAGE_INDEX_DEFAULT, required = false) int index,
            @RequestParam(name = PAGE_SIZE, defaultValue = PAGE_SIZE_DEFAULT, required = false) int size,
            @RequestParam(name = "like", defaultValue = "", required = false) String drugName
    ) {
        log.info("GET:/category/{}/class/{}/drug/list", categoryId, classId);

        ListResponse<DrugDto> response = this.drugService.listDrugs(categoryId, classId, drugName, index, size);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
