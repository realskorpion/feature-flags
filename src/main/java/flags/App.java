package flags;

import com.launchdarkly.client.LDClient;
import com.launchdarkly.client.LDUser;
import java.io.IOException;
import static java.util.Collections.singletonList;

public class App {

  private static final String SHOW_FEATURE = "Showing your awesome feature to: %s";
  private static final String NO_SHOW_FEATURE = "NOT showing your awesome feature to: %s";
  private static final String SDK_KEY = "sdk-dd4d62dd-d8c9-462d-a7b6-912775f36743";
  private static final String FEATURE_FLAG_NAME = "enable-awesome-feature";

  public static void main(String[] args) throws Exception {
    while(true) {
      System.out.println(showFeature("realskorpion@hotmail.com"));
      System.out.println(showFeature("user@test.com"));
      System.out.println("============================================================");
      Thread.sleep(2000);
    }
  }

  private static final String showFeature(String email) throws IOException {
    return isFeatureEnabled(email) ? String.format(SHOW_FEATURE, email) : String.format(NO_SHOW_FEATURE, email);
  }

  private static boolean isFeatureEnabled(String email) throws IOException {
    try (LDClient client = new LDClient(SDK_KEY)) {
        LDUser user = new LDUser.Builder(email)
                               //.firstName("Bob")
                               //.lastName("Loblaw")
                               //.customString("groups", singletonList("beta_testers"))
                               .build();

        return client.boolVariation(FEATURE_FLAG_NAME, user, false);
    }
  }
}
