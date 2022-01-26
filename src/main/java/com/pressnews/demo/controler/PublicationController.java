package com.pressnews.demo.controler;

import com.pressnews.demo.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/")
public class PublicationController {

    private final PublicationService publicationService;

    @Autowired
    PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @DeleteMapping("Publication")
    public ResponseEntity<?> deletePublication(@RequestParam Long PublicationId) {
        return publicationService.deletePublication(PublicationId);
    }

    @PutMapping("Publication")
    public ResponseEntity<?> changePublication(@RequestParam Long PublicationId, @RequestParam String title, @RequestParam String name, @RequestParam String date, @RequestParam(value = "file") MultipartFile file) {
        return publicationService.changePublication(PublicationId, title, name, date, file);
    }

    @PostMapping(value = "Publication", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<?> CreatePublication(@RequestParam String title, @RequestParam String name, @RequestParam Long authorId, @RequestParam String date, @RequestParam(value = "file") MultipartFile file) {
        return publicationService.CreatePublication(title, name, authorId, date, file);
    }

    @GetMapping("Publications")
    public ResponseEntity<?> getPublication() {
        return publicationService.getPublication();
    }

    @GetMapping("Publication")
    public ResponseEntity<?> getPublicationSingle(@RequestParam Long PublicationId) {
        return publicationService.getPublicationSingle(PublicationId);
    }

    @GetMapping("PublicationSearch")
    public ResponseEntity<?> getPublicationByText(@RequestParam String keyword) {
        return publicationService.getPublicationByText(keyword);
    }

}
