package com.wineworld.demo.services;

import com.wineworld.demo.dtos.role.RoleRequest;
import com.wineworld.demo.dtos.role.RoleResponse;
import com.wineworld.demo.entities.Role;
import com.wineworld.demo.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.modelMapper = new ModelMapper();
    }

    public RoleResponse addRole(RoleRequest roleRequest){
        Role role = modelMapper.map(roleRequest, Role.class);
        Role createdRole = roleRepository.save(role);
        return modelMapper.map(createdRole, RoleResponse.class);
    }

    public RoleResponse getRoleById(Long roleId){
        return modelMapper.map(roleRepository.findById(roleId).orElseThrow(EntityExistsException::new), RoleResponse.class);
    }

    public List<RoleResponse> getAllRoles(){
        return roleRepository.findAll().stream()
                .map(role -> modelMapper.map(role, RoleResponse.class))
                .collect(Collectors.toList());
    }

    public void deleteRoleById(Long roleId){
        roleRepository.deleteById(roleId);
    }

    public RoleResponse updateRole(Long roleId, RoleRequest roleRequest){
        Role roleToUpdate = roleRepository.getOne(roleId);
        roleToUpdate.setName(roleRequest.getName());
        roleRepository.save(roleToUpdate);
        return modelMapper.map(roleToUpdate, RoleResponse.class);
    }


}
