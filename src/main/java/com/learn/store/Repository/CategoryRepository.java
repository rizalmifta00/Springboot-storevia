package com.learn.store.Repository;

import com.learn.store.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAll();


    Optional<Category> findByName(String name);

    @Query(value = "Select * FROM Category WHERE name= :?1 ",nativeQuery = true)
    Optional<Category> findbyNama(@Param("?1") String name);
}
