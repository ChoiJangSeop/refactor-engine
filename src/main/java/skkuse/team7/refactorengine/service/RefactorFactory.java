package skkuse.team7.refactorengine.service;

import static skkuse.team7.refactorengine.service.RefactorType.*;

import org.springframework.stereotype.Component;

@Component
public class RefactorFactory {
    public BaseRefactor createRefactor(RefactorType type) {

        if (type == ITER_GET_SIZE) {
            return new IterateGetSizeRefactor();
        }
        return null;
    }
}
