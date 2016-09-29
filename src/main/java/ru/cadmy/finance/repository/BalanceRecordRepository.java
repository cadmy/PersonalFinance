package ru.cadmy.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.cadmy.finance.model.BalanceRecord;
import ru.cadmy.finance.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by ssemenov on 19.09.16.
 */
@Repository
@Transactional
public interface BalanceRecordRepository extends JpaRepository<BalanceRecord, Long> {
    @Query("from BalanceRecord b where b.user = :user and b.date >= :dateFrom and b.date < :dateTo")
    List<BalanceRecord> getBalanceRecordsForPeriod(@Param("user") User user, @Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);
}
