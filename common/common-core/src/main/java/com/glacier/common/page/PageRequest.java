package com.glacier.common.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-14 15:53
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest<T> implements Serializable {
    private static final long serialVersionUID = 4076080201697869870L;
    /**
     * 当前页码
     */
    private int current = 1;
    /**
     * 每页数量
     */
    private int size = 10;
    /**
     * 查询参数
     */
    private T params;

}