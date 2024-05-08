package skkuse.team7.refactorengine.service;

import ch.qos.logback.core.joran.sanity.Pair;
import org.springframework.stereotype.Service;
import skkuse.team7.refactorengine.domain.FixedCode;

@Service
public interface BaseRefactor {

    FixedCode doFix(String codeText);
}
