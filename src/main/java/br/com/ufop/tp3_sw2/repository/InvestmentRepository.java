package br.com.ufop.tp3_sw2.repository;

import br.com.ufop.tp3_sw2.enums.InvestmentType;
import br.com.ufop.tp3_sw2.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByType(InvestmentType type);
}
