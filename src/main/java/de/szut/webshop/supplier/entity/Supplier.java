package de.szut.webshop.supplier.entity;

import de.szut.webshop.article.entity.Article;
import de.szut.webshop.contact.entity.Contact;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Table(name = "supplier")
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "supplier",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Set<Article> articles;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Contact contact;

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
