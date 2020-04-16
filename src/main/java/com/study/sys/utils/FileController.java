package com.study.sys.utils;

import com.study.sys.utils.FileOperationUtil;
import com.study.sys.utils.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wxl
 * @date 2020/4/1 15:16:50
 *
 */
@Slf4j
@Api(value = "FileController控制类", tags = "文件控制类")
@Controller
public class FileController extends FileOperationUtil {

    @ApiOperation(value = "文件上传")
    @PostMapping(value = "/uploadFile", headers = "content-type=multipart/form-data")
    @ResponseBody
    public RestResult uploadFile(HttpServletRequest request, HttpServletResponse response){
        RestResult<List<String>> result = RestResult.success();
        try {
            result.setData(this.uploadFile(request));
        }catch (Exception e){
            log.error("文件上传失败，原因：{}", e);
            result = RestResult.failure();
        }
        return result;
    }
}
