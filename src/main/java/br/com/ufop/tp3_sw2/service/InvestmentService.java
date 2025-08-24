package br.com.ufop.tp3_sw2.service;

import br.com.ufop.tp3_sw2.dto.InvestmentRequestDTO;
import br.com.ufop.tp3_sw2.dto.InvestmentResponseDTO;
import br.com.ufop.tp3_sw2.dto.InvestmentSummaryDTO;
import br.com.ufop.tp3_sw2.enums.InvestmentType;
import br.com.ufop.tp3_sw2.model.Investment;
import br.com.ufop.tp3_sw2.repository.InvestmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    public InvestmentResponseDTO createInvestment(InvestmentRequestDTO requestDTO) {
        Investment investment = new Investment();
        investment.setType(requestDTO.getType());
        investment.setSymbol(requestDTO.getSymbol());
        investment.setQuantity(requestDTO.getQuantity());
        investment.setPurchasePrice(requestDTO.getPurchasePrice());
        investment.setPurchaseDate(requestDTO.getPurchaseDate());
        Investment savedInvestment = investmentRepository.save(investment);
        return toResponseDTO(savedInvestment);
    }

    public List<InvestmentResponseDTO> getInvestments(InvestmentType type) {
        List<Investment> investments;
        if (type != null) {
            investments = investmentRepository.findByType(type);
        } else {
            investments = investmentRepository.findAll();
        }
        return investments.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public InvestmentResponseDTO updateInvestment(Long id, InvestmentRequestDTO requestDTO) {
        Investment investment = investmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Investment not found with id: " + id));
        investment.setType(requestDTO.getType());
        investment.setSymbol(requestDTO.getSymbol());
        investment.setQuantity(requestDTO.getQuantity());
        investment.setPurchasePrice(requestDTO.getPurchasePrice());
        investment.setPurchaseDate(requestDTO.getPurchaseDate());
        Investment updatedInvestment = investmentRepository.save(investment);
        return toResponseDTO(updatedInvestment);
    }

    public void deleteInvestment(Long id) {
        if (!investmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Investment not found with id: " + id);
        }
        investmentRepository.deleteById(id);
    }

    public InvestmentSummaryDTO getSummary() {
        List<Investment> investments = investmentRepository.findAll();
        BigDecimal totalInvested = investments.stream()
                .map(inv -> inv.getPurchasePrice().multiply(BigDecimal.valueOf(inv.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<InvestmentType, BigDecimal> totalByType = investments.stream()
                .collect(Collectors.groupingBy(
                        Investment::getType,
                        Collectors.mapping(
                                inv -> inv.getPurchasePrice().multiply(BigDecimal.valueOf(inv.getQuantity())),
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                ));

        long assetCount = investments.size();

        return new InvestmentSummaryDTO(totalInvested, totalByType, assetCount);
    }

    private InvestmentResponseDTO toResponseDTO(Investment investment) {
        return new InvestmentResponseDTO(
                investment.getId(),
                investment.getType(),
                investment.getSymbol(),
                investment.getQuantity(),
                investment.getPurchasePrice(),
                investment.getPurchaseDate()
        );
    }
}
