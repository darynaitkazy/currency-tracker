package ru.freedomfinance.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.freedomfinance.model.Usd;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsdRepository extends JpaRepository<Usd, Integer> {
    @Query(nativeQuery = true, value = "SELECT *  FROM usd ORDER BY date DESC LIMIT 10")
    List<Usd> getLastUsdCurrencyChanges();

    @Query(nativeQuery = true, value = "SELECT *  FROM usd ORDER BY date DESC LIMIT 2")
    List<Usd> getLastTwoUsdCurrencyChanges();

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM usd WHERE date = ?1")
    Integer countByDate(String date);
}
