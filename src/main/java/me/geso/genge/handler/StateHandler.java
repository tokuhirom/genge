package me.geso.genge.handler;

import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.content.TextContent;
import lombok.Getter;
import lombok.Setter;
import me.geso.genge.storage.KeyValueStorage;
import org.springframework.beans.factory.annotation.Autowired;

public class StateHandler implements TextContentHandler {
    @Autowired
    private TextContentHandler handler;
    @Getter
    @Setter
    private String state;
    @Autowired
    private KeyValueStorage<String> kvs;

    @Override
    public HandlerResponse told(TextContent textContent) throws LineBotAPIException {
        String state = kvs.load(getClass().getPackage(),
                textContent.getFrom());
        if (state == null) {
            state = "initial";
        }

        if (state.equals(this.state)) {
            return handler.told(textContent);
        } else {
            return HandlerResponse.DECLINED;
        }
    }
}
