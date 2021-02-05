package com.lxz.user.filter;

import com.lxz.user.annotation.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Description:
 *HandlerMethodArgumentResolver   方法参数解析器
 *
 */

public class CurrentUserResolver implements HandlerMethodArgumentResolver {

    /**
     * supportsParameter（满足某种要求，返回true，方可进入resolveArgument做参数处理）
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  @Nullable ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  @Nullable WebDataBinderFactory binderFactory) throws Exception {
        CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
        String userName = webRequest.getHeader("Authorization-UserName");
        if(userName == null){
            throw new IllegalArgumentException("userName不能为空");
        }
        return userName;
    }
}
