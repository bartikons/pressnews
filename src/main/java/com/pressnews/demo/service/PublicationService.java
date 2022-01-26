package com.pressnews.demo.service;

import com.pressnews.demo.dto.PublicationDto;
import com.pressnews.demo.model.AuthorModel;
import com.pressnews.demo.model.ContentModel;
import com.pressnews.demo.model.PublicationModel;
import com.pressnews.demo.repository.AuthorRepository;
import com.pressnews.demo.repository.ContentRepository;
import com.pressnews.demo.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final AuthorRepository authorRepository;
    private final ContentRepository contentRepository;
    private final FileSystemStorageService fileSystemStorageService;

    @Autowired
    PublicationService(PublicationRepository publicationRepository, AuthorRepository authorRepository, ContentRepository contentRepository, FileSystemStorageService fileSystemStorageService) {
        this.publicationRepository = publicationRepository;
        this.authorRepository = authorRepository;
        this.contentRepository = contentRepository;
        this.fileSystemStorageService = fileSystemStorageService;
    }

    public ResponseEntity<?> deletePublication(Long publicationId) {
        Optional<PublicationModel> optionalPublicationModel = publicationRepository.findById(publicationId);
        if (optionalPublicationModel.isEmpty()) {
            return new ResponseEntity<>("publication with this Id does not exist", HttpStatus.NOT_FOUND);
        }
        fileSystemStorageService.deleteDirectory(optionalPublicationModel.get().getContent().getId().toString());
        publicationRepository.delete(optionalPublicationModel.get());
        return new ResponseEntity<>("publication have been deleted", HttpStatus.OK);
    }

    public ResponseEntity<?> changePublication(Long id, String title, String name, String date, MultipartFile file) {
        Optional<PublicationModel> publicationModelOptional = publicationRepository.findById(id);
        if (publicationModelOptional.isEmpty()) {
            return new ResponseEntity<>("article with this Id does not exist", HttpStatus.NOT_FOUND);
        }

        ContentModel contentModel = contentRepository.save(new ContentModel(title, uploadFiles(file, publicationModelOptional.get().getId())));
        PublicationModel publicationModel = publicationModelOptional.get();
        publicationModel.setContent(contentModel);
        publicationModel.setName(name);
        publicationModel.setDate(Date.valueOf(date));
        fileSystemStorageService.deleteDirectory(publicationModelOptional.get().getContent().getId().toString());
        publicationModel = publicationRepository.save(publicationModelOptional.get());

        return new ResponseEntity<>(new PublicationDto(publicationModel), HttpStatus.OK);
    }

    public ResponseEntity<?> CreatePublication(String title, String name, Long authorId, String date, MultipartFile file) {
        Optional<AuthorModel> authorModelOptional = authorRepository.findById(authorId);
        if (authorModelOptional.isEmpty()) {
            return new ResponseEntity<>("Author was not found", HttpStatus.NOT_FOUND);
        }
        PublicationModel publicationModel = publicationRepository.save(new PublicationModel(name, date));
        String fileName = uploadFiles(file, publicationModel.getId());
        if (fileName == null) {
            return new ResponseEntity<>("file couldn't be save", HttpStatus.NO_CONTENT);
        }
        ContentModel contentModel = contentRepository.save(new ContentModel(title, fileName));
        publicationModel.setContent(contentModel);
        publicationModel.setAuthor(authorModelOptional.get());
        return new ResponseEntity<>(new PublicationDto(publicationRepository.save(publicationModel)), HttpStatus.OK);
    }

    public ResponseEntity<?> getPublication() {
        return new ResponseEntity<>(publicationRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getPublicationSingle(Long publicationId) {
        Optional<PublicationModel> publicationModelOptional = publicationRepository.findById(publicationId);
        if (publicationModelOptional.isEmpty()) {
            return new ResponseEntity<>("article was not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new PublicationDto(publicationModelOptional.get()), HttpStatus.OK);
    }

    public ResponseEntity<?> getPublicationByText(String keyword) {
        List<PublicationDto> publicationDtoList = Collections.synchronizedList(new ArrayList<>());
        Optional<List<PublicationModel>> publicationModelList = publicationRepository.findAllByKeyword(keyword);

        publicationModelList.ifPresent(publicationModels -> publicationModels.forEach(publicationModel -> publicationDtoList.add(new PublicationDto(publicationModel))));

        publicationRepository.findAll().parallelStream().forEach(publicationModel -> {
            Optional<ContentModel> contentModelOptional = contentRepository.findByPublicationId(publicationModel.getId());
            contentModelOptional.ifPresent(contentModel -> {

                if (fileSystemStorageService.countKeyword(publicationModel.getId().toString(), contentModel.getFile(), keyword) > 0) {
                    publicationDtoList.add(new PublicationDto(publicationModel));
                }
            });
        });
        if (publicationDtoList.size() == 0) {
            return new ResponseEntity<>("article with that keyword was not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(publicationDtoList, HttpStatus.OK);
    }

    public String uploadFiles(MultipartFile file, Long publicationId) {
        fileSystemStorageService.initialize(publicationId.toString());
        if (fileSystemStorageService.store(file)) {
            return file.getOriginalFilename();
        }
        return null;
    }

}
