package org.vakya.bookmyshowproject.services;

import org.vakya.bookmyshowproject.exeption.UserNotFoundException;
import org.vakya.bookmyshowproject.model.User;

public interface UserService {
    User signUp(String name, String email, String password) throws Exception;
    boolean login(String email, String password) throws UserNotFoundException;
}
