package ru.freedomfinance.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.freedomfinance.model.Chat_id;

@Repository
public interface ChatIdRepository extends JpaRepository<Chat_id, Integer> {


    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM chat_ids WHERE chat_id = ?1")
    Integer countByIds(String chat_id);
}
