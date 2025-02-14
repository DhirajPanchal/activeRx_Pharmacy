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
        //log.warn("InventoryFeignFallback . getDrugCategory () {} ", categoryId);
        System.out.println("                      [ STORE ] <FALLBACK> GET : /category/"+categoryId);
        return null;
    }

    @Override
    public ResponseEntity<ListResponse<DrugDto>> listDrugs(Long categoryId, Long classId, int index, int size, String drugName) {
        //log.warn("InventoryFeignFallback . listDrugs () ");
        System.out.println("                      [ STORE ] <FALLBACK> GET : category/"+categoryId+"/class/"+classId+"/drug/list");
        return null;
    }
}
