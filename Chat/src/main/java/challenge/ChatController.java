package challenge;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ChatController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/check", method = { RequestMethod.POST })
    public Map<String, String> check() {
        int result = this.jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        if (result != 1) {
            throw new RuntimeException("Unexpected query result");
        }
        return Collections.singletonMap("health", "ok");
    }

    @RequestMapping(value = "/createUser", method = { RequestMethod.POST })
    public Map<String, String> createUser(@RequestParam("username") String username,
                                          @RequestParam("password") String password) {
        Map<String, String> ret = new HashMap<>();
        ret.put("id", "0");
        return ret;
    }

    @RequestMapping(value = "/login", method = { RequestMethod.POST })
    public Map<String, String> login(@RequestParam("username") String username,
                                     @RequestParam("password") String password) {
        Map<String, String> ret = new HashMap<>();
        ret.put("id", "0");
        ret.put("token", "1111");
        return ret;
    }

    @RequestMapping(value = "/sendMessage", method = { RequestMethod.POST })
    public Map<String, String> sendMessage(@RequestParam("sender") Long sender,
                                           @RequestParam("recipient") Long recipient,
                                           @RequestParam("content") Map<String, Object> content) {
        Map<String, String> ret = new HashMap<>();
        ret.put("id", "0");
        ret.put("timestamp", new Date().toString());
        return ret;
    }

    @RequestMapping(value = "/getMessages", method = { RequestMethod.POST })
    public Map<String, Object> getMessages(@RequestParam("recipient") Long recipient,
                                           @RequestParam("start") Integer start,
                                           @RequestParam(name = "limit",
                                                   required = false,
                                                   defaultValue = "100") Integer limit) {
        Map<String, Object> ret = new HashMap<>();
        ArrayList<Map<String, Object>> messages = new ArrayList<>();
        HashMap<String, Object> message = new HashMap<>();
        message.put("timestamp", new Date().toString());
        message.put("sender", 1);
        message.put("recipient", 0);
        HashMap<Object, Object> content = new HashMap<>();
        content.put("type", "string");
        content.put("text", "string");
        message.put("content", content);
        messages.add(message);
        ret.put("messages", messages);
        return ret;
    }

}