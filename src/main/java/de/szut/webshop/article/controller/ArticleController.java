package de.szut.webshop.article.controller;

import de.szut.webshop.article.dto.AddArticleDto;
import de.szut.webshop.article.dto.GetArticleDto;
import de.szut.webshop.article.entity.Article;
import de.szut.webshop.article.service.ArticleService;
import de.szut.webshop.service.MappingService;
import de.szut.webshop.supplier.entity.Supplier;
import de.szut.webshop.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {
    private final ArticleService articleService;
    private final SupplierService supplierService;
    private final MappingService mapper;

    @Autowired
    public ArticleController(ArticleService articleService,
                             SupplierService supplierService,
                             MappingService mapper) {
        this.articleService = articleService;
        this.supplierService = supplierService;
        this.mapper = mapper;
    }

    @PostMapping("/{sid}")
    public ResponseEntity<GetArticleDto> createArticle(@PathVariable long sid, @RequestBody AddArticleDto dto) {
        Article article = articleService.create(mapper.mapAddArticleDtoToArticle(dto));
        Supplier supplier = supplierService.readById(sid);
        article.setSupplier(supplier);
        return new ResponseEntity<>(mapper.mapArticleToGetArticleDto(article), CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GetArticleDto>> getAllArticles() {
        return new ResponseEntity<>(articleService.readAll().stream()
                        .map(mapper::mapArticleToGetArticleDto)
                        .collect(Collectors.toList()), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetArticleDto> getArticleById(@PathVariable long id) {
        return new ResponseEntity<>(
                mapper.mapArticleToGetArticleDto(articleService.readById(id)), OK
        );
    }

    @GetMapping("/{designation}")
    public ResponseEntity<GetArticleDto> getArticleByDesignation(@PathVariable String designation) {
        return new ResponseEntity<>(
                mapper.mapArticleToGetArticleDto(articleService.readByDesignation(designation)
                ), OK
        );
    }

    @PutMapping
    public ResponseEntity<GetArticleDto> updateArticle(@RequestBody Article article) {
        return new ResponseEntity<>(
                mapper.mapArticleToGetArticleDto(articleService.update(article)), OK
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteArticle(@PathVariable long id) {
        articleService.deleteById(id);
    }
}
