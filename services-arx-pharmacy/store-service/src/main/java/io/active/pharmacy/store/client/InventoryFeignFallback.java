package io.active.pharmacy.store.client;


import io.active.pharmacy.base.dto.DrugDto;
import io.active.pharmacy.base.dto.ListResponse;
import io.active.pharmacy.base.entity.DrugCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryFeignFallback implements InventoryFeignClient {

    @Override
    public ResponseEntity<DrugCategory> getDrugCategory(Long categoryId) {
        log.warn("[STORE] InventoryFeignFallback . getDrugCategory () {} ", categoryId);
        //System.out.println(" ***InventoryFeignFallback getDrugCategory : "+categoryId);
        return null;
    }

    @Override
    public ResponseEntity<ListResponse<DrugDto>> listDrugs(Long categoryId, Long classId, int index, int size, String drugName) {
        log.warn("[STORE] InventoryFeignFallback . listDrugs () ");
        //System.out.println(" ***InventoryFeignFallback listDrugs : "+categoryId);
        return null;
    }
}
