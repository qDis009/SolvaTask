package kz.project.solva.rest.client;

import kz.project.solva.config.property.HolidaysClientProperty;
import kz.project.solva.rest.response.HolidaysResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HolidaysClientTest {

  @Mock private RestTemplate restTemplate;

  @Mock private HolidaysClientProperty holidaysClientProperty;

  @InjectMocks private HolidaysClient holidaysClient;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetHolidaysOnce() {
    int year = 2024;
    String countryCode = "KZ";
    String mockUrl = "https://date.nager.at/api/v3/publicholidays/{year}/{countryCode}";

    when(holidaysClientProperty.getUrl()).thenReturn(mockUrl);

    HolidaysResponse[] mockResponse = {
      new HolidaysResponse(
          "2024-01-01",
          "Жаңа жыл",
          "New Year's Day",
          "KZ",
          false,
          true,
          null,
          null,
          List.of("Public"))
    };

    when(restTemplate.getForObject(mockUrl, HolidaysResponse[].class, year, countryCode))
        .thenReturn(mockResponse);

    List<HolidaysResponse> holidays = holidaysClient.getHolidays(year, countryCode);

    assertEquals(1, holidays.size());
    assertEquals("New Year's Day", holidays.get(0).name());
    verify(restTemplate, times(1))
        .getForObject(mockUrl, HolidaysResponse[].class, year, countryCode);
  }
}
