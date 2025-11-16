package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.Models.BlackListRequest;
import ru.mtuci.Models.BookingRequest;
import ru.mtuci.Models.EndFlightRequest;
import ru.mtuci.Services.LogicService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Tag(name="Бизнес-логика")
@RequestMapping("/logic")
@RequiredArgsConstructor
public class LogicController {
    private final LogicService logicService;

    @PostMapping("/backlist")
    @Operation(summary = "Занесение пассажира в черный список и удаление со всех рейсов")
    public ResponseEntity<?> BlackList(@RequestBody BlackListRequest request){
        return logicService.toBlackList(request);
    }

    @PostMapping("/end")
    @Operation(summary = "Завершение полета по его номеру") // Завершение полета, удаление всех бронироний
    public ResponseEntity<Map<String, String>> EndFlight(@RequestBody EndFlightRequest request){
        return logicService.endFlightByNumber(request);
    }

    @PostMapping("/register")
    @Operation(summary = "Регистрация на рейс") // с проверкой свободных мест в самолете и не дублирующее место и н
    // и не повторную регистрацию
    public ResponseEntity<?> RegisterToFlight(@RequestBody BookingRequest request){
        return logicService.register(request);
    }

    @GetMapping("/now-flying")
    @Operation(summary = "Получение количества пассажиров, которые сейчас летят")
    public ResponseEntity<Map<String, Integer>> GetNowFlightPassengers(@RequestParam LocalDateTime time){ // получени количество пассажиров, которые сейчас в воздухе
        return logicService.getNowFlying(time);
    }

    @GetMapping("/delay-notification")
    @Operation(summary = "Получение почт всех пользователей, чей вылет задерживается")
    public ResponseEntity<List<String>> GetEmailByFlightId(@RequestParam UUID flightId){
        return logicService.getEmailByFlightId(flightId);
    }

}
