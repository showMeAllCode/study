package com.study.sys.utils;

import com.study.sys.dto.PageQueryDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public RestResult<T> saveMsg(@ApiParam(name = "dto", required = true) T entity) {
        RestResult<T> result = RestResult.success();
        try {
            this.getEntityService().saveMsg(entity);
            result.setData(entity);
        } catch (Exception e) {
            log.error("新增信息失败，失败原因：{}", e);
            result = RestResult.failure();
        }

        return result;
    }

    @ApiOperation(value = "根据id整表修改信息", notes = "所有信息将与传入信息修改一致")
    @PostMapping("/updateMsg")
    @ResponseBody
    public RestResult<T> updateMsg(@ApiParam(name = "dto", required = true) T entity) {
        RestResult<T> result = RestResult.success();
        try {
            this.getEntityService().updateMsg(entity);
            result.setData(entity);
        } catch (Exception e) {
            log.error("修改整表信息失败，失败原因：{}", e);
            result = RestResult.failure();
        }
        return result;
    }

    @ApiOperation(value = "根据id删除信息", notes = "id必传")
    @PostMapping("/deleteMsg")
    @ResponseBody
    public RestResult<T> deleteMsg(@ApiParam(name = "id", example = "1", required = true) Long id) {
        RestResult<T> result = RestResult.success();
        try {
            this.getEntityService().deleteMsg(id);
        } catch (Exception e) {
            log.error("根据id删除信息，失败原因：{}", e);
            result = RestResult.failure();
        }
        return result;
    }

    @ApiOperation(value = "根据id获取信息", notes = "id必传")
    @GetMapping("/getMsg")
    @ResponseBody
    public RestResult<T> getMsg(@ApiParam(name = "id", example = "1", required = true) @RequestParam("id") Long id) {
        RestResult<T> result = RestResult.success();
        try {
            result.setData((T) this.getEntityService().getById(id));
        } catch (Exception e) {
            log.error("根据id获取信息，失败原因：{}", e);
            result = RestResult.failure();
        }
        return result;
    }

    @ApiOperation(value = "根据id列表获取信息", notes = "id使用','隔开")
    @GetMapping("/queryByIds")
    @ResponseBody
    public RestResult<T> queryByIds(@ApiParam(name = "ids", example = "1,2,3", required = true) @RequestParam("ids") String ids) {
        RestResult<T> result = RestResult.success();
        try {
            result.setPageInfos(this.doQueryByIds(ids));
        } catch (Exception e) {
            log.error("根据id列表获取信息，失败原因：{}", e);
            result = RestResult.failure();
        }
        return result;
    }

    @ApiOperation(value = "分页获取信息")
    @PostMapping("/getPageMsg")
    @ResponseBody
    public RestResult<T> getPageMsg(@ApiParam(name = "PageQueryDto", required = true) PageQueryDto dto) {
        RestResult<T> result = RestResult.success();
        try {
            result.setPageInfos(this.doPageQuery(dto));
        } catch (Exception e) {
            log.error("分页获取信息，失败原因：{}", e);
            result = RestResult.failure();
        }
        return result;
    }

    @ApiOperation(value = "根据条件分页获取信息")
    @PostMapping("/queryMsgByParamsEq")
    @ResponseBody
    public RestResult<T> queryMsgByParamsEq(@ApiParam(name = "dto", required = true) T entity,
                                            @ApiParam(name = "PageQueryDto", required = true) PageQueryDto dto,
                                            HttpServletRequest request) {
        RestResult<T> result = RestResult.success();
        try {
            result.setPageInfos(this.doQueryMsgByParams(entity, dto, true, request));
        } catch (Exception e) {
            log.error("分页获取信息，失败原因：{}", e);
            result = RestResult.failure();
        }
        return result;
    }

    @ApiOperation(value = "根据条件模糊查询分页信息")
    @PostMapping("/queryMsgByParamsLike")
    @ResponseBody
    public RestResult<T> queryMsgByParamsLike(@ApiParam(name = "dto", required = true) T entity,
                                              @ApiParam(name = "PageQueryDto", required = true) PageQueryDto dto,
                                              HttpServletRequest request) {
        RestResult<T> result = RestResult.success();
        try {
            result.setPageInfos(this.doQueryMsgByParams(entity, dto, false, request));
        } catch (Exception e) {
            log.error("分页获取信息，失败原因：{}", e);
            result = RestResult.failure();
        }
        return result;
    }
}
