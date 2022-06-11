package ru.freedomfinance.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.freedomfinance.model.Eur;
import ru.freedomfinance.model.Usd;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface EurRepository extends JpaRepository<Eur, Integer> {
    @Query(nativeQuery = true, value = "SELECT *  FROM eur ORDER BY date DESC LIMIT 10")
    List<Eur> getLastEurCurrencyChanges();
    @Query(nativeQuery = true, value = "SELECT *  FROM eur ORDER BY date DESC LIMIT 2")
    List<Eur> getLastTwoEurCurrencyChanges();
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM eur WHERE date = ?1")
    Integer countByDate(String date);
}
