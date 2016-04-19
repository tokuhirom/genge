package me.geso.genge.plugin.time_signal;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;
import me.geso.genge.friend_list.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@ConditionalOnProperty("genge.time_signal.enabled")
public class TimeSignalHandler {
    @Autowired
    private LineBotClient lineBotClient;
    @Autowired
    private FriendRepository friendRepository;

    @Scheduled(cron="0 * * * * *")
    public void run() throws LineBotAPIException {
        Set<String> mids = friendRepository.getMids();
        if (!mids.isEmpty()) {
            lineBotClient.sendText(mids,
                    LocalDateTime.now().toString());
        }
    }
}
