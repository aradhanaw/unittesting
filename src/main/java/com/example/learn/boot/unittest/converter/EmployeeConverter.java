package com.example.learn.boot.unittest.converter;

import com.example.learn.boot.unittest.model.EmployeeDTO;
import com.example.learn.boot.unittest.domain.PersistentEmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter extends Convertable<PersistentEmployeeEntity, EmployeeDTO>{
    @Override
    public PersistentEmployeeEntity convertToEntity(EmployeeDTO dto) {
        return this.copyConvertToEntity(dto,new PersistentEmployeeEntity());
    }

    @Override
    public EmployeeDTO convertToDto(PersistentEmployeeEntity entity) {
        if(entity == null){
            return null;
        }
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        return dto;
    }

    @Override
    public PersistentEmployeeEntity copyConvertToEntity(EmployeeDTO dto, PersistentEmployeeEntity entity) {
        if(dto == null || entity == null){
            return entity;
        }
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}
