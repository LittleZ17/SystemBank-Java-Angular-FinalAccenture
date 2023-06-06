package zyBank.TransactionService.controller.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
public class FileController {

    @Autowired
    private RestTemplate restTemplate;

    private Path rootLocation;


    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        String uploadUrl = "http://localhost:8081/media/upload";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Prepare the request body with the file and its content length
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        headers.setContentLength(file.getSize());

        // Create the RequestEntity with the headers and body
        RequestEntity<MultiValueMap<String, Object>> requestEntity = new RequestEntity<>(body, headers, HttpMethod.POST, URI.create(uploadUrl));

        // Send the request using RestTemplate and retrieve the response
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        // Handle the response as needed
        ResponseEntity response = responseEntity;
        return response;
    }

    @GetMapping("/files/{filename}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resource> getFile(@PathVariable(name = "filename") String filename) {
        System.out.println(filename);
        String getUrl = "http://localhost:8081/media/" + filename; // Reemplaza con la URL de tu controlador original

        ResponseEntity<Resource> response = restTemplate.getForEntity(getUrl, Resource.class);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", response.getHeaders().getFirst("Content-Type"));

        return ResponseEntity.ok().headers(headers).body(response.getBody());
    }
}
