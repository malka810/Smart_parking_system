package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.UserDTO;

public interface UserService {
    String registerUser(UserDTO userDTO);

    UserDTO getUserById(Long id);

    UserDTO getUserByUsername(String username);

    UserDTO updateUser(UserDTO userDTO);

    Boolean deleteUser(Long id);
}
