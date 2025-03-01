package com.hid_web.be.controller;

import com.hid_web.be.controller.request.CreateExhibitRequest;
import com.hid_web.be.controller.request.UpdateExhibitRequest;
import com.hid_web.be.controller.response.ExhibitPreviewResponse;
import com.hid_web.be.controller.response.ExhibitResponse;
import com.hid_web.be.domain.exhibit.ExhibitType;
import com.hid_web.be.storage.ExhibitEntity;
import com.hid_web.be.domain.exhibit.ExhibitService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exhibits")
public class ExhibitController {
    private final ExhibitService exhibitService;
    /*
    @GetMapping("/previews") // findAllExhibitPreview -> findExhibitPreviewsByYearAndMajor, findExhibitPreviewsByYearAndClub 임시
    public ResponseEntity<Result<List<ExhibitPreviewResponse>>> findAllExhibitPreview() {
        List<ExhibitEntity> exhibitEntityList = exhibitService.findAllExhibit();
        List<ExhibitPreviewResponse> exhibitPreviewResponseList = exhibitEntityList.stream()
                .map(e -> ExhibitPreviewResponse.of(e))
                .collect(Collectors.toList());

        // Result 객체로 리스트를 감싸서 반환
        return ResponseEntity.ok().body(new Result<>(exhibitPreviewResponseList));
    }
    */

    @GetMapping("/previews/clubs") // 소모임 전시에선 연도와 소모임 이름으로 필터링 구현
    public ResponseEntity<Result<List<ExhibitPreviewResponse>>> findExhibitPreviewsByYearAndClub(
            @RequestParam(defaultValue = "2024") String year,
            @RequestParam(defaultValue = "ALL") String club) {
        List<ExhibitEntity> exhibitEntities = exhibitService.findExhibitsByYearAndClub(ExhibitType.CLUB, year, club);

        List<ExhibitPreviewResponse> exhibitPreviewResponses = exhibitEntities.stream()
                .map(ExhibitPreviewResponse::of)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(new Result<>(exhibitPreviewResponses));
    }

    @GetMapping("/{exhibitId}")
    public ResponseEntity<ExhibitResponse> findExhibitByExhibitId(@PathVariable Long exhibitId) {

        ExhibitEntity exhibitEntity = exhibitService.findExhibitByExhibitId(exhibitId);

        return ResponseEntity.ok().body(ExhibitResponse.of(exhibitEntity));
    }

    /*
    @Valid 어노테이션이 있으면 Spring이 자동으로 Validation을 수행하고, 실패 시 MethodArgumentNotValidException을 발생시키며, 이를 @ExceptionHandler가 처리한다.
     */
    @PostMapping
    public ResponseEntity<ExhibitResponse> createExhibit(@Valid @ModelAttribute CreateExhibitRequest createExhibitRequest) {
        try {
            ExhibitEntity exhibitEntity = exhibitService.createExhibit(
                    createExhibitRequest.getMainImgFile(),
                    createExhibitRequest.toDetailImgs(),
                    createExhibitRequest.toDetails(),
                    createExhibitRequest.toArtists()
            );

            return ResponseEntity.ok(ExhibitResponse.of(exhibitEntity));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{exhibitId}")
    public ResponseEntity<Void> deleteExhibit(@PathVariable Long exhibitId) {
        exhibitService.deleteExhibit(exhibitId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{exhibitId}")
    public ResponseEntity<ExhibitResponse> updateExhibit(@Valid @PathVariable Long exhibitId, @ModelAttribute UpdateExhibitRequest updateExhibitRequest) {
        try {
            ExhibitEntity updatedExhibit = exhibitService.updateExhibit(
                    exhibitId,
                    updateExhibitRequest.getMainImgFile(),
                    updateExhibitRequest.toDetailImgs(),
                    updateExhibitRequest.toDetails(),
                    updateExhibitRequest.toArtists());

            return ResponseEntity.ok(ExhibitResponse.of(updatedExhibit));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Data
    @AllArgsConstructor
    public static class Result<T> {
        private T data;
    }
}