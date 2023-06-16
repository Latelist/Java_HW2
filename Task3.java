import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 3) Дана json-строка (можно сохранить в файл и читать из файла)
// [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"}
// ,{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
// Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: 
// Студент [фамилия] получил [оценка] по предмету [предмет].
// Пример вывода:
// Студент Иванов получил 5 по предмету Математика.
// Студент Петрова получил 4 по предмету Информатика.
// Студент Краснов получил 5 по предмету Физика.

public class Task3 {
    Task3() throws IOException {
        FileReader fr = new FileReader("param3.txt");
        BufferedReader br = new BufferedReader(fr);
        String text = br.readLine();
        br.close();
        System.out.println(text);
        allTogether(text);

    }

    public static List<String> toList(String text) {
        int begin = 0;
        int end = 0;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < text.length(); ++i) {
            if (text.charAt(i) == '{') {
                begin = i+1;
            } else if (text.charAt(i) == '}') {
                end = i;
                String toAdd = text.substring(begin, end).replaceAll("\"", "");
                list.add(toAdd);
            }
        }
        return list;
    }

    public static String[] splitter(String str) {
        String[] splitted = str.split(",");
        return splitted;
    }

    public static HashMap<String, String> toMap(String[] splitted) {
        HashMap<String, String> mapped = new HashMap<>();
        for (String string : splitted) {
            String key = string.substring(0, string.indexOf(":"));
            String value = string.substring(string.indexOf(":") + 1, string.length());
            mapped.put(key, value);
        }
        return mapped;
    }

    public static String stringGenerator(HashMap<String, String> mapped) {
        String result = "Студент " + mapped.get("фамилия") + " получил " + mapped.get("оценка") + " по предмету " + mapped.get("предмет");
        return result;
    }

    public static void allTogether(String text) {
        List<String> listed = toList(text);
        for (String string : listed) {
            String[] splitted = splitter(string);
            HashMap<String, String> mapped = toMap(splitted);
            System.out.println(stringGenerator(mapped));
        }
    }
}
