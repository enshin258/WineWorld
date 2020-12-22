package com.wineworld.demo.controllers;

import com.wineworld.demo.dtos.opinion.OpinionRequest;
import com.wineworld.demo.dtos.opinion.OpinionResponse;
import com.wineworld.demo.services.OpinionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/opinion")
public class OpinionController {
    private final OpinionService opinionService;

    public OpinionController(OpinionService opinionService) {
        this.opinionService = opinionService;
    }

    @PostMapping("/add")
    public ResponseEntity<OpinionResponse> addOpinion(@RequestBody OpinionRequest opinionRequest){
        if(opinionRequest != null){
            return new ResponseEntity<>(opinionService.addOpinion(opinionRequest), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{userId}/{productId}")
    public ResponseEntity<Void> removeOpinion(@PathVariable Long userId, @PathVariable Long productId){
        opinionService.removeOpinion(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get/opinions/{userId}")
    public ResponseEntity<List<OpinionResponse>> getUserOpinions(@PathVariable Long userId){
        return new ResponseEntity<>(opinionService.getUserOpinions(userId), HttpStatus.OK);
    }


    @GetMapping("/get/products/{productId}")
    public ResponseEntity<List<OpinionResponse>> getOpinions(@PathVariable Long productId){
        return new ResponseEntity<>(opinionService.getAllOpinions(productId), HttpStatus.OK);
    }

    @PutMapping("/update/{opinionId}")
    public ResponseEntity<OpinionResponse> updateOpinion(@PathVariable Long opinionId, @RequestBody OpinionRequest opinionRequest){
        return new ResponseEntity<>(opinionService.updateOpinion(opinionId, opinionRequest), HttpStatus.OK);
    }


}
