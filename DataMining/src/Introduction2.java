import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Introduction2 {
    public static void main(String[] args) throws IOException {
        File file = new File("transactions.csv");
        List<String> list = new BufferedReader(new FileReader(file)).lines().skip(1).map(s -> s.split(";")[0]).collect(Collectors.toList());
        Map<String, Integer> prods = new HashMap<>();
        for (String x : list) {
            if (!prods.containsKey(x)) {
                prods.put(x, 1);
            } else {
                prods.put(x, prods.get(x) + 1);
            }
        }
        Map<String, Integer> sortedMap = prods.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        File out = new File("out.csv");
        BufferedWriter buf = new BufferedWriter(new FileWriter(out));
        for (Map.Entry<String, Integer> x : sortedMap.entrySet()) {
            try {
                buf.write(x.getKey() + ";" + x.getValue() + "\n");
                buf.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
