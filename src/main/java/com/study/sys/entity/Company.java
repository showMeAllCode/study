package com.study.sys.entity;

import java.math.BigDecimal;
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
 * 公司信息
 * </p>
 *
 * @author wxl
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_company")
@ApiModel(value="Company对象", description="公司信息")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "唯一编码")
    private String companyNum;

    @ApiModelProperty(value = "公司名")
    private String companyName;

    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "法定代表人")
    private String legalRepresentative;

    @ApiModelProperty(value = "注册资本（万元）")
    private BigDecimal registeredCapital;

    @ApiModelProperty(value = "公司地址")
    private String address;

    @ApiModelProperty(value = "官网地址")
    private String websiteAddress;

    @ApiModelProperty(value = "公司图标地址")
    private String companyLogo;

    @ApiModelProperty(value = "公司电话（可以多个如023-44951826,0236-954217451）")
    private String companyPhone;

    @ApiModelProperty(value = "业务范围")
    private String companyBussRange;

    @ApiModelProperty(value = "公司发票号")
    private String companyCard;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "公司层级一级二级三级")
    private String comType;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "经营状态")
    private String manageStatus;

    @ApiModelProperty(value = "工商注册号")
    private String commercialRegistrationNumber;

    @ApiModelProperty(value = "统一社会信用代码")
    private String unifiedSocialCreditCode;

    @ApiModelProperty(value = "组织机构代码")
    private String organizingInstitutionBarCode;

    @ApiModelProperty(value = "成立日期")
    private LocalDateTime establishDate;

    @ApiModelProperty(value = "行业")
    private String industry;

    @ApiModelProperty(value = "纳税人资质")
    private String taxpayerQualification;

    @ApiModelProperty(value = "实缴资本（万）")
    private Double contributedCapital;

    @ApiModelProperty(value = "人员规模")
    private String staffSize;

    @ApiModelProperty(value = "参保人数")
    private Integer insuredNumber;


}
