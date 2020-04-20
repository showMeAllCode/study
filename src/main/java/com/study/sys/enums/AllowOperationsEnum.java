package com.study.sys.enums;


import com.study.sys.utils.EnumUtil;

/**
 * @author wxl
 * @date 2020/4/20 09:04:02
 */
public enum AllowOperationsEnum implements EnumUtil {
    SAVE_MSG("saveMsg", "新增信息"),
    UPDATE_JSON("updateJson", "根据id修改传入属性信息"),
    UPDATE_MSG("updateMsg", "根据id整表修改信息"),
    DELETE_MSG("deleteMsg", "根据id删除信息"),
    GET_MSG("getMsg", "根据id获取信息"),
    QUERY_BY_IDS("queryByIds", "根据id列表获取信息"),
    GET_PAGE_MSG("getPageMsg", "分页获取信息"),
    QUERY_MSG_BY_PARAMS_EQ("queryMsgByParamsEq", "根据条件分页获取信息"),
    QUERY_MSG_BY_PARAMS_LIKE("queryMsgByParamsLike", "根据条件模糊查询分页信息");

    private String value;

    private String desc;

    AllowOperationsEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    @Override
    public String getValue() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }
}
