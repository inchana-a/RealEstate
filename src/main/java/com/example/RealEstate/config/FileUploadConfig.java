package com.example.RealEstate.config;

import com.example.RealEstate.Service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class FileUploadConfig implements WebMvcConfigurer {

    private final FileStorageService fileStorageService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = "file:" + fileStorageService.getUploadRoot().toString() + "/";
        System.out.println("Serving uploads from: " + location);
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(location);
    }
}
