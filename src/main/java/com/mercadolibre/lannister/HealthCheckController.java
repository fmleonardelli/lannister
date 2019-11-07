package com.mercadolibre.lannister;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @RequestMapping("health")
    public String HealthCheck() {
        return "Health is Fine";
    }
}
