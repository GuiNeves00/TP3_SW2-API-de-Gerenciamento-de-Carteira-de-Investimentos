package br.com.ufop.tp3_sw2.dto;

import br.com.ufop.tp3_sw2.enums.InvestmentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InvestmentRequestDTO {

    @NotNull
    private InvestmentType type;

    @NotNull
    private String symbol;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @Positive
    private BigDecimal purchasePrice;

    @NotNull
    private LocalDate purchaseDate;
}
