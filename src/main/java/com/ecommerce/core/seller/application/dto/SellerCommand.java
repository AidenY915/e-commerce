package com.ecommerce.core.seller.application.dto;

import java.util.UUID;

public record SellerCommand (
        UUID id,
        String companyName,
        String representativeName,
        String email,
        String phone,
        String businessNumber,
        String address,
        String status
) {
}


