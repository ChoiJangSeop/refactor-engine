package skkuse.team7.refactorengine.domain;

import lombok.AccessLevel;
import lombok.Builder;
import skkuse.team7.refactorengine.dto.RefactorResponse;
import skkuse.team7.refactorengine.service.RefactorType;

@Builder(access = AccessLevel.PRIVATE)
public record FixedCode(
        RefactorType refactorId, String fixedCodeText, String buggyCodePart, String fixedCodePart, Double reduced) {

    /**
     * of method
     */
    public static FixedCode
    of(RefactorType refactorId, String fixedCodeText, String buggyCodePart, String fixedCodePart, Double reduced) {
        return FixedCode.builder()
                .refactorId(refactorId)
                .fixedCodeText(fixedCodeText)
                .buggyCodePart(buggyCodePart)
                .fixedCodePart(fixedCodePart)
                .reduced(reduced)
                .build();
    }
}
