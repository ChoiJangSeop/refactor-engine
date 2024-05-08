package skkuse.team7.refactorengine.dto;

import lombok.AccessLevel;
import lombok.Builder;
import skkuse.team7.refactorengine.service.RefactorType;

@Builder(access =  AccessLevel.PRIVATE)
public record RefactorRequest(String codeText) {
    /**
     * of method
     */
    public static RefactorRequest of(String codeText) {
        return RefactorRequest.builder()
                .codeText(codeText)
                .build();
    }
}
