package com.dobudobu.file_service.Repository;

import com.dobudobu.file_service.Entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends MongoRepository<File, String> {
    Optional<File> findByPublicId(String id);
}
