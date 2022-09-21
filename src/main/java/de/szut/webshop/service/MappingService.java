package de.szut.webshop.service;

import de.szut.webshop.article.dto.AddArticleDto;
import de.szut.webshop.article.dto.GetAllArticlesBySupplierDto;
import de.szut.webshop.article.dto.GetArticleDto;
import de.szut.webshop.article.entity.Article;
import de.szut.webshop.contact.entity.Contact;
import de.szut.webshop.supplier.dto.AddSupplierDto;
import de.szut.webshop.supplier.dto.GetSupplierDto;
import de.szut.webshop.supplier.entity.Supplier;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service to map Data-Transfer-Objects
 */
@Service
public class MappingService {
    /**
     * Maps {@link AddSupplierDto} to {@link Supplier}
     * @param dto The dto source
     * @return actual supplier object that can be stored into database
     */
    public Supplier mapAddSupplierDtoToSupplier(AddSupplierDto dto) {
        Supplier supplier = new Supplier();

        Contact contact = new Contact();
        contact.setStreet(dto.getStreet());
        contact.setPostcode(dto.getPostcode());
        contact.setCity(dto.getCity());
        contact.setPhone(dto.getPhone());

        supplier.setName(dto.getName());
        supplier.setContact(contact);
        contact.setSupplier(supplier);

        return supplier;
    }

    /**
     * Maps {@link Supplier} into viewable DTO
     * @param supplier the supplier to map
     * @return the viewable dto for a get request
     */
    public GetSupplierDto mapSupplierToGetSupplierDto(Supplier supplier) {
        GetSupplierDto dto = new GetSupplierDto();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());

        Contact contact = supplier.getContact();
        dto.setStreet(contact.getStreet());
        dto.setPostcode(contact.getPostcode());
        dto.setCity(contact.getCity());
        dto.setPhone(contact.getPhone());

        return dto;
    }

    public Article mapAddArticleDtoToArticle(AddArticleDto dto) {
        return Article.builder()
                .designation(dto.getDesignation())
                .price(dto.getPrice())
                .build();
    }

    /**
     * Maps {@link Article} into viewable DTO
     * @param article the article to map
     * @return the viewable dto for a get request
     */
    public GetArticleDto mapArticleToGetArticleDto(Article article) {
        GetArticleDto dto = new GetArticleDto();

        dto.setId(article.getId());
        dto.setDesignation(article.getDesignation());
        dto.setPrice(article.getPrice());

        return dto;
    }

    /**
     * Maps all Articles of a Supplier to {@link GetArticleDto} and
     * sets the id of the supplier
     *
     * @param supplier The actual supplier to map
     * @return The dto for the get request
     */
    public GetAllArticlesBySupplierDto mapAllArticlesToGetAllArticlesBySupplierDto(Supplier supplier) {
        GetAllArticlesBySupplierDto dto = new GetAllArticlesBySupplierDto();

        dto.setId(supplier.getId());

        // Maps all Articles to GetArticleDto
        Set<GetArticleDto> articleDtoSet = supplier.getArticles().stream()
                .map(this::mapArticleToGetArticleDto)
                .collect(Collectors.toSet());

        dto.setArticles(articleDtoSet);

        return dto;
    }
}
