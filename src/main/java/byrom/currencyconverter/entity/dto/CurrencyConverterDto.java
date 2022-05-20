package byrom.currencyconverter.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CurrencyConverterDto {

    private double result;

    @JsonProperty("query")
    private ConverterDto converterDto;
}
