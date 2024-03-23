package org.example.examplemanager.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.examplemanager.admin.jwt.CustomJwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
        origins = {"http://localhost:4200", "http://frontend-service:4200"},
        allowedHeaders = "*",
        methods = {RequestMethod.GET}
)
public class HelloController {
    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ROLE_example')")
    public ResponseEntity<String> hello() throws JsonProcessingException {
        CustomJwt authentication = (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
        Message message = new Message();
        message.setMessage("Hello User " + authentication.getFirstname() + " " + authentication.getLastname() + ", welcome to the application!");
        ObjectMapper mapper = new ObjectMapper();
        return ResponseEntity.ok(mapper.writeValueAsString(message));
    }

    public class Message {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
