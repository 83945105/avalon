package com.global.config;

import pub.avalon.global.config.message.MessageConfig;
import pub.avalon.holygrail.response.utils.ResultUtil;
import feign.FeignException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pub.avalon.holygrail.response.beans.ResultInfo;
import pub.avalon.holygrail.response.exception.NeedLoginException;
import pub.avalon.holygrail.response.exception.NoAuthorityException;
import pub.avalon.holygrail.response.exception.NotFoundException;
import pub.avalon.holygrail.response.exception.ResultException;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.response.views.ExceptionView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author baichao
 * @date 2018-1-20
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 404异常
     *
     * @param request
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    DataView notFound(HttpServletRequest request, NotFoundException ex) throws Exception {
        return new ExceptionView(0, ex.getResultInfo());
    }

    /**
     * 需要登录
     *
     * @param request
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(NeedLoginException.class)
    @ResponseBody
    DataView needLogin(HttpServletRequest request, NeedLoginException ex) throws Exception {
        return new ExceptionView(0, ex.getResultInfo());
    }

    /**
     * 无权
     *
     * @param request
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(NoAuthorityException.class)
    @ResponseBody
    DataView noAuthority(HttpServletRequest request, NoAuthorityException ex) throws Exception {
        return new ExceptionView(0, ex.getResultInfo());
    }

    /**
     * Feign异常
     *
     * @param request
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(FeignException.class)
    @ResponseBody
    DataView feign(HttpServletRequest request, FeignException ex) throws Exception {
        ex.printStackTrace();
        return new ExceptionView(0, ResultUtil.createFail(MessageConfig.EXCEPTION_FEIGN_MESSAGE));
    }

    @ExceptionHandler(ResultException.class)
    @ResponseBody
    DataView result(HttpServletRequest request, ResultException ex) throws Exception {
        ResultInfo resultInfo = ex.getResultInfo();
        if (resultInfo.isError()) {
            ex.printStackTrace();
        }
        return new ExceptionView(0, resultInfo);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    DataView exceptionHandler(HttpServletRequest request, Exception ex) throws Exception {
        ex.printStackTrace();
        return new ExceptionView(0, ResultUtil.createError(MessageConfig.EXCEPTION_DEFAULT_MESSAGE));
    }

}
