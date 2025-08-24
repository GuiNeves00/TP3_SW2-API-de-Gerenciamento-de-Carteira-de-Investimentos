package br.com.ufop.tp3_sw2.dto;

import br.com.ufop.tp3_sw2.enums.InvestmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentSummaryDTO {
    private BigDecimal totalInvested;
    private Map<InvestmentType, BigDecimal> totalByType;
    private long assetCount;
}
