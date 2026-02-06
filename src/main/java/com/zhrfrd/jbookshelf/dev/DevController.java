package com.zhrfrd.jbookshelf.dev;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev")
@Profile("dev")
public class DevController {
    private final DataSeeder dataSeeder;

    public DevController(DataSeeder dataSeeder) {
        this.dataSeeder = dataSeeder;
    }

    @PostMapping("/seed")
    public String seed(@RequestParam(defaultValue = "100") int count) {
        dataSeeder.seedBooks(count);
        return count + " books inserted";
    }
}
