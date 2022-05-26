package com.practice.googlesheetpractice.create;



import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.GeneratedIds;
import com.google.api.services.drive.model.Permission;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.practice.googlesheetpractice.googledrivemanager.GoogleDriveManager;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class CreateAndUploadFile {

    @Autowired
    private GoogleDriveManager googleDriveManager;

    public String uploadFile() throws IOException {

        File fileMetadata = new File();
        fileMetadata.setName("GoogleFeed");
        fileMetadata.setMimeType("application/vnd.google-apps.spreadsheet");

        java.io.File filePathTemp =  new java.io.File("/Users/sumit.baria/Downloads/google-sheet-practice/src/main/resources/Book1.csv");
        FileContent fileContent = new FileContent("text/csv", filePathTemp);

        try{
            File fileTemp =  googleDriveManager.getInstance().files().create(fileMetadata, fileContent).setFields("id").execute();
//
//            String fileId = "1j3iwmMylMMEHXCIXOzgcUh4gV-VWvvkm9zuEIT1VONo";
//            File fileTemp = googleDriveManager.getInstance().files().update(fileId, fileMetadata, fileContent).setFields("id").execute();
            System.out.println("id of created file" + fileTemp.getId());
            googleDriveManager.getInstance().permissions().create(fileTemp.getId(), new Permission().setType("anyone").setRole("reader")).execute();
            System.out.println(fileTemp.getPermissions() + " " + fileTemp.getId());
            return fileTemp.getId();

        } catch (Exception e){
            System.out.println("Exception" + e);
            return e.getMessage();
        }
    }
}
