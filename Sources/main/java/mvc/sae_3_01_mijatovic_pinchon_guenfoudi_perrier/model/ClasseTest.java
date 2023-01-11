package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ClasseTest {
    public static void main(String[] args) {
        try {
            String chemin = new File("fichiers_test/Personne.class").toString();
            // Execute the javap command
            Process process = Runtime.getRuntime().exec("javap -p "+chemin);

            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                // Look for lines that start with "  public"
                // to find the fields and methods
                if (line.startsWith("  public")) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}