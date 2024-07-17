package nazeem.autoparts.client.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

@RestController
public class SpeechController {

    @GetMapping("/speech-to-text")
    public String speechToText() {
        StringBuilder output = new StringBuilder();
        try {
            // Load the Python script from the resources folder
            ClassPathResource resource = new ClassPathResource("python/speech_to_text.py");
            String scriptPath = resource.getFile().getAbsolutePath();

            ProcessBuilder pb = new ProcessBuilder("python3", scriptPath);
            //ProcessBuilder pb = new ProcessBuilder("/Users/nomanazeem/Documents/GitHub/recommendation-ai/.venv/bin/python", scriptPath);
            Map<String, String> env = pb.environment();
            env.put("PYTHONWARNINGS", "ignore");

            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
        return output.toString();
    }
}
