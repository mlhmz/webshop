package de.szut.webshop.article.service;

import de.szut.webshop.article.entity.Article;
import de.szut.webshop.article.repository.ArticleRepository;
import de.szut.webshop.exception.ResourceNotFoundException;
import de.szut.webshop.supplier.entity.Supplier;
import de.szut.webshop.supplier.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article create(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> readAll() {
        return articleRepository.findAll();
    }

    public Article readById(long id) {
        Optional<Article> article = articleRepository.findById(id);

        if (article.isEmpty()) {
            throw new ResourceNotFoundException(Article.class, id);
        }

        return article.get();
    }

    public Article readByDesignation(String designation) {
        Optional<Article> article = articleRepository.findArticleByDesignation(designation);

        if (article.isEmpty()) {
            throw new ResourceNotFoundException(Article.class, "designation", designation);
        }

        return article.get();
    }

    public Article update(Article article) {
        if (!articleRepository.existsById(article.getId())) {
            throw new ResourceNotFoundException(Article.class, article.getId());
        }
        return articleRepository.save(article);
    }

    public void deleteById(long id) {
        articleRepository.deleteById(id);
    }
}
