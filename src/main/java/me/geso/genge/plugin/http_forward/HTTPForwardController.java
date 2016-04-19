package me.geso.genge.plugin.http_forward;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;
import me.geso.genge.friend_list.FriendRepository;
import me.geso.genge.storage.KeyValueStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@ConditionalOnProperty("genge.http_forward.enabled")
public class HTTPForwardController {
    @Autowired
    private LineBotClient lineBotClient;
    @Autowired
    private FriendRepository friendRepository;

    @RequestMapping(value = "${genge.http_forward.path:/forward}", method = RequestMethod.POST)
    public void forward(@RequestParam("message") String message) throws LineBotAPIException {
        Set<String> mids = friendRepository.getMids();
        lineBotClient.sendText(mids, message);
    }
}
