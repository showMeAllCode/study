package com.study.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wxl
 * @date 2020/3/31 14:25:13
 */
@Data
@ApiModel(value="PageQueryDto对象", description="分页查询入参")
public class PageQueryDto implements Serializable {

    @ApiModelProperty(value = "页数")
    private Integer pageNo = 1;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize = 20;
}
