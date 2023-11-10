package com.kad.security;

import com.kad.entity.Gallery;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GalleryService {
    public void storeImage(MultipartFile file) throws IOException;

    public boolean deleteImage(long id);

    public List<Gallery> findAll();

}
