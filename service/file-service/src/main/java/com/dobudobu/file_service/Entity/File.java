package com.dobudobu.file_service.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "file")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    private String id;

    private String publicId;

    private String fileUrl;

    private String fileType;

}
