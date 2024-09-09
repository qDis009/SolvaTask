package kz.project.solva.rest.client;

import kz.project.solva.config.property.HolidaysClientProperty;
import kz.project.solva.rest.response.HolidaysResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class HolidaysClient {
  private final HolidaysClientProperty holidaysClientProperty;
  private final RestTemplate restTemplate;

  @Cacheable("holidays")
  public List<HolidaysResponse> getHolidays(int year, String countryCode) {
    HolidaysResponse[] holidays = restTemplate.getForObject(holidaysClientProperty.getUrl(),
            HolidaysResponse[].class, year, countryCode);
    return Arrays.asList(Objects.requireNonNull(holidays));
  }
}
