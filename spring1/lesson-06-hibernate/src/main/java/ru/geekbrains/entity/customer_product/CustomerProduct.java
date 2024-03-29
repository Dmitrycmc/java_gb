package ru.geekbrains.entity.customer_product;

import ru.geekbrains.entity.customer.Customer;
import ru.geekbrains.entity.product.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class CustomerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private BigDecimal price;

    public CustomerProduct(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
        this.price = product.getPrice();
    }

    public CustomerProduct() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CustomerProduct{" +
                "id=" + id +
                ", customer=" + customer +
                ", product=" + product +
                ", price=" + price +
                '}';
    }
}
