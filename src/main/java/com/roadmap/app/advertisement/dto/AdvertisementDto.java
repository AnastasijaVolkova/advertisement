package com.roadmap.app.advertisement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AdvertisementDto {

    private long id;
    private String title;
    private BigDecimal price;
    private String description;
    private String contactPhoneNumber1;
    private String contactPhoneNumber2;
    private String contactEmailAddress;
    private String city;
    private String address;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDateOfAd;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDateOfAd;
}
