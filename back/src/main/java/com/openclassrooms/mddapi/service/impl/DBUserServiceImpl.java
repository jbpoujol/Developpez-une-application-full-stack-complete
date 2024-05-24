package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.excepton.CustomNotFoundException;
import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.repository.DBUserRepository;
import com.openclassrooms.mddapi.service.DBUserService;
import com.openclassrooms.mddapi.util.DtoConverter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DBUserServiceImpl implements DBUserService {

    private final DBUserRepository dbUserRepository;

    public DBUserServiceImpl(DBUserRepository dbUserRepository) {
        this.dbUserRepository = dbUserRepository;
    }

    @Override
    public Optional<DBUser> find(String email) {
        return dbUserRepository.findByEmail(email);
    }

    @Override
    public Optional<DBUser> findUserById(Long id) {
        return dbUserRepository.findById(id);
    }

    @Override
    public UserDTO findUserDTOById(Long id) {
        return dbUserRepository.findById(id)
                .map(DtoConverter::convertToUserDTO)
                .orElseThrow(() -> new CustomNotFoundException("User not found"));
    }
}
