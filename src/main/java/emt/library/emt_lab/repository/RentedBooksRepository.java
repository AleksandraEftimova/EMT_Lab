package emt.library.emt_lab.repository;

import emt.library.emt_lab.model.RentedBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentedBooksRepository extends JpaRepository<RentedBooks, Long> {


}
