package org.geekhub.niko.test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/ping")
    public String ping() {
        return "Pong!";
    }
}
