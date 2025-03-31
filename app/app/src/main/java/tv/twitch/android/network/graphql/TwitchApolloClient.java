package tv.twitch.android.network.graphql;

import okhttp3.OkHttpClient;


public class TwitchApolloClient {
    private final OkHttpClient injectedClient;

    public TwitchApolloClient(OkHttpClient injectedClient) {
        this.injectedClient = injectedClient;
    }
}
