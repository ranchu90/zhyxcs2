package com.zhyxcs.xxzz.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhyxcs.xxzz.utils.CommonUtils;
import com.zhyxcs.xxzz.utils.CramsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebSecurityConfig extends WebMvcConfigurationSupport {
    @Autowired
    private ImageConfig imageConfig;

    @Bean
    public SecurityInterceptor getSecurityInterceptor(){
        return new SecurityInterceptor();
    }

    public void  addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        /*不需要登陆验证*/
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/login/*");
//        addInterceptor.excludePathPatterns("/workIndex**");
        addInterceptor.excludePathPatterns("/OTA/**");
        addInterceptor.excludePathPatterns("/images/**");
        addInterceptor.excludePathPatterns("/approvalRecord/**");

        /*需要登陆验证*/
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
            HttpSession session = request.getSession();

            System.out.println("拦截器："+session.getId());

            //判断是否已有该用户登录的session
            if(session.getAttribute(CramsConstants.SESSION_LOGIN_USER) != null
                    && session.getAttribute(CramsConstants.SESSION_ORGA_WITH_USER) != null){
                return true;
            }

            //跳转到登录页
            Map result = new HashMap();
            result.put("state", "expired");
            result.put("code", 8848);
            result.put("message", "用户会话已过期");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");

            String resultStr = CommonUtils.objectToJson(result);
            PrintWriter out = null;

            try {
                out = response.getWriter();
                out.append(resultStr);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
            }

            return false;
        }
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        String basePath = imageConfig.getBasePath();
        registry.addResourceHandler("/OTA/**").addResourceLocations("file:" + basePath);
        super.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/OTA/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST")
                .allowCredentials(true).maxAge(3600);
    }
}
