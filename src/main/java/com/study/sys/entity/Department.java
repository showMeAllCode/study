package com.study.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门信息
 * </p>
 *
 * @author wxl
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_department")
@ApiModel(value="Department对象", description="部门信息")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "角色名")
    private String departmentName;

    @ApiModelProperty(value = "角色描述")
    private String departmentDetail;

    @ApiModelProperty(value = "公司id")
    private String companyId;

    @ApiModelProperty(value = "角色状态:{normal:正常，freeze:冻结}")
    private String departmentStatuts;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;


}
