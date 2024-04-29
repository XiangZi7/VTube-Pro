package com.vtube.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component //Component  组成部分
@ConfigurationProperties(prefix = "vtube.sa-token") // ConfigurationProperties配置属性  prefix 字首 Properties属性
public class SatokenProperties {
    /**
     * 路由放行
     * excludes  排除
     */
    private List<String> excludes;

    public List<String> getExcludes() {
        return excludes;
    }

    public void setExcludes(List<String> excludes) {
        this.excludes = excludes;
    }
}