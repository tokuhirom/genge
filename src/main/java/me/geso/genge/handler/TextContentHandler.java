package me.geso.genge.handler;

import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.content.TextContent;

public interface TextContentHandler {
    HandlerResponse told(TextContent message) throws LineBotAPIException;
}
