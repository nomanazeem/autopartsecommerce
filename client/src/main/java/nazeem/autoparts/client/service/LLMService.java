package nazeem.autoparts.client.service;


import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.json.JSONObject;

@Service
public class LLMService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String LLM_API_URL = "https://api.openai.com/v1/completions";
    //noman_azeem@yahoo.com account
    private static final String API_KEY = "sk-None-KeYj0MYUvbH0WKhaCwr0T3BlbkFJ3KlyRoA9nqn1ML7YU6Fg";

    public String interpretCommand(String text) throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo");  // "gpt-3.5-turbo" or "gpt-4" if available
        requestBody.put("prompt", "Interpret the following command: " + text);
        requestBody.put("max_tokens", 50);

        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);
        ResponseEntity<String> response = restTemplate.exchange(LLM_API_URL, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}