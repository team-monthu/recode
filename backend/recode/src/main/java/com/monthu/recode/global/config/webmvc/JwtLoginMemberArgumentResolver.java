package com.monthu.recode.global.config.webmvc;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class JwtLoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isAuthMemberAnnotation =
                parameter.getParameterAnnotation(JwtLoginMember.class) != null;
        boolean isAuthMemberClass = AuthMember.class.equals(parameter.getParameterType());
        return isAuthMemberAnnotation && isAuthMemberClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
        Long memberId = Long.valueOf(authentication.getPrincipal()
                                                   .toString());
        return new AuthMember(memberId);
    }
}
