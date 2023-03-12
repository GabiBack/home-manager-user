package com.example.homemanageruser.model;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userEntityToUser(UserEntity userEntity);
}
