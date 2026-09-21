package com.example.marketingservice.dto;

public class VoucherResponse {
    private String code;
    private String title;
    private Long discountAmount;

    public VoucherResponse() {
    }

    public VoucherResponse(String code, String title, Long discountAmount) {
        this.code = code;
        this.title = title;
        this.discountAmount = discountAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Long discountAmount) {
        this.discountAmount = discountAmount;
    }
}