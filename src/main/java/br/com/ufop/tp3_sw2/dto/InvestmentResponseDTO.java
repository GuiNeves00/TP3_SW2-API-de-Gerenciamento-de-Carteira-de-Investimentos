package br.com.ufop.tp3_sw2.dto;

import br.com.ufop.tp3_sw2.enums.InvestmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentResponseDTO {
    private Long id;
    private InvestmentType type;
    private String symbol;
    private Integer quantity;
    private BigDecimal purchasePrice;
    private LocalDate purchaseDate;
}
