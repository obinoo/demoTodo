package com.example.demoTodo.Repos;
import com.example.demoTodo.Model.Us;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Us, Integer> {
    Us findByEmailAndPassword(String email, String password);

    Us findByEmail(String email);

    Us findTopByPassword(String password);
}