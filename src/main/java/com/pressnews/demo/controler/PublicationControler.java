package com.pressnews.demo.controler;

import com.pressnews.demo.command.PublicationCommand;
import com.pressnews.demo.service.PublicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/")
public class PublicationControler {

    private final PublicationService publicationService;
    PublicationControler(PublicationService publicationService){
        this.publicationService=publicationService;
    }

    @DeleteMapping
    public ResponseEntity<?> deletePublication(@RequestParam Long articleId){
        return publicationService.deletePublication();
    }
    @PutMapping
    public ResponseEntity<?> changePublication(){
        return publicationService.changePublication();
    }
    @PostMapping
    public ResponseEntity<?> CreatePublication(@RequestBody PublicationCommand publicationCommand){
        return publicationService.CreatePublication();
    }
    @GetMapping
    public ResponseEntity<?> getPublication(){
        return publicationService.getPublication();
    }
    @GetMapping
    public ResponseEntity<?> getPublicationSingle(){
        return publicationService.getPublicationSingle();
    }
    @GetMapping
    public ResponseEntity<?> getPublicationByText(@RequestParam String keyword){
        return publicationService.changePublication(keyword);
    }

}
