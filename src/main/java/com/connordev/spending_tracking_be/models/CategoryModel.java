package com.connordev.spending_tracking_be.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryModel {
    private String id;
    private String name;
}
