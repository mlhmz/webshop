package de.szut.webshop.article.dto;

import lombok.Data;

@Data
public class GetArticleDto {
    private Long id;
    private String designation;
    private double price;
}
