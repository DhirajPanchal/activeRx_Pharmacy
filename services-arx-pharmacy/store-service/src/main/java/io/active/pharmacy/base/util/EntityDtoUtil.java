package io.active.pharmacy.base.util;

import io.active.pharmacy.base.dto.DrugDto;
import io.active.pharmacy.base.dto.ListItem;
import io.active.pharmacy.base.entity.Drug;
import io.active.pharmacy.base.entity.DrugCategory;

public class EntityDtoUtil {

    public static DrugDto toDrugDto(Drug entity) {

        DrugDto dto = new DrugDto();

        dto.setId(entity.getId());
        dto.setDrugLabelName(entity.getDrugLabelName());
        dto.setCategoryId(entity.getDrugCategory().getId());
        dto.setCategoryName(entity.getDrugCategory().getCategoryName());
        dto.setClassId(entity.getDrugClass().getId());
        dto.setClassName(entity.getDrugClass().getClassName());
        dto.setIsActive(entity.getIsActive());
        dto.setIsDeleted(entity.getIsDeleted());
        dto.setUpdatedOn(entity.getUpdatedOn());
        dto.setCreatedOn(entity.getCreatedOn());

        return dto;

    }

    public static ListItem toListItemDto(DrugCategory category) {

        return new ListItem(category.getId(), category.getCategoryName());

    }

}
