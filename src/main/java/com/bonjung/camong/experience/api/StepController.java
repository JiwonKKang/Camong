package com.bonjung.camong.experience.api;

import com.bonjung.camong.common.Response;
import com.bonjung.camong.experience.api.request.StepCreateRequest;
import com.bonjung.camong.experience.api.request.StepSequenceUpdateRequest;
import com.bonjung.camong.experience.api.request.StepUpdateRequest;
import com.bonjung.camong.experience.api.response.StepResponse;
import com.bonjung.camong.experience.app.service.StepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/experiences")
@Tag(name = "스텝(페이지) 관련 컨트롤러")
public class StepController {

    private final StepService stepService;

    @PostMapping(path = "/{experienceId}/pages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "스텝 생성")
    public Response<Void> createStep(@PathVariable Long experienceId,
                                     @RequestPart StepCreateRequest request,
                                     @RequestPart MultipartFile image,
                                     @RequestPart MultipartFile voice) {
        stepService.createStep(experienceId, request, image, voice);

        return Response.success("create page success");
    }

    @GetMapping("/pages/{stepId}")
    @Operation(summary = "스텝 상세 정보 가져오기")
    public Response<StepResponse> getStepDetail(@PathVariable Long stepId) {
        StepResponse res = StepResponse.from(stepService.getStep(stepId));
        return Response.success(res);
    }

    @PatchMapping(path = "/pages/{stepId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "스텝 수정")
    public Response<Void> updateStep(@PathVariable Long stepId,
                                     @RequestPart StepUpdateRequest request,
                                     @RequestPart(required = false) MultipartFile image,
                                     @RequestPart(required = false) MultipartFile voice ) {
        stepService.updateStep(stepId, request, image, voice);
        return Response.success("update step success");
    }


    @PatchMapping("/pages")
    @Operation(summary = "스텝 순서 한번에 수정")
    public Response<Void> updateStepsSequence(@RequestBody List<StepSequenceUpdateRequest> requests) {
        stepService.updateSteps(requests);
        return Response.success("sequence update success");
    }


    @GetMapping("/{experienceId}/pages")
    @Operation(summary = "스텝들 불러오기")
    public Response<List<StepResponse>> getSteps(@PathVariable Long experienceId) {

        return Response.success(stepService.getSteps(experienceId));
    }

    @DeleteMapping("/pages/{stepId}")
    @Operation(summary = "스텝 삭제")
    public Response<Void> deleteStep(@PathVariable Long stepId) {
        stepService.deleteStep(stepId);
        return Response.success("step delete success");
    }

}
