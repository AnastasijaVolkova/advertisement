package com.roadmap.app.advertisement.service;

import com.roadmap.app.advertisement.dto.AdvertisementDto;
import com.roadmap.app.advertisement.entity.Advertisement;
import com.roadmap.app.advertisement.mapper.AdvertisementMapper;
import com.roadmap.app.advertisement.repository.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor public class AdvertisementService {

    private final AdvertisementMapper mapper;
    private final AdvertisementRepository repository;

    public List<AdvertisementDto> getAllAdvertisements() {
        return mapper.toDtoList(repository.findAll());
    }


    @PostConstruct
    public void init() {
        for (int i = 0; i < 5; i++) {
            repository.save(create());
        }
    }
    private Advertisement create() {
        Advertisement advertisement = new Advertisement();
        advertisement.setEndDateOfAd(LocalDate.now());
        advertisement.setStartDateOfAd(LocalDate.now());
        advertisement.setAddress("Address");
        advertisement.setTitle("Title");
        advertisement.setCity("City");
        advertisement.setPrice(BigDecimal.ONE);
        advertisement.setContactPhoneNumber1("phone 1");
        advertisement.setContactPhoneNumber2("phone 2");
        advertisement.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type");
        return advertisement;
    }

    @Transactional
    public long saveAdvertisement(AdvertisementDto advertisementDto) {
        Advertisement advertisement = mapper.toEntity(advertisementDto);
        advertisement.setStartDateOfAd(LocalDate.now());
        advertisement.setEndDateOfAd(advertisement.getStartDateOfAd().plusMonths(1));
        repository.save(advertisement);
        return advertisement.getId();
    }

    public AdvertisementDto getAdvertisement(long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Transactional
    public AdvertisementDto updateAdvertisement(long id, AdvertisementDto dto) {
        Advertisement advertisement = repository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        mapper.update(advertisement, dto);
        return mapper.toDto(repository.save(advertisement));
    }

    @Transactional
    @Modifying
    public void deleteAdvertisement(long id) {
        repository.deleteById(id);
    }
}
