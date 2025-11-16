package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Системные функции")
@RequestMapping("/system")
public class SystemController {

    @PostMapping("/clean")
    @Operation(summary = "Производит очистку БД")
    public ResponseEntity<Void> ClearDB(){
        return null;
    }

    @PostMapping("/generate")
    @Operation(summary = "Создает тестовые данные")
    public ResponseEntity<Void> GenerateTestData(){
        return null;
    }
}
