package com.glacier.common.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-11-09 22:05
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParentChildrenDto implements Serializable {

    private static final long serialVersionUID = -3487750758987093736L;
    /**
     * 父级id
     */
    private String parentId;

    /**
     * 子类id
     */
    private List<String> childrenIds;
}
