package byrom.currencyconverter.service;

import byrom.currencyconverter.entity.Currency;
import byrom.currencyconverter.entity.dto.CurrencyConverterDto;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@EnableScheduling
@AllArgsConstructor
public class CurrencyApi {

    private final RestTemplate restTemplate;
    private final String FINAL_URL = "https://api.apilayer.com/currency_data/convert?to=RUB&from=USD&amount=1&apikey=nV9q5L5o9tlTZXbVJmqljs7CNXY8WKjy";
    private final String URL = "https://api.apilayer.com/currency_data/convert?";

    public CurrencyConverterDto getCurrencyConverter(Currency currencyTo, Currency currencyFrom, Double amount){
        String url = URL + "to=" + currencyTo + "&from=" + currencyFrom + "&amount=" +
                amount + "&apikey=nV9q5L5o9tlTZXbVJmqljs7CNXY8WKjy";

        ResponseEntity<CurrencyConverterDto> response =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });

        return response.getBody();
    }
}
