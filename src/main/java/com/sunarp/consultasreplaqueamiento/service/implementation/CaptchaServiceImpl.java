package com.sunarp.consultasreplaqueamiento.service.implementation;

import com.sunarp.consultasreplaqueamiento.service.dtos.CloudflareCaptchaResponse;
import com.sunarp.consultasreplaqueamiento.service.interfaces.CaptchaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

    @Value("${secret.key}")
    private String secretKey;

    private final WebClient webClient;

    @Override
    public Boolean verifyCaptcha(String captcha) {
        try {
            CloudflareCaptchaResponse response = webClient.post()
                    .uri("https://challenges.cloudflare.com/turnstile/v0/siteverify")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData("secret", secretKey)
                            .with("response", captcha))
                    .retrieve().bodyToMono(CloudflareCaptchaResponse.class).block();

            return response != null && response.success();
        }catch (Exception e) {
            log.error("Error verifying captcha", e);
            return false;
        }
    }
}
