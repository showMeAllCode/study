package com.study.sys.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wxl
 * @date 2020/4/1 11:28:58
 */
@Slf4j
public class FileOperationUtil {

    private final String filePathPrefix = ".files.";

    public List<String> uploadFile(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultiValueMap<String, MultipartFile> multipartFiles = multiRequest.getMultiFileMap();
        if (multipartFiles.size() == 0) {
            throw new RuntimeException("无可上传文件");
        }
        List<String> filePathList = new ArrayList<String>();
        String datePath = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String filePath = filePathPrefix + datePath;
        String filepath = new File("..").getCanonicalPath() + filePath.replace('.', File.separatorChar);
        log.info("文件上传路径位置-------->>>>>>>>>>>>" + filepath);
        Iterator var5 = multipartFiles.entrySet().iterator();
        while (var5.hasNext()) {
            Map.Entry<String, List<MultipartFile>> entry = (Map.Entry) var5.next();
            List<MultipartFile> listFile = (List) entry.getValue();
            listFile.forEach(file -> {
                if (!file.isEmpty()) {
                    String fileName = file.getOriginalFilename();
                    String fileType = fileName.substring(fileName.lastIndexOf('.'));
                    log.debug("上传来的文件类型------->>>>>>>>>" + fileType);
                    String newFileName = fileName.substring(0, fileName.lastIndexOf('.'));
                    log.debug("上传来的文件名称------->>>>>>>>>" + newFileName);
                    String newFilePath = filepath + File.separatorChar + newFileName + fileType;
                    log.info("拼接好的文件路径地址------------->>>>>>>>" + newFilePath);
                    File dest = new File(filepath);
                    if (!dest.exists()) {
                        dest.mkdirs();
                    }
                    File uploadFile = new File(newFilePath);
                    if (uploadFile.exists()) {
                        uploadFile.delete();
                    }
                    log.info("开始将写入文件写入服务器-------------->>>>>>> " + fileName);
                    try {
                        file.transferTo(uploadFile);
                    } catch (IOException e) {
                        log.error("文件写入失败，失败原因：{}", e);
                        throw new RuntimeException("文件写入失败");
                    } catch (IllegalStateException e) {
                        log.error("文件写入失败，失败原因：{}", e);
                        throw new RuntimeException("文件写入失败");
                    }
                    String filepathUrl = "http://10.35.11.238:8080/files/" + datePath + "/" + newFileName + fileType;
                    log.info("返回路径---------------->>>>>>>>>>" + filepathUrl);
                    filePathList.add(filepathUrl);
                }
            });
        }
        return filePathList;
    }
}
