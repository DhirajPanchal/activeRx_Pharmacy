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

    public DrugCategory getDrugCategory() {
        //log.info("DrugService . getDrugCategory()");

        ResponseEntity<DrugCategory> response = this.inventoryFeignClient.getDrugCategory(Long.valueOf(1));

        DrugCategory drugCategory = response.getBody();

        return drugCategory;
    }

    public ListResponse<DrugDto> listDrugs(Long categoryId, Long classId, String drugName, int index, int size) {

        log.info("DrugService . listDrugs()");

        ResponseEntity<ListResponse<DrugDto>> response =
                this.inventoryFeignClient.listDrugs(categoryId, classId, index, size, drugName);

        ListResponse<DrugDto> drugList = response.getBody();

        return drugList;
    }
}
