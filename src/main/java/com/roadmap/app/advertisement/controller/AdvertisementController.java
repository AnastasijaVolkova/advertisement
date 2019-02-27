package com.roadmap.app.advertisement.controller;

import com.roadmap.app.advertisement.dto.AdvertisementDto;
import com.roadmap.app.advertisement.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequiredArgsConstructor
public class AdvertisementController {

    private final AdvertisementService service;

    @GetMapping({"/ads","/"})
    public String findAllAdvertisements(Model model) {
        model.addAttribute("ads", service.getAllAdvertisements());
        return "ads";

    }

    @GetMapping("/ads/create")
    public String createNewAdvertisement(AdvertisementDto advertisementDto) {
        return "save-ad";
    }

    @PostMapping("/newAd")
    public RedirectView saveAdvertisement(@ModelAttribute AdvertisementDto advertisementDto) {
        long id = service.saveAdvertisement(advertisementDto);
        return new RedirectView("/ads/" + id);
    }

    @GetMapping("/ads/{id}")
    public String getAdvertisement(@PathVariable long id, Model model) {
        AdvertisementDto advertisement = service.getAdvertisement(id);
        model.addAttribute("ad", advertisement);
        return "single-ad";
    }

    @PostMapping("/ads/update/{id}")
    public RedirectView updateAdvertisement(@PathVariable long id, @ModelAttribute AdvertisementDto advertisementDto) {
        service.updateAdvertisement(id, advertisementDto);
        return new RedirectView("/ads/" + id);
    }

    @GetMapping("/ads/update/{id}")
    public String update(@PathVariable long id, Model model) {
        model.addAttribute("advertisementDto", service.getAdvertisement(id));
        return "update-ad";
    }

    @DeleteMapping("/ads/{id}")
    public RedirectView deleteAdvertisement(@PathVariable long id) {
        service.deleteAdvertisement(id);
        return new RedirectView("/");
    }

}
