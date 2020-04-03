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
 * 功能权限
 * </p>
 *
 * @author wxl
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permission")
@ApiModel(value="Permission对象", description="功能权限")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "权限名")
    private String permissionName;

    @ApiModelProperty(value = "权限链接地址")
    private String permissionUrl;

    @ApiModelProperty(value = "权限描述")
    private String permissionDetail;

    @ApiModelProperty(value = "菜单级别（0、1、2、3、4...）")
    private Integer level;

    @ApiModelProperty(value = "隶属上级菜单ID")
    private String parentId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;


}
