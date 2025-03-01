package com.hid_web.be.controller.response;

import com.hid_web.be.domain.s3.S3UrlConverter;
import com.hid_web.be.storage.ExhibitEntity;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ExhibitPreviewResponse {
    private Long exhibitId;
    private String club;
    private String mainImgUrl;
    private String titleKo;
    private String titleEn;
    private String subTitleKo;
    private String subTitleEn;

    public static ExhibitPreviewResponse of(ExhibitEntity exhibitEntity) {
        return ExhibitPreviewResponse.builder()
                .exhibitId(exhibitEntity.getExhibitId())
                .club(exhibitEntity.getClub())
                .mainImgUrl(S3UrlConverter.convertCloudfrontUrlFromObjectKey(exhibitEntity.getMainImgObjectKey()))
                .titleKo(exhibitEntity.getTitleKo())
                .titleEn(exhibitEntity.getTitleEn())
                .subTitleKo(exhibitEntity.getSubTitleKo())
                .subTitleEn(exhibitEntity.getSubTitleEn())
                .build();
    }
}

