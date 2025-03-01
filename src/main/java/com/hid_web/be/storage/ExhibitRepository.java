package com.hid_web.be.storage;

import com.hid_web.be.domain.exhibit.ExhibitType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExhibitRepository extends JpaRepository<ExhibitEntity, Long> {
    List<ExhibitEntity> findAll();
    List<ExhibitEntity> findByTypeAndYear(ExhibitType exhibitType, String year);
    List<ExhibitEntity> findByTypeAndYearAndClub(ExhibitType exhibitType, String year, String club);
    ExhibitEntity findByExhibitId(Long exhibitId);
}