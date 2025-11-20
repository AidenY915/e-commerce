package com.ecommerce.core.seller.presentation.dto;

import com.ecommerce.core.seller.application.dto.SellerCommand;

import java.util.UUID;

public record SellerRequest(
        UUID id,
        String companyName,
        String representativeName,
        String email,
        String phone,
        String businessNumber,
        String address,
        String status
) {

    public SellerCommand toCommand() {
        return new SellerCommand(id, companyName, representativeName, email, phone, businessNumber, address, status);
    }
}
