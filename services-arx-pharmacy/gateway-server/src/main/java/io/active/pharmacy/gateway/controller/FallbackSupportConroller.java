package io.active.pharmacy.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("support")
public class FallbackSupportConroller {

    @GetMapping("inventory")
    public Mono<String> inventorySupport() {
        return Mono.just("INVENTORY SUPPORT: Error occured ");
    }

    @GetMapping("store")
    public Mono<String> storeSupport() {
        return Mono.just("STORE SUPPORT: Error occured ");
    }


}
