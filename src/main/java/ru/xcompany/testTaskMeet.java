package ru.xcompany;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class testTaskMeet {
    public static void main(String[] args) throws Exception {
        try {
            int q;                                              //количество строк в файле
            String name;                                        //название типа запроса, команды
            Meet meet;                                          //встреча
            ArrayList<Meet> meetList = new ArrayList<>();       //список назначенных встреч
            ArrayList<Meet> meetListSort = new ArrayList<>();   //список встреч для записи результата по второму запросу
            TimeComparator myTimeComparator;                    //компоратор для сортировки по времени
            int day_2;                                          //день для второго типа запроса
            String part_2;                                      //участник для втрого типа запроса
            String result;                                      //результат сравнения

            //создаём Сканер, указываем путь до файла чтения
            File file = new File("C:\\TestTaskMeet\\Meets.txt");
            Scanner sc = new Scanner(file);

            //читаем, записываем первую строку
            q = sc.nextInt();
            //читаем остальные строки
            for (int i = 1; i <= q; i++) {

                //зачищаем результат команды предыдущей строки, начинаем новую команду с "ОК"
                result = "OK";
                //зачищаем список-результат предыдущего запроса
                meetListSort.clear();
                //читаем первую команду строки
                name = sc.next();

                //если запрос 1-го типа
                if (name.equals("APPOINT")) {
                    //создаём встречу
                    meet = new Meet(sc.nextInt(), sc.next(), sc.nextInt(), sc.nextInt(), sc.nextLine());
                    //перебор уже добавленных в список встреч
                    for (Meet j : meetList) {
                        //пересчение перебираемой встречи и вновь созданной, по дню и времени
                        if (j.getDay().equals(meet.getDay())
                                &&
                                (j.getStartTime().before(meet.getStartTime()) && j.finishTime().after(meet.getStartTime())
                                        ||
                                        j.getStartTime().equals(meet.getStartTime())
                                        ||
                                        j.getStartTime().before(meet.finishTime()) && j.finishTime().after(meet.finishTime()))) {
                            //перебираем участников вновь созданной встречи
                            for (String z : meet.getPartsList()) {
                                //вхождение одного из участников вновь созданной встречи в строку участников уже добавленной встречи
                                if (j.getPartsString().contains(z)) {
                                    //если по данному запросу ещё не писали "FAIL"
                                    if (!result.equals("FAIL")) {
                                        result = "FAIL";
                                        System.out.println(result);
                                    }
                                    System.out.print(z + " ");
                                }
                            }
                        }
                    }
                    //перенос строки после вывода занятых участников
                    if (result.equals("FAIL")) {
                        System.out.println();
                    }
                    //результат ОК, добавляем встречу в список
                    if (result.equals("OK")) {
                        meetList.add(meet);
                        System.out.println(result);
                    }
                    //переходим к следующей строке
                    continue;
                }

                //если запрос 2-го типа
                if (name.equals("PRINT")) {
                    day_2 = sc.nextInt();
                    part_2 = sc.next();
                    //перебор добавленных в список встреч
                    for (Meet j : meetList) {
                        //совпадение встречи по запрошенному дню и вхождение запрошенного участника в строку участников
                        if (j.getDay().equals(day_2) && j.getPartsString().contains(part_2)) {
                            //добавляем удовлетворяющие встречи в новый список
                            meetListSort.add(j);
                        }
                    }
                    //для сортировки
                    myTimeComparator = new TimeComparator();
                    meetListSort.sort(myTimeComparator);
                    //вывод отсортированного списка
                    for (Meet z : meetListSort) {
                        System.out.println(z.getStartTimeString() + " " + z.getDuration() + " " + z.getPartsString());
                    }
                }
            }
            //закрываем сканер
            sc.close();
        } catch (Exception ex) {
            System.out.println("Возникла ошибка выполнения, проверьте корректность заполнения файла");
        }
    }
}