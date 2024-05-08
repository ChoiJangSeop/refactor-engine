package skkuse.team7.refactorengine.controller;

import ch.qos.logback.core.joran.sanity.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import skkuse.team7.refactorengine.domain.FixedCode;
import skkuse.team7.refactorengine.dto.RefactorRequest;
import skkuse.team7.refactorengine.dto.RefactorResponse;
import skkuse.team7.refactorengine.service.BaseRefactor;
import skkuse.team7.refactorengine.service.RefactorFactory;
import skkuse.team7.refactorengine.service.RefactorType;

@RestController
@RequiredArgsConstructor
public class RefactorController {

    private final RefactorFactory refactorFactory;

    @PostMapping(value = "/refactoring/{refactorId}", consumes = "text/plain")
    public ResponseEntity<RefactorResponse> refactoring(@PathVariable Integer refactorId, @RequestBody String codeText) {

        // TODO dispatch refactorService corresponding refactor id
        // TODO handle NPE using optional
        BaseRefactor refactor = refactorFactory.createRefactor(RefactorType.values()[refactorId]);

        FixedCode fixedCode = refactor.doFix(codeText);
        return new ResponseEntity<>(RefactorResponse.of(fixedCode), HttpStatus.OK);
    }
}
