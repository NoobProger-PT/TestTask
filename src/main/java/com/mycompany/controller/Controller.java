package com.mycompany.controller;

import com.mycompany.service.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/count")
@Slf4j
@Validated
public class Controller {
    private final ServiceImpl service;

    @GetMapping()
    public String count(@RequestParam(defaultValue = "0")
                            @Size(min = 1, max = 10, message = "Длина должна составлять от 1 до 10")
                            String str) {
        log.info("Получение строки от пользователя");
        return service.count(str);
    }
}
