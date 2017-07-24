package com.mall.common.service;

import com.mall.common.spring.exetend.PropertyConfig;
import org.springframework.stereotype.Service;

@Service
public class PropertieService {

    @PropertyConfig
    public String REPOSITORY_PATH;
    
    @PropertyConfig
    public String IMAGE_BASE_URL;

}
