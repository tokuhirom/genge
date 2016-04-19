package me.geso.genge.plugin.echo;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.model.content.AbstractContent;
import com.linecorp.bot.model.content.TextContent;
import me.geso.genge.handler.HandlerResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EchoHandlerTest {
    @Mock
    private LineBotClient lineBotClient;
    @InjectMocks
    private EchoHandler underTest;

    @Test
    public void told() throws Exception {
        assertThat(underTest.told(new TextContent("1", "uXXX", AbstractContent.CONTENT_TYPE_TEXT, 1L, "hello")))
                .isEqualTo(HandlerResponse.OK);

        verify(lineBotClient)
                .sendText("uXXX", "hello");
    }
}
