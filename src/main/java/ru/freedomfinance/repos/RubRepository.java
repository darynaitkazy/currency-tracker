package ru.freedomfinance.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.freedomfinance.model.Rub;
import ru.freedomfinance.model.Usd;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface RubRepository extends JpaRepository<Rub, Integer>{
    @Query(nativeQuery = true, value = "SELECT *  FROM rub ORDER BY date DESC LIMIT 10")
    List<Rub> getLastRubCurrencyChanges();

    @Query(nativeQuery = true, value = "SELECT *  FROM rub ORDER BY date DESC LIMIT 2")
    List<Rub> getLastTwoRubCurrencyChanges();

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM rub WHERE date = ?1")
    Integer countByDate(String date);
}
