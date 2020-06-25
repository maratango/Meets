package ru.xcompany;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

//класс Встречи
public class Meet {

    //день
    private int day;
    //время начала
    private Calendar startTime = new GregorianCalendar(2018, Calendar.JANUARY , 1);
    //время окончания
    private Calendar finishTime = new GregorianCalendar(2018, Calendar.JANUARY , 1);
    //время начала, строка
    private String startTimeString;
    //продолжительность
    private int duration;
    //участники, список
    private ArrayList<String> partsList = new ArrayList<>();
    //участники, строка
    private String partsString;
    //сканер
    Scanner scan;

    //гетеры
    public Integer getDay() { return day; }
    public Integer getDuration() { return duration; }
    public ArrayList<String> getPartsList() { return partsList; }
    public String getPartsString() { return partsString; }
    public String getStartTimeString() { return startTimeString; }
    public Calendar getStartTime() { return startTime; }
    public Calendar finishTime() { return finishTime; }

    //конструктор для начальных заполнений объектов Всречи
    public Meet (int d, String st, int dur, int tech, String p) {
        //присвоить день, продолжительность
        day=d;
        duration = dur;
        //записать участников в строку через пробел
        partsString = p;
        //записать дату начала в строков виде
        startTimeString = st;
        //записать участников в список
        scan = new Scanner(p);
        while (scan.hasNext()) {
            partsList.add(scan.next());
        }
        //вычисляем дату начала
        startTime.set(Calendar.HOUR, Integer.parseInt(st.substring(0, 2)));
        startTime.set(Calendar.MINUTE, Integer.parseInt(st.substring(3)));
        //вычисляем временное значение даты окончания
        finishTime.set(Calendar.HOUR, Integer.parseInt(st.substring(0, 2)));
        finishTime.set(Calendar.MINUTE, Integer.parseInt(st.substring(3)));
        //вычисляем окночательное значение даты окночания
        finishTime.add(Calendar.MINUTE, dur);
    }
}
