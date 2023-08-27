package selfstudy.security1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test-secured-endpoint")
public class TestController {

    @GetMapping
    public ResponseEntity<String> testSecuredEndpoint() {
        return ResponseEntity.ok("You had successfully authenticated. ");
    }
}
