package com.study.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户的员工信息
 * </p>
 *
 * @author wxl
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_employee")
@ApiModel(value="Employee对象", description="用户的员工信息")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.UUID)
    private Long id;

    @ApiModelProperty(value = "唯一编码")
    private String employeeNum;

    @ApiModelProperty(value = "姓名")
    private String employeeName;

    @ApiModelProperty(value = "证件类型")
    private String credentialsStype;

    @ApiModelProperty(value = "证件号")
    private String cardId;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "出生日期,格式yyyy-MM-dd")
    private LocalDate birthDay;

    @ApiModelProperty(value = "国籍")
    private String nationalityType;

    @ApiModelProperty(value = "民族")
    private String nationType;

    @ApiModelProperty(value = "婚姻状况")
    private String maritalStatus;

    @ApiModelProperty(value = "籍贯")
    private String nativePlace;

    @ApiModelProperty(value = "出生地")
    private String birthPlace;

    @ApiModelProperty(value = "政治面貌")
    private String politicalStatus;

    @ApiModelProperty(value = "入党日期,yyyy-mm-dd")
    private LocalDate admissionDay;

    @ApiModelProperty(value = "参加工作时间,yyyy-mm")
    private LocalDate joinWorkDay;

    @ApiModelProperty(value = "最高学历")
    private String highestEducation;

    @ApiModelProperty(value = "最高学位")
    private String highestDegree;

    @ApiModelProperty(value = "健康状况")
    private String health;

    @ApiModelProperty(value = "专业技术职务")
    private String technicalPosition;

    @ApiModelProperty(value = "职（执）业资格")
    private String qualification;

    @ApiModelProperty(value = "最近进入系统时间")
    private LocalDateTime intoSysTime;

    @ApiModelProperty(value = "人员状态")
    private String employeeStatus;

    @ApiModelProperty(value = "进入公司时间")
    private LocalDateTime intoCompanyTime;

    @ApiModelProperty(value = "进入来源")
    private String sourceEntry;

    @ApiModelProperty(value = "户口类别")
    private String accountType;

    @ApiModelProperty(value = "户口所在地")
    private String registeredResidence;

    @ApiModelProperty(value = "现居住地")
    private String currentResidence;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "办公电话")
    private String phone;

    @ApiModelProperty(value = "手机号")
    private String tel;

    @ApiModelProperty(value = "紧急联系人")
    private String contactRelationshipName;

    @ApiModelProperty(value = "紧急联系人关系")
    private String contactRelationship;

    @ApiModelProperty(value = "紧急联系人电话")
    private String contactRelationshipPhone;

    @ApiModelProperty(value = "登录账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String passWord;

    @ApiModelProperty(value = "简拼")
    private String jianpin;

    @ApiModelProperty(value = "专长")
    private String zhuanchang;

    @ApiModelProperty(value = "开户行")
    private String openingBank;

    @ApiModelProperty(value = "银行账号")
    private String bankAccount;

    @ApiModelProperty(value = "类型 1是普通用户 2 超级用户（查看自己公司）3可以查看自己子公司 4 全部 5程序猿的")
    private Integer type;

    @ApiModelProperty(value = "开户行地址")
    private String openingBankAdress;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "档案所在地")
    private String archivesResidence;

    @ApiModelProperty(value = "年度考核结果")
    private String annualAssessmentResults;

    @ApiModelProperty(value = "入职人员表")
    private String entry;

    @ApiModelProperty(value = "状态")
    private Integer employeeState;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "试用期")
    private Integer probationaryEriod;

    @ApiModelProperty(value = "是否干部")
    private Integer cadre;

    @ApiModelProperty(value = "极光登录唯一标识id")
    private String registrationId;


}
