package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {
    public String  currentDate(int num){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf  = new SimpleDateFormat("hh");


        String todayDate =  sdf.format(cal.getInstance());
        cal.setTime(new java.util.Date());
        cal.add(Calendar.HOUR,num);



        return sdf.format(cal.getTime());

    }
}
