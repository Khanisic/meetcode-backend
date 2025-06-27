package com.meetcode.backend_meetcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBannerResponse {
    private String username;
    private Boolean success;
    private String banner;
} 