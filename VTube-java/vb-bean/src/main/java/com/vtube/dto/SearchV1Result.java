package com.vtube.dto;

import lombok.Data;

@Data
public class SearchV1Result {
    private String title;
    private String id;
    private String imageUrl;
    private String note;
    private String country;
    private String year;
    private String rating;
}
