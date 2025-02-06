package io.active.pharmacy.store.service;

import io.active.pharmacy.base.dto.DrugDto;
import io.active.pharmacy.base.dto.ListResponse;
import io.active.pharmacy.base.entity.DrugCategory;
import io.active.pharmacy.store.client.InventoryFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DrugService {

    private final InventoryFeignClient inventoryFeignClient;

    public DrugService(InventoryFeignClient inventoryFeignClient) {
        this.inventoryFeignClient = inventoryFeignClient;
    }

    public Boolean test() {
        log.info("[STORE] test()");
        ResponseEntity<DrugCategory> response = this.inventoryFeignClient.getDrugCategory(Long.valueOf(1));
        System.out.println(response);
        System.out.println(response.getBody());

        DrugCategory drugCategory = response.getBody();
        log.info("[STORE] test() drugCategory :: {} ", drugCategory);
        return true;
    }

    public ListResponse<DrugDto> listDrugs(Long categoryId, Long classId, String drugName, int index, int size) {
        log.info("[STORE] listDrugs()");
        ResponseEntity<ListResponse<DrugDto>> response =
                this.inventoryFeignClient.listDrugs(categoryId, classId, index, size, drugName);
        ListResponse<DrugDto> drugList = response.getBody();
        log.info("[STORE] listDrugs() drugList :: ()", drugList);
        return drugList;
    }
}
