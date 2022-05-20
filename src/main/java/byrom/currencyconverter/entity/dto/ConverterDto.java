package byrom.currencyconverter.entity.dto;

import byrom.currencyconverter.entity.Currency;
import lombok.Data;

@Data
public class ConverterDto {

    private Currency from;

    private Currency to;

    private Long amount;
}
