package de.szut.webshop.article.dto;

import lombok.Data;

import java.util.Set;

@Data
public class GetAllArticlesBySupplierDto {
    private long id;

    private Set<GetArticleDto> articles;
}
