package nazeem.autoparts.client.controller;

import nazeem.autoparts.client.service.LLMService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpeechController {

    @Autowired
    private LLMService llmService;

    @GetMapping("/interpret-command")
    public String interpretCommand(@RequestParam String command) throws JSONException {
        return llmService.interpretCommand(command);
    }
}