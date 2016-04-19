package me.geso.genge.plugin.karma;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.content.TextContent;
import me.geso.genge.handler.HandlerResponse;
import me.geso.genge.handler.TextContentHandler;
import me.geso.genge.storage.KeyValueStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@ConditionalOnProperty("genge.karma.enabled")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class KarmaHandler implements TextContentHandler {
    @Autowired
    private LineBotClient lineBotClient;
    @Autowired
    private KeyValueStorage<Integer> keyValueStorage;

    private static final Pattern PATTERN = Pattern.compile("(\\w+)(\\+\\+|\\-\\-)");

    @Override
    public HandlerResponse told(TextContent textContent) throws LineBotAPIException {
        String text = textContent.getText();
        Matcher matcher = PATTERN.matcher(text);

        if (!matcher.matches()) {
            return HandlerResponse.DECLINED;
        }

        String name = matcher.group(1);
        String type = matcher.group(2);

        Integer retval = keyValueStorage.compute(
                this.getClass().getPackage(),
                name,
                ( key,  current) -> {
                    if (current == null) {
                        return 1;
                    } else {
                        if ("++".equals(type)) {
                            return current + 1;
                        } else {
                            return current - 1;
                        }
                    }
                }
        );

        lineBotClient.sendText(
                textContent.getFrom(),
                String.format("%s: %d", name, retval)
        );
        return HandlerResponse.OK;
    }
}
