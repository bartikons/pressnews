package com.pressnews.demo.repository;


import com.pressnews.demo.model.PublicationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<PublicationModel, Long> {


    Optional<PublicationModel> findById(Long publicationId);

    @Query(nativeQuery = true, value = "select p.* from Publication p ORDER BY p.date_modification")
    List<PublicationModel> findAll();

    @Query(nativeQuery = true, value = "select p.* from Publication p inner join content c on p.content_id=c.id where c.title like %?1%")
    Optional<List<PublicationModel>> findAllByKeyword(String keyword);

}