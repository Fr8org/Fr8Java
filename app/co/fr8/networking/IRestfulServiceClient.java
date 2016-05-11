package co.fr8.networking;

import org.apache.http.HttpResponse;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by Charles Pretzer on 4/18/2016.
 */
abstract public class IRestfulServiceClient {
    private URI baseURI;

    abstract public Future<Boolean> downloadAsync(URI requestURI, String correlationId, Map<String, String> headers);
    abstract public Future<HttpResponse> getAsync(URI requestURI, String correlationId, Map<String, String> headers);
    abstract public Future<HttpResponse> postAsync(URI requestURI, String correlationId, Map<String, String> headers);
    abstract public Future<HttpResponse> postAsync(URI requestURI, HttpResponse content, String correlationId, Map<String, String> headers);
    abstract public Future deleteAsync(URI requestURI, String correlationId, Map<String, String> headers);
    abstract public Future<HttpResponse> putAsync(URI requestURI, HttpResponse content, String correlationId, Map<String, String> headers);

    public URI getBaseURI() {
        return baseURI;
    }

    public void setBaseURI(URI baseURI) {
        this.baseURI = baseURI;
    }
}
