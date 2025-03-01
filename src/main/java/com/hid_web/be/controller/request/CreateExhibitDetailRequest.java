package com.hid_web.be.controller.request;

import com.hid_web.be.domain.exhibit.ExhibitType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExhibitDetailRequest {
    @NotNull(message = "전시 타입은 필수입니다.")
    private ExhibitType type;

    @NotNull(message = "전시 연도는 필수입니다.")
    @Pattern(regexp = "^\\d{4}$", message = "연도는 4자리 숫자여야 합니다.")
    private String year;

    private String major;
    private String club;

    @NotBlank(message = "국문 제목은 필수입니다")
    @Size(max = 33, message = "한글 제목은 33자를 초과할 수 없습니다")
    private String titleKo;

    @NotBlank(message = "영문 제목은 필수입니다")
    @Size(max = 50, message = "영문 제목은 50자를 초과할 수 없습니다")
    private String titleEn;

    @NotBlank(message = "국문 부제는 필수입니다")
    @Size(max = 300, message = "한글 부제목은 300자를 초과할 수 없습니다")
    private String subTitleKo;

    @NotBlank(message = "영문 부제는 필수입니다")
    @Size(max = 700, message = "영문 부제목은 700자를 초과할 수 없습니다")
    private String subTitleEn;

    @NotBlank(message = "국문 본문은 필수입니다")
    @Size(max = 1000, message = "한글 본문은 1000자를 초과할 수 없습니다")
    private String textKo;

    @NotBlank(message = "영문 본문은 필수입니다")
    @Size(max = 1000, message = "영문 본문은 1000자를 초과할 수 없습니다")
    private String textEn;

    /**
     * 영상 선택
     */
    // @Pattern(regexp = "^(https?://.*)?$", message = "올바른 URL 형식이 아닙니다")
    private String videoUrl;

    @AssertTrue(message = "졸업 전시의 경우 전공명이 필수이며, 소모임 이름은 입력할 수 없습니다")
    private boolean isValidGraduationExhibit() { // 리플렉션을 이용하므로 private으로 지정해도 사용 가능하다.
        if (ExhibitType.GRADUATION == type) {
            return major != null && !major.trim().isEmpty()
                    && (club == null || club.trim().isEmpty());
        }
        return true;
    }

    @AssertTrue(message = "소모임 전시의 경우 소모임 이름이 필수이며, 전공명은 입력할 수 없습니다")
    private boolean isValidClubExhibit() {
        if (ExhibitType.CLUB == type) {
            return club != null && !club.trim().isEmpty()
                    && (major == null || major.trim().isEmpty());
        }
        return true;
    }
}
