package com.roadmap.app.advertisement.mapper;

import com.roadmap.app.advertisement.dto.AdvertisementDto;
import com.roadmap.app.advertisement.entity.Advertisement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AdvertisementMapperTest {

    private AdvertisementMapper advertisementMapper;

    @Before
    public void setUp() throws Exception {
        advertisementMapper = new AdvertisementMapper();
    }

    @Test
    public void toDtoNullParam() {
        AdvertisementDto advertisementDto = advertisementMapper.toDto(null);
        Assert.assertNotNull((advertisementDto));
        Assert.assertEquals(new AdvertisementDto(), advertisementDto);
    }

    @Test
    public void toDto() {
        AdvertisementDto advertisementDto = advertisementMapper.toDto(createAd());
        assertDto(advertisementDto);
    }

    @Test
    public void toDtoList() {
        List<AdvertisementDto> advertisementDtos = advertisementMapper
                .toDtoList(Collections.singletonList(createAd()));
        Assert.assertNotNull(advertisementDtos);
        Assert.assertEquals(1, advertisementDtos.size());
        assertDto(advertisementDtos.get(0));
    }

    @Test
    public void toEntity() {
        Advertisement advertisement = advertisementMapper.toEntity(createAdDto());
        Assert.assertNotNull(advertisement);
        Assert.assertEquals(123, advertisement.getId());
        Assert.assertEquals(BigDecimal.TEN, advertisement.getPrice());
        Assert.assertEquals("testTitle", advertisement.getTitle());
        Assert.assertEquals("descr", advertisement.getDescription());
        Assert.assertEquals("phoneNumber1", advertisement.getContactPhoneNumber1());
        Assert.assertEquals("phoneNumber2", advertisement.getContactPhoneNumber2());
        Assert.assertEquals("city", advertisement.getCity());
        Assert.assertEquals("address", advertisement.getAddress());
        Assert.assertEquals("email", advertisement.getContactEmailAddress());
        Assert.assertEquals(LocalDate.of(2017, 2,2), advertisement.getStartDateOfAd());
        Assert.assertEquals(LocalDate.of(2018, 3,6), advertisement.getEndDateOfAd());
    }

    @Test
    public void update() {
        Advertisement advertisement = new Advertisement();
        advertisement.setId(555);
        advertisement.setStartDateOfAd(LocalDate.now());
        advertisement.setEndDateOfAd(LocalDate.now());
        advertisementMapper.update(advertisement, createAdDto());

        Assert.assertNotNull(advertisement);
        Assert.assertEquals(555, advertisement.getId());
        Assert.assertEquals(BigDecimal.TEN, advertisement.getPrice());
        Assert.assertEquals("testTitle", advertisement.getTitle());
        Assert.assertEquals("descr", advertisement.getDescription());
        Assert.assertEquals("phoneNumber1", advertisement.getContactPhoneNumber1());
        Assert.assertEquals("phoneNumber2", advertisement.getContactPhoneNumber2());
        Assert.assertEquals("city", advertisement.getCity());
        Assert.assertEquals("address", advertisement.getAddress());
        Assert.assertEquals("email", advertisement.getContactEmailAddress());
        Assert.assertEquals(LocalDate.now(), advertisement.getStartDateOfAd());
        Assert.assertEquals(LocalDate.now(), advertisement.getEndDateOfAd());
    }

    @Test
    public void updateWithNullDto() {
        Advertisement advertisement = new Advertisement();
        advertisementMapper.update(advertisement, null);
        Assert.assertNotNull(advertisement);
        Assert.assertEquals(0, advertisement.getId());
        Assert.assertNull(advertisement.getStartDateOfAd());
        Assert.assertNull(advertisement.getAddress());
        Assert.assertNull(advertisement.getCity());
        Assert.assertNull(advertisement.getContactEmailAddress());
        Assert.assertNull(advertisement.getContactPhoneNumber1());
        Assert.assertNull(advertisement.getContactPhoneNumber2());
        Assert.assertNull(advertisement.getDescription());
        Assert.assertNull(advertisement.getEndDateOfAd());
        Assert.assertNull(advertisement.getPrice());
        Assert.assertNull(advertisement.getTitle());
    }

    private void assertDto(AdvertisementDto advertisementDto) {
        Assert.assertNotNull(advertisementDto);
        Assert.assertEquals(123, advertisementDto.getId());
        Assert.assertEquals(BigDecimal.TEN, advertisementDto.getPrice());
        Assert.assertEquals("testTitle", advertisementDto.getTitle());
        Assert.assertEquals("descr", advertisementDto.getDescription());
        Assert.assertEquals("phoneNumber1", advertisementDto.getContactPhoneNumber1());
        Assert.assertEquals("phoneNumber2", advertisementDto.getContactPhoneNumber2());
        Assert.assertEquals("city", advertisementDto.getCity());
        Assert.assertEquals("address", advertisementDto.getAddress());
        Assert.assertEquals("email", advertisementDto.getContactEmailAddress());
        Assert.assertEquals(LocalDate.of(2017, 2,2), advertisementDto.getStartDateOfAd());
        Assert.assertEquals(LocalDate.of(2018, 3,6), advertisementDto.getEndDateOfAd());
    }

    private AdvertisementDto createAdDto() {
        AdvertisementDto advertisement = new AdvertisementDto();
        advertisement.setId(123);
        advertisement.setPrice(BigDecimal.TEN);
        advertisement.setTitle("testTitle");
        advertisement.setDescription("descr");
        advertisement.setContactPhoneNumber1("phoneNumber1");
        advertisement.setContactPhoneNumber2("phoneNumber2");
        advertisement.setCity("city");
        advertisement.setAddress("address");
        advertisement.setStartDateOfAd(LocalDate.of(2017, 2,2));
        advertisement.setEndDateOfAd(LocalDate.of(2018, 3,6));
        advertisement.setContactEmailAddress("email");
        return advertisement;
    }

    private Advertisement createAd() {
        Advertisement advertisement = new Advertisement();
        advertisement.setId(123);
        advertisement.setPrice(BigDecimal.TEN);
        advertisement.setTitle("testTitle");
        advertisement.setDescription("descr");
        advertisement.setContactPhoneNumber1("phoneNumber1");
        advertisement.setContactPhoneNumber2("phoneNumber2");
        advertisement.setCity("city");
        advertisement.setAddress("address");
        advertisement.setStartDateOfAd(LocalDate.of(2017, 2,2));
        advertisement.setEndDateOfAd(LocalDate.of(2018, 3,6));
        advertisement.setContactEmailAddress("email");
        return advertisement;
    }
}