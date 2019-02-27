package com.roadmap.app.advertisement.service;

import com.roadmap.app.advertisement.dto.AdvertisementDto;
import com.roadmap.app.advertisement.entity.Advertisement;
import com.roadmap.app.advertisement.mapper.AdvertisementMapper;
import com.roadmap.app.advertisement.repository.AdvertisementRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AdvertisementServiceTest {

    private AdvertisementService advertisementService;
    @Mock
    private AdvertisementRepository advertisementRepository;
    @Mock
    private AdvertisementMapper advertisementMapper;

    @Before
    public void setUp() {
        Advertisement advertisement = new Advertisement();
        advertisement.setId(123);
        Mockito.when(advertisementMapper.toEntity(any())).thenReturn(advertisement);
        advertisementService = new AdvertisementService(advertisementMapper, advertisementRepository);
        Mockito.when(advertisementRepository.findAll())
                .thenReturn(Collections.emptyList());
        Mockito.when(advertisementMapper.toDtoList(any())).thenReturn(Arrays.asList(new AdvertisementDto(),
                new AdvertisementDto()));
        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setId(123);
        Mockito.when(advertisementMapper.toDto(any())).thenReturn(advertisementDto);
        Mockito.when(advertisementRepository.save(any())).thenReturn(new Advertisement());
        Mockito.doNothing().when(advertisementMapper).update(any(), any());
    }

    @Test
    public void getAllAdvertisements() {
        List<AdvertisementDto> allAdvertisements = advertisementService.getAllAdvertisements();
        Assert.assertNotNull(allAdvertisements);
        Assert.assertFalse(allAdvertisements.isEmpty());
        Assert.assertEquals(2, allAdvertisements.size());
    }

    @Test
    public void saveAdvertisement() {
        long id = advertisementService.saveAdvertisement(new AdvertisementDto());
        Assert.assertEquals(123, id);
    }

    @Test
    public void getAdvertisement() {
        Mockito.when(advertisementRepository.findById(any()))
                .thenReturn(Optional.of(new Advertisement()));
        AdvertisementDto advertisement = advertisementService.getAdvertisement(122);
        Assert.assertNotNull(advertisement);
        Assert.assertEquals(123, advertisement.getId());
    }

    @Test
    public void getNullAdvertisement() {
        Mockito.when(advertisementRepository.findById(any()))
                .thenReturn(Optional.empty());
        Mockito.when(advertisementMapper.toDto(null))
                .thenReturn(null);
        AdvertisementDto advertisement = advertisementService.getAdvertisement(122);
        Assert.assertNull(advertisement);
    }

    @Test
    public void updateAdvertisement() {
        Mockito.when(advertisementRepository.findById(any())).thenReturn(Optional.of(new Advertisement()));
        AdvertisementDto advertisementDto = advertisementService.updateAdvertisement(123, new AdvertisementDto());
        Mockito.verify(advertisementRepository, Mockito.times(1)).findById(123L);
        Mockito.verify(advertisementRepository, Mockito.times(1)).save(any());
        Assert.assertNotNull(advertisementDto);
        Assert.assertEquals(123, advertisementDto.getId());
    }

    @Test(expected = HttpClientErrorException.class)
    public void updateAdvertisementWithException() {
        Mockito.when(advertisementRepository.findById(any())).thenReturn(Optional.empty());
        try {
            advertisementService.updateAdvertisement(123, new AdvertisementDto());
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
            throw ex;
        }
    }

    @Test
    public void deleteAdvertisement() {
        Mockito.doNothing().when(advertisementRepository).deleteById(any());
        advertisementService.deleteAdvertisement(123);
        Mockito.verify(advertisementRepository, Mockito.times(1))
                .deleteById(123L);
    }
}