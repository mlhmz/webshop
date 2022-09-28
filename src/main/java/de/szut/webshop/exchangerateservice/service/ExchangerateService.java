package de.szut.webshop.exchangerateservice.service;

import de.szut.webshop.exchangerateservice.dto.RateDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangerateService {
    public static final String DEFAULT_CURRENCY = "EUR";

    private static final String EXCHANGE_API_URL = "https://api.exchangerate.host/";

    private final RestTemplate template;

    public ExchangerateService() {
        this.template = new RestTemplate();
    }

    /**
     * Converts a certain amount from a currency to a currency
     * @param from currency to convert from
     * @param to currency to convert to
     * @param amount amount to convert
     * @return response as dto
     */
    public RateDto convert(String from, String to, Double amount) {
        return template.getForEntity(EXCHANGE_API_URL + "convert?from={from}&to={to}&amount={amount}", RateDto.class,
                from, to, amount).getBody();
    }
}
