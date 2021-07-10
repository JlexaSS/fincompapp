package net.fincomp.spring.fincomp.model;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne(cascade = {
            CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_user")
    private User user;

    private Integer count;
    private String nonUser;

    public void IncCount(Integer count){
        this.count += count;
    }

    public String getFilename() {
        return product.getFilename();
    }
    public String getModel() {
        return product.getModel();
    }
    public Integer getPrice() {
        return product.getPrice();
    }

    public String getNonUser() {
        return nonUser;
    }

    public void setNonUser(String nonUser) {
        this.nonUser = nonUser;
    }

    public Cart() {
    }

    public Cart(Product product, User user, Integer count, String nonUser) {
        this.product = product;
        this.user = user;
        this.count = count;
        this.nonUser = nonUser;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
