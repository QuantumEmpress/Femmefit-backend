package FemmeFit.demo2.repository;

import FemmeFit.demo2.entity.ProgressReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressReportRepository extends JpaRepository<ProgressReport, Long> {
    List<ProgressReport> findByUserId(Long userId);
}