import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

// Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса, 
// используя StringBuilder. Данные для фильтрации приведены ниже в виде json-строки.
// Если значение null, то параметр не должен попадать в запрос.
// Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}


public class Task1 {
    Task1() throws IOException {
        FileReader fr = new FileReader("parameters.txt");
        BufferedReader br = new BufferedReader(fr);
        String str = br.readLine();
        br.close();
        HashMap<String, String> hm = parcer(str);
        System.out.println(result(hm));
    }

    public static String cleaner(String str) {
        String cleaned = str;
        cleaned = cleaned.replace("{", "").replaceAll("\"", "").replace("}", "");
        return cleaned;
    }

    public static String[] splitter(String str) {
        String[] array = str.split(", ");
        return array;
    }

    public static void printer(String[] array) {
        for (String string : array) {
            System.out.println(string);
        }
    }

    public static HashMap<String, String> organizer(String[] array) {
        HashMap<String, String> parameters = new HashMap<>();
        for (String string : array) {
            String key = string.substring(0, string.indexOf(":"));
            String value = string.substring(string.indexOf(":") + 1, string.length());
            parameters.put(key, value);
        }
        return parameters;
    }

    public static HashMap<String, String> parcer(String str) {
        String cleaned = cleaner(str);
        String[] splitted = splitter(cleaned);
        HashMap<String, String> organized = organizer(splitted);
        return organized;
    }

    public static String result(HashMap<String, String> parced) {
        StringBuilder sb = new StringBuilder("select * from students where ");
        int l = sb.length();
        for (String item : parced.keySet()) {
            if (!parced.get(item).equals("null")) {
                if (sb.length() == l) {
                    sb.append(item + " = " + parced.get(item)); 
                } else {
                    sb.append(" and " + item + " = " + parced.get(item));
                }
            }       
        }
        return sb.toString();
    }

}
