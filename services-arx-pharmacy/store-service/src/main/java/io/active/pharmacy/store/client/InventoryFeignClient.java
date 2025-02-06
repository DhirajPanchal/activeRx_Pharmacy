package io.active.pharmacy.store.client;

import io.active.pharmacy.base.dto.DrugDto;
import io.active.pharmacy.base.dto.ListResponse;
import io.active.pharmacy.base.entity.DrugCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import static io.active.pharmacy.base.constant.RestConstants.*;
import static io.active.pharmacy.base.constant.RestConstants.PAGE_SIZE_DEFAULT;


@FeignClient(name = "inventory-service", fallback = InventoryFeignFallback.class)
public interface InventoryFeignClient {

    @GetMapping("category/{categoryId}")
    public ResponseEntity<DrugCategory> getDrugCategory(@PathVariable Long categoryId);

    @GetMapping("category/{categoryId}/class/{classId}/drug/list")
    public ResponseEntity<ListResponse<DrugDto>> listDrugs(
            @PathVariable Long categoryId,
            @PathVariable Long classId,
            @RequestParam(name = PAGE_INDEX, defaultValue = PAGE_INDEX_DEFAULT, required = false) int index,
            @RequestParam(name = PAGE_SIZE, defaultValue = PAGE_SIZE_DEFAULT, required = false) int size,
            @RequestParam(name = "like", defaultValue = "", required = false) String drugName
    );


}
