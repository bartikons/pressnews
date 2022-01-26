package com.pressnews.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pressnews.demo.service.PublicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private PublicationService publicationService;

    @Test
    void contextLoads() {
    }

    @Test
    void getPublications() {
        try {
            mockMvc.perform(get("/api/Publications"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getPublicationNotFound() {
        try {
            mockMvc.perform(get("/api/Publication?PublicationId=-1"))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getPublicationByKeywordNotFound() {
        try {
            mockMvc.perform(get("/api/PublicationSearch?keyword=rar"))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void createPublicationAndSearch() {
        try {
            MockMultipartFile file
                    = new MockMultipartFile(
                    "file",
                    "hello.txt",
                    MediaType.TEXT_PLAIN_VALUE,
                    "Hello, Rorld!".getBytes()
            );

            MockMvc mockMvc
                    = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            mockMvc.perform(multipart("/api/Publication?title=zip&name=rar AND zip&authorId=1&date=2021-10-21").file(file))
                    .andExpect(status().isOk());

            mockMvc.perform(get("/api/PublicationSearch?keyword=Rorld"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void changePublication() {
        try {
            MockMultipartFile file
                    = new MockMultipartFile(
                    "file",
                    "hello.txt",
                    MediaType.TEXT_PLAIN_VALUE,
                    "Hello, World!".getBytes()
            );

            MockMvc mockMvc
                    = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            mockMvc.perform(multipart("/api/Publication?id=1&title=zip&name=rar AND zip&authorId=1&date=2021-10-21").file(file))
                    .andExpect(status().isOk());

            file = new MockMultipartFile(
                    "file",
                    "hello.txt",
                    MediaType.TEXT_PLAIN_VALUE,
                    "Rello, Rorld!".getBytes()
            );
            ResponseEntity<?> changePublication = publicationService.changePublication(3L, "changeTitle", "changeName", "2010-01-01", file);
            mockMvc.perform(get("/api/PublicationSearch?keyword=Rorld"))
                    .andExpect(status().isOk());
            assertEquals(changePublication.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void createPublication() {
        try {
            MockMultipartFile file
                    = new MockMultipartFile(
                    "file",
                    "hello.txt",
                    MediaType.TEXT_PLAIN_VALUE,
                    "Hello, World!".getBytes()
            );

            MockMvc mockMvc
                    = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            mockMvc.perform(multipart("/api/Publication?id=1&title=zip&name=rar AND zip&authorId=1&date=2021-10-21").file(file))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getPublicationOK() {
        try {
            mockMvc.perform(get("/api/Publication?PublicationId=2"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void deletePublicationOK() {
        try {
            MockMultipartFile file
                    = new MockMultipartFile(
                    "file",
                    "hello.txt",
                    MediaType.TEXT_PLAIN_VALUE,
                    "Hello, World!".getBytes()
            );

            MockMvc mockMvc
                    = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            mockMvc.perform(multipart("/api/Publication?id=1&title=zip&name=rar AND zip&authorId=1&date=2021-10-21").file(file))
                    .andExpect(status().isOk());

            mockMvc.perform(delete("/api/Publication?PublicationId=1"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
