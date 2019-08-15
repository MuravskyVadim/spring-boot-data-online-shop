package com.example.onlineshop.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.PrimaryKeyJoinColumn;

@Setter
@Getter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "orders", schema = "spring_shop")
@PrimaryKeyJoinColumn(name = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "code_id")
    private Code code;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    public Order() {
    }

    public Order(Basket basket, User user, Code code, String firstName, String lastName,
                 String city, String street, String houseNumber, String phoneNumber) {
        this.basket = basket;
        this.user = user;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.phoneNumber = phoneNumber;
    }

    public Order(Long id, Basket basket, User user, Code code, String firstName, String lastName,
                 String city, String street, String houseNumber, String phoneNumber) {
        this.id = id;
        this.basket = basket;
        this.user = user;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.phoneNumber = phoneNumber;
    }
}
