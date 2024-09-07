package kz.project.solva.rest.response;

import java.util.List;

public record HolidaysResponse(
    String date,
    String localName,
    String name,
    String countryCode,
    Boolean fixed,
    Boolean global,
    List<String> countries,
    Integer launchYear,
    List<String> types) {}
