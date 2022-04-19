package slackIntegration;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class EyesSlack {
    private static final Logger LOG = LoggerFactory.getLogger(EyesSlack.class);
    private static final String urlSlackWebHook = "https://hooks.slack.com/services/T02LRE6FWAY/B03BXLN8XTN" +
            "/q1S7dzpQ4htpLoL1PwWvNoe2";
    private static final String channelName = "slackIntegration";

    public static void sendTestExecutionStatusToSlack(List<String> passedTests, List<String> failedTests,
                                                      List<String> skippedTests) {
        try {
            StringBuilder messageBuilder = createMessageForSending(passedTests, failedTests, skippedTests);
            Payload payload = Payload.builder().channel(channelName).text(messageBuilder.toString()).build();

            LOG.info("urlSlackWebHook is " + urlSlackWebHook);
            LOG.info("channelName is " + channelName);
            WebhookResponse webhookResponse = Slack.getInstance().send(urlSlackWebHook, payload);
            webhookResponse.getMessage();
        } catch (IOException e) {
            LOG.info("Unexpected Error! WebHook:" + urlSlackWebHook);
        }
    }

    private static StringBuilder createMessageForSending(List<String> passedTests, List<String> failedTests,
                                                         List<String> skippedTests) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Passed tests are : \n");
        passedTests.forEach(each -> messageBuilder.append(each).append(StringUtils.SPACE));
        messageBuilder.append("\n");
        messageBuilder.append("Failed tests are : \n");
        failedTests.forEach(each -> messageBuilder.append(each).append(StringUtils.SPACE));
        messageBuilder.append("\n");
        messageBuilder.append("Skipped tests are : \n");
        skippedTests.forEach(each -> messageBuilder.append(each).append(StringUtils.SPACE));
        return messageBuilder;
    }
}
