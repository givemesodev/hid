package com.hid_web.be.storage.exhibit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitArtistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;

    private String artistUUID;
    private String profileImgObjectKey;
    private String nameKo;
    private String nameEn;
    private String role;
    private String email;
    private String instagramUrl;
    private String behanceUrl;
    private String linkedinUrl;

    public ExhibitArtistEntity(Long id, String artistUUID, String uploadedUrl) {
        this.artistId = id;
        this.artistUUID = artistUUID;;
        this.profileImgObjectKey = uploadedUrl;
    }
}

