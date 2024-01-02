package com.bonjung.camong.experience.api;

import com.bonjung.camong.common.Response;
import com.bonjung.camong.experience.api.request.ExperienceCreateRequest;
import com.bonjung.camong.experience.api.request.StepCreateRequest;
import com.bonjung.camong.experience.api.response.ExperienceResponse;
import com.bonjung.camong.experience.api.response.StepResponse;
import com.bonjung.camong.experience.app.service.ExperienceService;
import com.bonjung.camong.experience.app.service.StepService;
import io.swagger.v3.oas.annotations.Operation;
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
public class ExperienceController {

    private final ExperienceService experienceService;
    private final StepService stepService;

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

    @PostMapping(path = "/{experienceId}/pages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "페이지 생성")
    public Response<Void> createPage(@PathVariable Long experienceId,
                                     @RequestPart StepCreateRequest request,
                                     @RequestPart MultipartFile image,
                                     @RequestPart MultipartFile voice) {

        stepService.createStep(experienceId, request, image, voice);

        return Response.success("create page success");
    }

    @GetMapping("/{experienceId}/pages")
    @Operation(summary = "페이지들 불러오기")
    public Response<List<StepResponse>> getPages(@PathVariable Long experienceId) {
        return Response.success(stepService.getSteps(experienceId));
    }
}
