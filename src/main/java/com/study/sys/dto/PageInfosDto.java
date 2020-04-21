package com.study.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wxl
 * @date 2020/3/31 11:45:36
 */
@Data
@ApiModel(value="PageInfosDto实体类", description="分页信息")
public class PageInfosDto<T> implements Serializable {

    @ApiModelProperty(value = "页数")
    private Integer pageNo = 1;

    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize = 20;

    @ApiModelProperty(value = "数据总条数")
    private Integer totalNum = 20;

    @ApiModelProperty(value = "数据列表")
    private List<T> dataList;
}
