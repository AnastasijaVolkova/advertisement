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
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementMapper mapper;
    private final AdvertisementRepository repository;

    public List<AdvertisementDto> getAllAdvertisements() {
        return mapper.toDtoList(repository.findAll());
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
