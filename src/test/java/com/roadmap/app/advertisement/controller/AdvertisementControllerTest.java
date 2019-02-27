package com.roadmap.app.advertisement.controller;

import com.roadmap.app.advertisement.dto.AdvertisementDto;
import com.roadmap.app.advertisement.service.AdvertisementService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AdvertisementControllerTest {

    @Mock
    private AdvertisementService service;
    private AdvertisementController controller;
    @Mock
    private Model model;

    @Before
    public void setUp() throws Exception {
        controller = new AdvertisementController(service);
        Mockito.when(model.addAttribute(any(), any())).thenReturn(model);
    }

    @Test
    public void findAllAdvertisements() {
        String result = controller.findAllAdvertisements(model);
        Assert.assertNotNull(result);
        Assert.assertEquals("ads", result);
        Mockito.verify(model, Mockito.times(1))
                .addAttribute(any(), any());
    }

    @Test
    public void createNewAdvertisement() {
        String result = controller.createNewAdvertisement(new AdvertisementDto());
        Assert.assertNotNull(result);
        Assert.assertEquals("save-ad", result);
    }

    @Test
    public void saveAdvertisement() {
        Mockito.when(service.saveAdvertisement(any())).thenReturn(123L);
        RedirectView redirectView = controller.saveAdvertisement(new AdvertisementDto());
        Assert.assertNotNull(redirectView);
        Assert.assertEquals("/ads/123", redirectView.getUrl());
    }

    @Test
    public void getAdvertisement() {

    }

    @Test
    public void updateAdvertisement() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteAdvertisement() {
    }
}