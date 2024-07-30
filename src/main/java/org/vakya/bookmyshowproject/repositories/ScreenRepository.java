package org.vakya.bookmyshowproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vakya.bookmyshowproject.model.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {

}
