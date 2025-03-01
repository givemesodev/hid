package com.hid_web.be.domain.exhibit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExhibitDetail {
    private ExhibitType type;
    private String year;
    private String major;
    private String club;
    private String titleKo;
    private String titleEn;
    private String subTitleKo;
    private String subTitleEn;
    private String textKo;
    private String textEn;
    private String videoUrl;

    public ExhibitDetail(String year, String titleKo, String titleEn, String subTitleKo, String subTitleEn, String textKo, String textEn, String videoUrl) {
        this.year = year;
        this.titleKo = titleKo;
        this.titleEn = titleEn;
        this.subTitleKo = subTitleKo;
        this.subTitleEn = subTitleEn;
        this.textKo = textKo;
        this.textEn = textEn;
        this.videoUrl = videoUrl;
    }
}
