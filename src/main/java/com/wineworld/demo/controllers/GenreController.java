package com.wineworld.demo.controllers;

import com.wineworld.demo.dtos.genre.GenreRequest;
import com.wineworld.demo.dtos.genre.GenreResponse;
import com.wineworld.demo.services.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/genre")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/add")
    public ResponseEntity<GenreResponse> addGenre(@RequestBody GenreRequest genreRequest){
        if(genreRequest != null){
            GenreResponse genreResponse = genreService.addGenre(genreRequest);
            return new ResponseEntity<>(genreResponse, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<GenreResponse>> getGenres(){
        return new ResponseEntity<>(genreService.getAllGenres(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{genreId}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long genreId){
        genreService.deleteGenre(genreId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get/{genreId}")
    public ResponseEntity<GenreResponse> getGenre(@PathVariable Long genreId){
        try {
            return new ResponseEntity<>(genreService.getGenre(genreId), HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{genreId}")
    public ResponseEntity<GenreResponse> updateGenre(@PathVariable Long genreId, @RequestBody GenreRequest genreRequest){
        try {
            return new ResponseEntity<>(genreService.updateGenre(genreId, genreRequest), HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
