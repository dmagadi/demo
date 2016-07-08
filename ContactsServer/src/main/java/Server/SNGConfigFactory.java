package Server;

import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.config.ConfigFactory;
import org.pac4j.core.matching.ExcludedPathMatcher;
import org.pac4j.http.client.FormClient;
import org.pac4j.http.client.direct.DirectBasicAuthClient;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.http.client.indirect.IndirectBasicAuthClient;
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.sparkjava.DefaultHttpActionAdapter;
import spark.TemplateEngine;

public class SNGConfigFactory implements ConfigFactory {

    private final String salt;

    public SNGConfigFactory(final String salt) {
        this.salt = salt;
    }

    @Override
    public Config build() {
        

        
        

        
        // HTTP
        //final IndirectBasicAuthClient indirectBasicAuthClient = new IndirectBasicAuthClient(new SimpleTestUsernamePasswordAuthenticator());

        // REST authent with JWT for a token passed in the url as the token parameter
        ParameterClient parameterClient = new ParameterClient("token", new JwtAuthenticator(salt));
        parameterClient.setSupportGetRequest(true);
        parameterClient.setSupportPostRequest(true);

        // basic auth

        final Clients clients = new Clients(parameterClient);

        final Config config = new Config(clients);
        //config.addAuthorizer("admin", new RequireAnyRoleAuthorizer("ROLE_ADMIN"));
        //config.addAuthorizer("custom", new CustomAuthorizer());
        config.addMatcher("excludedPath", new ExcludedPathMatcher("^/login$"));
        config.setHttpActionAdapter(new DefaultHttpActionAdapter());
        return config;
    }
}
