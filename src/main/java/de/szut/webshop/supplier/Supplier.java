package de.szut.webshop.supplier;

import de.szut.webshop.article.Article;
import de.szut.webshop.contact.Contact;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "supplier",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Set<Article> articles;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Contact contact;
}
