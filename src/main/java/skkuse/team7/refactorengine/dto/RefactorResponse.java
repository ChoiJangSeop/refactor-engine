package skkuse.team7.refactorengine.dto;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record RefactorResponse(Long refactorId, String originCodeText, String refacteredCodeText) {

}
