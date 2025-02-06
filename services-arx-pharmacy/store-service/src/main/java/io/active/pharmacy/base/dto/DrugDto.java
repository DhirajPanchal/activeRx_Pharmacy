package io.active.pharmacy.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@ToString
public class DrugDto extends BaseDto {

    private Long id;

    private String drugLabelName;

    private Long categoryId;

    private String categoryName;

    private Long classId;

    private String className;

}
