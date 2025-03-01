package com.hid_web.be.domain.exhibit;

import com.hid_web.be.storage.ExhibitEntity;
import com.hid_web.be.storage.ExhibitRepository;
import com.hid_web.be.support.error.ErrorCode;
import com.hid_web.be.support.error.ExhibitException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExhibitReader {
    private final ExhibitRepository exhibitRepository;

    // 임시
    public List<ExhibitEntity> findAll() {
        return exhibitRepository.findAll();
    }

    // 구현
    public List<ExhibitEntity> findByTypeAndYear(ExhibitType type, String year) {
        return exhibitRepository.findByTypeAndYear(type, year);
    }


    public List<ExhibitEntity> findByTypeAndYearAndClub(ExhibitType type, String year, String club) {
        return exhibitRepository.findByTypeAndYearAndClub(type, year, club);
    }

    public ExhibitEntity findByExhibitId(Long exhibitId) {
        ExhibitEntity exhibitEntity = exhibitRepository.findByExhibitId(exhibitId);

        if (exhibitEntity == null) {
            throw new ExhibitException(ErrorCode.EXHIBIT_NOT_FOUND, "해당하는 전시 ID가 존재하지 않습니다.");
        }

        return exhibitRepository.findByExhibitId(exhibitId);
    }
}