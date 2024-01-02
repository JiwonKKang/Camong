package com.bonjung.camong.admin.api;

import com.bonjung.camong.admin.app.AdminService;
import com.bonjung.camong.common.Response;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    @Operation(summary = "어드민 로그인")
    public Response<Void> login(@RequestBody String password) {
        return Response.success(adminService.login(password));
    }

}
