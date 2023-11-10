package com.kad.service.Impl;

import com.kad.entity.Gallery;
import com.kad.repository.GalleryRepository;
import com.kad.security.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryRepository grp;

    @Value("${image.upload.path}")
    private String uploadPath;

    public void storeImage(MultipartFile file) throws IOException {
        Path imagePath = Paths.get(uploadPath).resolve(file.getOriginalFilename());

        if (grp.count() < 4) {
            if (!Files.exists(imagePath)) {
                Files.createDirectories(imagePath);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
                Gallery g = new Gallery();
                g.setPostPhotoName(file.getOriginalFilename());
                grp.save(g);
            } catch (IOException e) {
                throw new IOException("Could not save file");
            }
        }
        else {
            throw new RuntimeException("Maximum limit reached for entries.");
        }
    }

    @Override
    public boolean deleteImage(long id) {
        Gallery g = grp.findById(id).orElseThrow(() -> new RuntimeException("Image not Found with ID"));
        Path path = Paths.get(uploadPath+ File.separator+g.getPostPhotoName());
            try {
                if (g.getPostPhotoName() != null) {
                    grp.deleteById(id);
                    Files.deleteIfExists(path);
                }
            } catch (IOException ignored) {
                return false;
            }
        return true;
    }

    @Override
    public List<Gallery> findAll() {
        return grp.findAll();
    }


}
