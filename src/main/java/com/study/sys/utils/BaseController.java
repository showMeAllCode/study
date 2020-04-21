package com.study.sys.utils;

import com.study.sys.dto.PageQueryDto;
import com.study.sys.enums.AllowOperationsEnum;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wxl
 * @date 2020/3/30 14:30:15
 */
@Slf4j
public class BaseController<T, M extends BaseService> extends BaseControllerOperationUtil<T, M> {

    @ApiOperation(value = "新增信息", notes = "id不用传")
    @PostMapping("/saveMsg")
    @ResponseBody
    public RestResult saveMsg(@ApiParam(name = "dto", required = true) T entity, HttpServletRequest request) {
        RestResult result = new RestResult();
        try {
            isAllow(AllowOperationsEnum.SAVE_MSG);
            entity = ParameterBindingUtil.requestBodyBinding(request,entity);
            this.getEntityService().saveMsg(entity);
            result.setData(entity);
        } catch (Exception e) {
            log.error("新增信息失败，失败原因：{}", e);
            result = new RestResult(500, e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "根据id修改传入属性信息", notes = "数据表中字段信息将与传入信息修改一致")
    @PostMapping("/updateJson")
    @ResponseBody
    public RestResult updateJson(@ApiParam(name = "dto", required = true) T entity, HttpServletRequest request) {
        RestResult result = new RestResult();
        try {
            isAllow(AllowOperationsEnum.UPDATE_JSON);
            entity = ParameterBindingUtil.requestBodyBinding(request,entity);
            this.getEntityService().updateJson(entity);
            result.setData(entity);
        } catch (Exception e) {
            log.error("修改整表信息失败，失败原因：{}", e);
            result = new RestResult(500, e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "根据id整表修改信息", notes = "所有信息将与传入信息修改一致")
    @PostMapping("/updateMsg")
    @ResponseBody
    public RestResult updateMsg(@ApiParam(name = "dto", required = true) T entity, HttpServletRequest request) {
        RestResult result = new RestResult();
        try {
            isAllow(AllowOperationsEnum.UPDATE_MSG);
            entity = ParameterBindingUtil.requestBodyBinding(request,entity);
            this.getEntityService().updateMsg(entity);
            result.setData(entity);
        } catch (Exception e) {
            log.error("修改整表信息失败，失败原因：{}", e);
            result = new RestResult(500, e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "根据id删除信息", notes = "id必传")
    @PostMapping("/deleteMsg")
    @ResponseBody
    public RestResult deleteMsg(@ApiParam(name = "id", example = "1", required = true)String id, HttpServletRequest request) {
        RestResult result = new RestResult();
        try {
            isAllow(AllowOperationsEnum.DELETE_MSG);
            ParameterBindingUtil.requestBodyBinding(request);
            this.getEntityService().deleteMsg(id);
        } catch (Exception e) {
            log.error("根据id删除信息，失败原因：{}", e);
            result = new RestResult(500, e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "根据id获取信息", notes = "id必传")
    @GetMapping("/getMsg")
    @ResponseBody
    public RestResult getMsg(@ApiParam(name = "id", example = "1", required = true)String id, HttpServletRequest request) {
        RestResult result = new RestResult();
        try {
            isAllow(AllowOperationsEnum.GET_MSG);
            result.setData((T) this.getEntityService().getById(id));
        } catch (Exception e) {
            log.error("根据id获取信息，失败原因：{}", e);
            result = new RestResult(500, e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "根据id列表获取信息", notes = "id使用','隔开")
    @GetMapping("/queryByIds")
    @ResponseBody
    public RestResult queryByIds(@ApiParam(name = "ids", example = "1,2,3", required = true) String ids, HttpServletRequest request) {
        RestResult result = new RestResult();
        try {
            isAllow(AllowOperationsEnum.QUERY_BY_IDS);
            result.setData(this.doQueryByIds(ids));
        } catch (Exception e) {
            log.error("根据id列表获取信息，失败原因：{}", e);
            result = new RestResult(500, e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "分页获取信息")
    @GetMapping("/getPageMsg")
    @ResponseBody
    public RestResult getPageMsg(@ApiParam(name = "PageQueryDto", required = true) PageQueryDto dto, HttpServletRequest request) {
        RestResult result = new RestResult();
        try {
            isAllow(AllowOperationsEnum.GET_PAGE_MSG);
            result.setData(this.doPageQuery(dto));
        } catch (Exception e) {
            log.error("分页获取信息，失败原因：{}", e);
            result = new RestResult(500, e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "根据条件分页获取信息")
    @GetMapping("/queryMsgByParamsEq")
    @ResponseBody
    public RestResult queryMsgByParamsEq(@ApiParam(name = "dto", required = true) T entity,
                                     @ApiParam(name = "PageQueryDto", required = true) PageQueryDto dto,
                                     HttpServletRequest request) {
        RestResult result = new RestResult();
        try {
            isAllow(AllowOperationsEnum.QUERY_MSG_BY_PARAMS_EQ);
            result.setData(this.doQueryMsgByParams(entity, dto, true, request));
        } catch (Exception e) {
            log.error("分页获取信息，失败原因：{}", e);
            result = new RestResult(500, e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "根据条件模糊查询分页信息")
    @GetMapping("/queryMsgByParamsLike")
    @ResponseBody
    public RestResult queryMsgByParamsLike(@ApiParam(name = "dto", required = true) T entity,
                                       @ApiParam(name = "PageQueryDto", required = true) PageQueryDto dto,
                                       HttpServletRequest request) {
        RestResult result = new RestResult();
        try {
            isAllow(AllowOperationsEnum.QUERY_MSG_BY_PARAMS_LIKE);
            result.setData(this.doQueryMsgByParams(entity, dto, false, request));
        } catch (Exception e) {
            log.error("分页获取信息，失败原因：{}", e);
            result = new RestResult(500, e.getMessage());
        }
        return result;
    }
}
