package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.LoginCheckInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // --- 의존성 주입 (DI) ---

    // application.properties의 file.upload-dir 값을 주입받습니다.
    @Value("${file.upload-dir}")
    private String uploadDir;

    // LoginCheckInterceptor를 주입받습니다.
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;


    // --- WebMvcConfigurer 메서드 오버라이드 ---

    /**
     * CORS(Cross-Origin Resource Sharing) 설정을 추가합니다.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    /**
     * 정적 리소스 핸들러를 추가합니다.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + uploadDir + "/");
    }

    /**
     * 인터셉터를 추가합니다.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**")
                // 참고: addResourceHandlers에 등록된 '/images/**'도 제외해주는 것이 좋습니다.
                .excludePathPatterns("/images/**", "/css/**", "/js/**");
    }
}