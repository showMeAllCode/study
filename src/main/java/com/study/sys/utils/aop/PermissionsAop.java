//package com.study.sys.utils.aop;
//
//import com.mapscience.utils.SecurityUtil;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author wxl
// * @date 2020/4/29 14:54:04
// */
//@Component
//@Aspect
//public class PermissionsAop {
//
//    @Autowired
//    private HttpServletRequest request;
//
//    @Before(value = "@annotation(CheckPermissions)")
//    public void checkPermissions(){
//        StringBuffer requestURL = request.getRequestURL();
//        SecurityUtil.getSession().getAttribute("");
//        if(requestURL)
//
//    }
//}
