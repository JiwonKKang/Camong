package com.bonjung.camong.admin.app;

import com.bonjung.camong.common.exception.CustomException;
import com.bonjung.camong.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    @Value("${admin.password}")
    private String adminPassword;

    public String login(String password) {

        if (!Objects.equals(password, adminPassword)) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        return "success";
    }

}
