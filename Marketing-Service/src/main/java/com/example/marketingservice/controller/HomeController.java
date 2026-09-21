package com.example.marketingservice.controller;

import com.example.marketingservice.dto.VoucherResponse;
import com.example.marketingservice.service.VoucherService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

    private final VoucherService voucherService;

    public HomeController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping("/vouchers")
    public ResponseEntity<List<VoucherResponse>> getHomeVouchers() {
        List<VoucherResponse> vouchers = voucherService.getFlashVouchers();
        return ResponseEntity.ok(vouchers);
    }
}