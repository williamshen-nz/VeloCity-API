package nz.shen.velocity.velocity;

import nz.shen.velocity.velocity.Helper.SpeedingCars;
import nz.shen.velocity.velocity.Model.CycleCrashes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class VelocityApplication {
    static CycleCrashes cycleCrashes = new CycleCrashes();
    static SpeedingCars speedingCars;

    public static void main(String[] args) {
        SpringApplication.run(VelocityApplication.class, args);

        readCycleCrashes();
        System.out.println("Read Cycle Crashes successfully!");

        speedingCars = new SpeedingCars();
        System.out.println("Speeding Cars loaded successfully!");

        System.out.println("==========\nAPI DOCS:");
        System.out.println("1. /getRoute?origin=...&destination=...&option=...");
        System.out.println("   Choose option = Shortest, Fastest, Safest, Dangerous, or Scenic");
        System.out.println("2. /getCrashes\n   Gets the crashes in the ACT region");
        System.out.println("=========");
    }

    private static void readCycleCrashes() {
        String csvFile = "data/Cyclist_Crashes.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "\t";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) {
                    first = false;
                    continue;
                }
                // use comma as separator
                String[] crash = line.split(cvsSplitBy);
                cycleCrashes.addCrash(crash[0], crash[2], crash[1], Double.parseDouble(crash[6]), Double.parseDouble(crash[7]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
