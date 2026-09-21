package com.example.marketingservice.service;

import com.example.marketingservice.dto.VoucherResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class VoucherService {

    private static final Logger log = LoggerFactory.getLogger(VoucherService.class);
    private final RestTemplate restTemplate = new RestTemplate();

    // Giả lập gọi URL không tồn tại để ép sinh ra lỗi
    private static final String MARKETING_SERVICE_URL = "http://localhost:9999/api/v1/marketing/flash-vouchers";

    @CircuitBreaker(name = "voucherCircuitBreaker", fallbackMethod = "getDefaultVouchers")
    public List<VoucherResponse> getFlashVouchers() {
        log.info("Dang goi sang Marketing-Service: {}", MARKETING_SERVICE_URL);

        ResponseEntity<List<VoucherResponse>> response = restTemplate.exchange(
                MARKETING_SERVICE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<VoucherResponse>>() {}
        );

        return response.getBody();
    }

    /**
     * Hàm Fallback:
     * - Cùng kiểu trả về List<VoucherResponse>
     * - Tham số cuối cùng bắt buộc là Throwable
     */
    public List<VoucherResponse> getDefaultVouchers(Throwable throwable) {
        log.warn("Marketing-Service gap su co: {}. Kich hoat Fallback tra ve voucher mac dinh.", throwable.getMessage());

        VoucherResponse defaultVoucher = new VoucherResponse(
                "DEFAULT_FREESHIP",
                "Mã Freeship 15K",
                15000L
        );

        return Collections.singletonList(defaultVoucher);
    }
}