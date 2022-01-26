package com.pressnews.demo.repository;

import com.pressnews.demo.model.ContentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<ContentModel, Long> {
    @Query(nativeQuery = true, value = "select c.* from content c where c.id=(select p.content_id from publication p where p.id=:id)")
    Optional<ContentModel> findByPublicationId(Long id);

}