package com.kad.controller;
import com.kad.security.GalleryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@CrossOrigin
public class GalleryController {

    @Autowired
    private GalleryService gsr;

    @PostMapping("/upload")
    public ResponseEntity<String> galleryUpload(@RequestParam("postPhoto") MultipartFile postPhoto) {
        if (postPhoto.isEmpty() || postPhoto.getSize() <= 0){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image Not Found in request");
        }
        else {
            try {
                gsr.storeImage(postPhoto);
                return ResponseEntity.ok("Image uploaded successfully");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
            }
        }
    }

    @GetMapping("/getAllGallery")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(gsr.findAll());
    }


    @DeleteMapping("/delete/{gId}")
    public ResponseEntity<String> deleteGalleryImage(@PathVariable("gId") long gId){
        if (gsr.deleteImage(gId)){
            return ResponseEntity.ok("Image Deleted Successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete image");
        }
    }
}