package com.example.demo.config; // 패키지 경로는 프로젝트에 맞게 조정하세요.

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // application.yml 또는 application.properties에 설정한 파일 저장 경로를 가져옵니다.
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /images/** 패턴의 URL 요청이 들어오면
        registry.addResourceHandler("/images/**")
                // file:///C:/dev/uploads/ 와 같은 실제 물리적 경로에서 파일을 찾아 리소스를 제공합니다.
                // "file:///" 접두사를 꼭 붙여야 합니다.
                .addResourceLocations("file:///" + uploadDir);
    }
}