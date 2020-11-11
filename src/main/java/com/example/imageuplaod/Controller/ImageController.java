package com.example.imageuplaod.Controller;

import com.example.imageuplaod.Model.ImageModel;
import com.example.imageuplaod.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class ImageController {
    @Autowired
    ImageRepository imageRepository;
    @GetMapping("/list")
    public String getAllCustomers(Model model) {
        model.addAttribute("List", imageRepository.findAll());
        return "list";
    }
    @RequestMapping("/")
    public String register(Model model){
//        model.addAttribute("ImageModel", new ImageModel());
        return "form";
    }
    @PostMapping("/save")
//    @RequestParam("photo") MultipartFile photo, ImageModel imageModel
    public String uplaodImage(@RequestParam("photo") MultipartFile photo,@RequestParam("name") String name){
        ImageModel imageModel = new ImageModel();
        if (photo.isEmpty()){
            return "form";
        }
        Path path = Paths.get("uploads/");
        try {
            InputStream inputStream = photo.getInputStream();
            Files.copy(inputStream,path.resolve(photo.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            imageModel.setPhoto(photo.getOriginalFilename().toLowerCase());
            imageModel.setName(name);
            imageRepository.save(imageModel);
        }catch (Exception e){
            // TDO: handle exception
            e.printStackTrace();
        }
        return "redirect:/list";
    }
    @RequestMapping(value = "getimage/{photo}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> geimage(@PathVariable("photo") String photo){
        if(!photo.equals("") || photo !=null){
            try {
                Path filename = Paths.get("uploads/",photo);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            }catch (Exception e){
                // TDO: handle exception
//                e.printStackTrace();
                System.out.println(photo+" waa lasoo waayay");
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
