package de.szut.webshop.article.repository;

import de.szut.webshop.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findArticleByDesignation(String designation);
}
