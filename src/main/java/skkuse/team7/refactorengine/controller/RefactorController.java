package skkuse.team7.refactorengine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import skkuse.team7.refactorengine.dto.RefactorRequest;
import skkuse.team7.refactorengine.dto.RefactorResponse;

@RestController
@RequiredArgsConstructor
public class RefactorController {

    @GetMapping("/refactoring/{refactorId}")
    public ResponseEntity<RefactorResponse> refactoring(@RequestBody RefactorRequest request) {
        return null;
    }
}
