package com.wineworld.demo.controllers;

import com.wineworld.demo.dtos.role.RoleRequest;
import com.wineworld.demo.dtos.role.RoleResponse;
import com.wineworld.demo.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public ResponseEntity<RoleResponse> addRole(@RequestBody RoleRequest roleRequest){
        if(roleRequest != null){
            return new ResponseEntity<>(roleService.addRole(roleRequest), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<RoleResponse>> getAllRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/get/{roleId}")
    public ResponseEntity<RoleResponse> getRole(@PathVariable Long roleId){
        try {
            return new ResponseEntity<>(roleService.getRoleById(roleId), HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long roleId){
        roleService.deleteRoleById(roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{roleId}")
    public ResponseEntity<RoleResponse> deleteRole(@PathVariable Long roleId, @RequestBody RoleRequest roleRequest){
        try {
            return new ResponseEntity<>(roleService.updateRole(roleId, roleRequest), HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
