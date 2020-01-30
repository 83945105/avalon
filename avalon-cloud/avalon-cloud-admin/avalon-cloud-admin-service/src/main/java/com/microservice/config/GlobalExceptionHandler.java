package com.microservice.config;

import feign.FeignException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pub.avalon.holygrail.response.beans.ResultInfo;
import pub.avalon.holygrail.response.exception.NeedLoginException;
import pub.avalon.holygrail.response.exception.NoAuthorityException;
import pub.avalon.holygrail.response.exception.NotFoundException;
import pub.avalon.holygrail.response.exception.ResultException;
import pub.avalon.holygrail.response.utils.ResultUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.response.views.ExceptionView;
import pub.avalonframework.core.beans.MessageConstant;
import pub.avalonframework.shiro.exception.ThirdPartyLoginException;

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
     * 角色授权异常
     *
     * @param request
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    DataView checkRole(HttpServletRequest request, UnauthenticatedException ex) throws Exception {
        return new ExceptionView(0, ResultUtil.createFail(MessageConstant.EXCEPTION_UNAUTHENTICATED_MESSAGE));
    }

    /**
     * 角色检验异常
     *
     * @param request
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    DataView checkRole(HttpServletRequest request, UnauthorizedException ex) throws Exception {
        return new ExceptionView(0, ResultUtil.createFail(MessageConstant.EXCEPTION_UNAUTHORIZED_MESSAGE));
    }

    /**
     * 第三方登录异常
     *
     * @param request
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(ThirdPartyLoginException.class)
    @ResponseBody
    DataView thirdPartyLogin(HttpServletRequest request, ThirdPartyLoginException ex) throws Exception {
        return new ExceptionView(0, ResultUtil.createFail(MessageConstant.EXCEPTION_THIRD_PARTY_LOGIN_MESSAGE));
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
        return new ExceptionView(0, ResultUtil.createFail(MessageConstant.EXCEPTION_FEIGN_MESSAGE));
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
        return new ExceptionView(0, ResultUtil.createError(MessageConstant.EXCEPTION_DEFAULT_MESSAGE));
    }
}