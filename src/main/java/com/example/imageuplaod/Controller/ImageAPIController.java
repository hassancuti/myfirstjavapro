package com.example.imageuplaod.Controller;

import com.example.imageuplaod.Model.ImageModel;
import com.example.imageuplaod.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/")
public class ImageAPIController {
    @Autowired
    ImageRepository imageRepository;
    @GetMapping("list")
    public List<ImageModel> getAll() {
        return imageRepository.findAll();
    }
}
