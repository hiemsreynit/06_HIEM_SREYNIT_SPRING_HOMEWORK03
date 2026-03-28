package com.hw.exception.hiem_sreynit_spring_homework03.utils;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeStampFormatter {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy").withZone(ZoneId.systemDefault());

}
