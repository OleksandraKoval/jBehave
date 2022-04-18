package slackIntegration;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

public class EyesSlack {

    private static final String urlSlackWebHook = "https://hooks.slack.com/services/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";
    private static final String channelName = "slackIntegration";

    public static void sendTestExecutionStatusToSlack(List<String> passedTests, List<String> failedTests,
                                                      List<String> skippedTests) throws Exception {
        try {
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("Passed tests are : \n");
            passedTests.forEach(each -> messageBuilder.append(each).append(StringUtils.SPACE));
            messageBuilder.append("\n");
            messageBuilder.append("Failed tests are : \n");
            failedTests.forEach(each -> messageBuilder.append(each).append(StringUtils.SPACE));
            messageBuilder.append("\n");
            messageBuilder.append("Skipped tests are : \n");
            skippedTests.forEach(each -> messageBuilder.append(each).append(StringUtils.SPACE));
            Payload payload = Payload.builder().channel(channelName).text(messageBuilder.toString()).build();

            System.out.println("urlSlackWebHook is "+urlSlackWebHook);
            System.out.println("channelName is "+channelName);
            WebhookResponse webhookResponse = Slack.getInstance().send(urlSlackWebHook, payload);
            webhookResponse.getMessage();
        } catch (IOException e) {
            System.out.println("Unexpected Error! WebHook:" + urlSlackWebHook);
        }
    }
}
