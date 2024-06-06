package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Data
public class ProductListResponse {
    private List<ProductResponse> data;

    @JsonProperty("total_pages")
    private int totalPages;
}
