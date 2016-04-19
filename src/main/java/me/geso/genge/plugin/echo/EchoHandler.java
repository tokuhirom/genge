package me.geso.genge.plugin.echo;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.content.TextContent;
import me.geso.genge.handler.HandlerResponse;
import me.geso.genge.handler.TextContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty("genge.echo.enabled")
public class EchoHandler implements TextContentHandler {
    @Autowired
    private LineBotClient lineBotClient;

    @Override
    public HandlerResponse told(TextContent textContent) throws LineBotAPIException {
        lineBotClient.sendText(
                textContent.getFrom(),
                textContent.getText()
        );
        return HandlerResponse.OK;
    }
}
