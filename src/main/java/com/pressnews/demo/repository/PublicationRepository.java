package com.pressnews.demo.repository;


import com.pressnews.demo.model.PublicationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<PublicationModel, Integer> {

}