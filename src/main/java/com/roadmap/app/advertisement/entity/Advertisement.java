package com.roadmap.app.advertisement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Title")
    @NotBlank(message = "Title is mandatory")
    private String title;
    @Column(name = "Price")
    private BigDecimal price;
    @Column(name = "Description")
    @NotBlank(message = "Description is mandatory")
    private String description;
    @Column(name = "Phone_Number1")
    @NotBlank(message = "At least one phone number should be entered")
    private String contactPhoneNumber1;
    @Column(name = "Phone_Number2")
    private String contactPhoneNumber2;
    @Column(name = "Email_Address")
    private String contactEmailAddress;
    @Column(name = "City")
    private String city;
    @Column(name = "Address")
    private String address;
    @Column(name = "Start_date")
    private LocalDate startDateOfAd;
    @Column(name = "End_date")
    private LocalDate endDateOfAd;
    @Column(name = "Archive")
    private boolean archive;

    public Advertisement(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Advertisement() {
    }
}
