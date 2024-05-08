package skkuse.team7.refactorengine.dto;

import lombok.AccessLevel;
import lombok.Builder;
import skkuse.team7.refactorengine.domain.FixedCode;
import skkuse.team7.refactorengine.service.RefactorType;

@Builder(access = AccessLevel.PRIVATE)
public record RefactorResponse(
        RefactorType refactorId, String fixedCodeText, String buggyCodePart, String fixedCodePart, Double reduced) {

    /**
     * of method
     */
    public static RefactorResponse
    of(FixedCode fixedCode) {
        return RefactorResponse.builder()
                .refactorId(fixedCode.refactorId())
                .fixedCodeText(fixedCode.fixedCodeText())
                .buggyCodePart(fixedCode.buggyCodePart())
                .fixedCodePart(fixedCode.fixedCodePart())
                .reduced(fixedCode.reduced())
                .build();
    }
}
