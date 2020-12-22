package com.wineworld.demo.services;

import com.wineworld.demo.dtos.genre.GenreRequest;
import com.wineworld.demo.dtos.genre.GenreResponse;
import com.wineworld.demo.entities.Genre;
import com.wineworld.demo.repositories.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GenreService {
    private final GenreRepository genreRepository;
    private ModelMapper modelMapper;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
        modelMapper = new ModelMapper();
    }

    public GenreResponse addGenre(GenreRequest genreRequest){
        Genre genre = modelMapper.map(genreRequest, Genre.class);
        Genre createdGenre = genreRepository.save(genre);
        return modelMapper.map(createdGenre, GenreResponse.class);
    }

    public void deleteGenre(Long genreId){
        genreRepository.deleteById(genreId);
    }

    public List<GenreResponse> getAllGenres(){
        return genreRepository.findAll().stream()
                .map(genre -> modelMapper.map(genre, GenreResponse.class))
                .collect(Collectors.toList());
    }

    public GenreResponse getGenre(Long genreId){
        return modelMapper.map(genreRepository.findById(genreId).orElseThrow(EntityExistsException::new), GenreResponse.class);
    }

    public GenreResponse updateGenre(Long genreId, GenreRequest genreRequest){
        Genre genreToUpdate = genreRepository.getOne(genreId);
        genreToUpdate.setName(genreRequest.getName());
        genreRepository.save(genreToUpdate);
        return modelMapper.map(genreToUpdate, GenreResponse.class);
    }
}
