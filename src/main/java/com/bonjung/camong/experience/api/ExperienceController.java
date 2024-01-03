package com.bonjung.camong.experience.api;

import com.bonjung.camong.common.Response;
import com.bonjung.camong.experience.api.request.ExperienceCreateRequest;
import com.bonjung.camong.experience.api.request.StepSequenceUpdateRequest;
import com.bonjung.camong.experience.api.request.StepCreateRequest;
import com.bonjung.camong.experience.api.request.StepUpdateRequest;
import com.bonjung.camong.experience.api.response.ExperienceResponse;
import com.bonjung.camong.experience.api.response.StepResponse;
import com.bonjung.camong.experience.app.service.ExperienceService;
import com.bonjung.camong.experience.app.service.StepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/experiences")
@Tag(name = "체험 관련 컨트롤러")
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "체험 생성")
    public Response<Void> createExperience(@RequestPart ExperienceCreateRequest request,
                                           @RequestPart MultipartFile image) {
        experienceService.createExperience(request, image);
        return Response.success("create experience success");
    }

    @GetMapping
    @Operation(summary = "체험들 불러오기")
    public Response<Page<ExperienceResponse>> getExperiences(@PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return Response.success(experienceService.getExperiences(pageable));
    }

    @GetMapping("/{experienceId}")
    @Operation(summary = "체험 상세 정보 불러오기")
    public Response<ExperienceResponse> getExperienceDetail(@PathVariable Long experienceId) {
        ExperienceResponse res = ExperienceResponse.from(experienceService.getExperienceById(experienceId));
        return Response.success(res);
    }

    @PatchMapping(path = "/{experienceId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "체험 수정")
    public Response<Void> updateExperience(@PathVariable Long experienceId,
                                           @RequestPart ExperienceCreateRequest request,
                                           @RequestPart(required = false) MultipartFile image) {
        experienceService.updateExperience(experienceId, request, image);
        return Response.success("update experience success");
    }

    @DeleteMapping("/{experienceId}")
    @Operation(summary = "체험 삭제")
    public Response<Void> deleteExperience(@PathVariable Long experienceId) {
        experienceService.deleteExperience(experienceId);
        return Response.success("experience delete success");
    }

}
