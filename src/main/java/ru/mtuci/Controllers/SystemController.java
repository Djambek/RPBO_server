package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mtuci.Services.SystemService;

@RestController
@Tag(name = "Системные функции")
@RequestMapping("/system")
@RequiredArgsConstructor
public class SystemController {
    private final SystemService systemService;

    @PostMapping("/clean")
    @Operation(summary = "Производит очистку БД")
    public ResponseEntity<Void> ClearDB(){
        return systemService.clearDB();
    }

    @PostMapping("/generate")
    @Operation(summary = "Создает тестовые данные")
    public ResponseEntity<Void> GenerateTestData(){
        return systemService.generateTestData();
    }
}
