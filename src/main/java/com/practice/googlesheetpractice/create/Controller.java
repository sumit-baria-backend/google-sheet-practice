package com.practice.googlesheetpractice.create;


import com.practice.googlesheetpractice.create.CreateAndUploadFile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@Slf4j
public class Controller {
    @Autowired
    private CreateAndUploadFile createAndUploadFile;

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadCsvFile() throws IOException {
        log.info("Request For Upload Resourse File on Drive");
        String fileId = createAndUploadFile.uploadFile();
        if(fileId == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("Success, FileId: "+ fileId);
    }
}
