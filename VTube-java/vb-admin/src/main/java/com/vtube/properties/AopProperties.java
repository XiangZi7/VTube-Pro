package com.vtube.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AopProperties {
    /**
     * 1秒内限制api访问的次数
     */
    @Value("${vtube.aop.visits}")
    private Integer visits;

    public Integer getVisits() {
        return visits;
    }

    public AopProperties setVisits(Integer visits) {
        this.visits = visits;
        return this;
    }
}