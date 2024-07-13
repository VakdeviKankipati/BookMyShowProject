package org.vakya.bookmyshowproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vakya.bookmyshowproject.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
   /* @Override
    Optional<User> findById(Long userId);*/

    Optional<User> findByEmail(String email);
}
