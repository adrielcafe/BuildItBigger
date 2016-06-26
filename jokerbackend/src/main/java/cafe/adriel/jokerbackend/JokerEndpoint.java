package cafe.adriel.jokerbackend;

import com.chuckjokes.Jokes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

@Api(
  name = "jokerApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "jokerbackend.adriel.cafe",
    ownerName = "jokerbackend.adriel.cafe",
    packagePath=""
  )
)
public class JokerEndpoint {

    @ApiMethod(name = "getJoke")
    public JokerBean getJoke() {
        JokerBean response = new JokerBean();
        response.setJoke(Jokes.getRandomJoke());
        return response;
    }

}
