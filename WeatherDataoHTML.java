import java.io.*;

import javax.imageio.plugins.tiff.ExifTIFFTagSet;

public class WeatherDataoHTML {
    /**
     * @param args
     */
    public static void main(String[] args) {

        String fileName = "meteo.data";
        String line;
        String html = "<html><body>";

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            html += "<table border=\" " + 1 + "\"" + "<thead> ";
            html += "<tr>";
            html += "<th>Année</th><th>J</th><th>F</th><th>M</th><th>A</th><th>M</th><th>J</th><th>J</th><th>A</th><th>S</th><th>O</th><th>N</th><th>D</th></tr></thead>";
            html += "<tbody>";

            int cnt = 0;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\s+", 3);
                if (cnt == 12) {
                    cnt = 0;
                }
                if (cnt == 0) {

                    html += "<tr>";
                    html += "<td>" + data[0] + "</td>";

                }

                if (Integer.parseInt(data[2]) <= 0) {
                    html += "<td class=\"very_cold\">" + data[2] + "</td>";
                }
                if (Integer.parseInt(data[2]) <= 10 && Integer.parseInt(data[2]) > 0) {
                    html += "<td class=\"cold\">" + data[2] + "</td>";
                }
                if (Integer.parseInt(data[2]) <= 20 && Integer.parseInt(data[2]) > 10) {
                    html += "<td class=\"warm\">" + data[2] + "</td>";
                }
                if (Integer.parseInt(data[2]) <= 30 && Integer.parseInt(data[2]) > 20) {
                    html += "<td class=\"hot\">" + data[2] + "</td>";
                }
                if (Integer.parseInt(data[2]) > 30) {
                    html += "<td class=\"very_hot\">" + data[2] + "</td>";
                }

                if (cnt == 11) {
                    html += "</tr>";
                }
                cnt++;
            }
            html += "</tbody></table>";

            html += "</body></html>";
            bufferedReader.close();

            FileWriter fileWriter = new FileWriter("meteo.html");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(html);
            bufferedWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }
}
