package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvReader {

    public static List<Map<String, String>> readAsMaps(String filename) {
        List<Map<String, String>> rows = new ArrayList<>();

        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            String headerLine = br.readLine();
            if (headerLine == null) {
                br.close();
                return rows;
            }

            String[] headers = headerLine.split(",");

            String line;
            while (br.ready()) {
                line = br.readLine();
                if (line == null) break;
                if (line.trim().isEmpty()) continue;

                String[] values = line.split(",", -1); // keep empty fields

                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    String key = headers[i].trim();
                    String val = (i < values.length) ? values[i].trim() : "";
                    row.put(key, val);
                }
                rows.add(row);
            }

            br.close();
        } catch (Exception e) {
            System.out.println("CSV read failed: " + e.getMessage());
        }

        return rows;
    }
}
