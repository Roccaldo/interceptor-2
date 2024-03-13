package co.develhope.interceptor2.controllers;

import co.develhope.interceptor2.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/months")
public class MonthController {

    @GetMapping("")
    public ResponseEntity<Month> month(HttpServletRequest request) {
        Month month = (Month) request.getAttribute("month");
        if (month != null) {
            return ResponseEntity.ok(month);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}