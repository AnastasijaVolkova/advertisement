package com.roadmap.app.advertisement.mapper;

import com.roadmap.app.advertisement.dto.AdvertisementDto;
import com.roadmap.app.advertisement.entity.Advertisement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AdvertisementMapper {

    public AdvertisementDto toDto(Advertisement advertisement) {
        AdvertisementDto advertisementDto = new AdvertisementDto();
        if (advertisement != null) {
            BeanUtils.copyProperties(advertisement, advertisementDto, AdvertisementDto.class);
        }
        return advertisementDto;
    }

    public List<AdvertisementDto> toDtoList(List<Advertisement> advertisements) {
        return advertisements.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Advertisement toEntity(AdvertisementDto dto) {
        Advertisement advertisement = new Advertisement();
        if (dto != null) {
            BeanUtils.copyProperties(dto, advertisement, Advertisement.class);
        }
        return advertisement;
    }

    public void update(Advertisement advertisement, AdvertisementDto dto) {
        if (dto != null && advertisement != null) {
            BeanUtils.copyProperties(dto, advertisement, Advertisement.class);
        }
    }
}
