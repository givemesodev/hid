package com.hid_web.be;

import com.hid_web.be.storage.exhibit.ExhibitEntity;
import com.hid_web.be.storage.exhibit.ExhibitArtistEntity;
import com.hid_web.be.storage.content.ContentMainVideoEntity;
import com.hid_web.be.storage.user.UserEntity;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit() {
            Long year = 2024L;
            List<ContentMainVideoEntity> existingVideos = em.createQuery(
                            "SELECT v FROM ContentMainVideoEntity v WHERE v.year = :year", ContentMainVideoEntity.class)
                    .setParameter("year", year)
                    .getResultList();

            if (existingVideos.isEmpty()) {
                ContentMainVideoEntity contentMainVideoEntity = new ContentMainVideoEntity();
                contentMainVideoEntity.setTitle("2024 졸업 전시 영상");
                contentMainVideoEntity.setS3ObjectKey("graduation-videos/2024/2024_graduation_video.mp4");
                contentMainVideoEntity.setYear(year);

                em.persist(contentMainVideoEntity);
            }
        }
    }
}