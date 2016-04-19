package me.geso.genge.plugin.base64;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.content.TextContent;
import lombok.extern.slf4j.Slf4j;
import me.geso.genge.handler.HandlerResponse;
import me.geso.genge.handler.TextContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@Slf4j
@ConditionalOnProperty("genge.base64.enabled")
public class Base64EncodeHandler implements TextContentHandler {
    @Autowired
    private LineBotClient lineBotClient;

    @Override
    public HandlerResponse told(TextContent textContent) throws LineBotAPIException {
        String encoded = Base64.getEncoder()
                .encodeToString(
                        textContent.getText()
                                .getBytes(StandardCharsets.UTF_8));

        log.info("{} => {}", textContent.getText(), encoded);

        lineBotClient.sendText(
                textContent.getFrom(),
                encoded
        );
        return HandlerResponse.OK;
    }
}
