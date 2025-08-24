package br.com.ufop.tp3_sw2.controller;

import br.com.ufop.tp3_sw2.dto.InvestmentRequestDTO;
import br.com.ufop.tp3_sw2.dto.InvestmentResponseDTO;
import br.com.ufop.tp3_sw2.dto.InvestmentSummaryDTO;
import br.com.ufop.tp3_sw2.enums.InvestmentType;
import br.com.ufop.tp3_sw2.service.InvestmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investments")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @PostMapping
    public ResponseEntity<InvestmentResponseDTO> createInvestment(@Valid @RequestBody InvestmentRequestDTO requestDTO) {
        InvestmentResponseDTO responseDTO = investmentService.createInvestment(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InvestmentResponseDTO>> getInvestments(@RequestParam(required = false) InvestmentType type) {
        List<InvestmentResponseDTO> investments = investmentService.getInvestments(type);
        return ResponseEntity.ok(investments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestmentResponseDTO> updateInvestment(@PathVariable Long id, @Valid @RequestBody InvestmentRequestDTO requestDTO) {
        InvestmentResponseDTO updatedInvestment = investmentService.updateInvestment(id, requestDTO);
        return ResponseEntity.ok(updatedInvestment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {
        investmentService.deleteInvestment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary")
    public ResponseEntity<InvestmentSummaryDTO> getSummary() {
        InvestmentSummaryDTO summary = investmentService.getSummary();
        return ResponseEntity.ok(summary);
    }
}
