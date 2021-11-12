package com.example.bkshopping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class DateUtility {

    public static String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
