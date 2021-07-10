package net.fincomp.spring.fincomp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne(cascade = {
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_producer")
    private Producer producer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private List<Cart> cart;

    private String model;
    private String description;
    private Integer price;
    private String filename;

    public Product(){}

    public Product(String model, String description, Integer price, Category category, Producer producer) {
        this.model = model;
        this.description = description;
        this.price = price;
        this.category = category;
        this.producer = producer;
    }

    public Long getIdCategory() {
        return category.getId();
    }

    public Long getIdProducer() {
        return producer.getId();
    }

    public String getCategory() {
        return category.getName();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProducer() {
        return producer.getName();
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
