package com.vtube.dto;

import lombok.Data;

import java.util.List;
@Data
public class ProductV1Detail {
    private String title;
    private String originalTitle;
    private String year;
    private String rating;
    private String imageUrl;
    private List<String> directors;
    private List<String> stars;
    private List<String> genres;
    private String country;
    private String alias;
    private String synopsis;
}
