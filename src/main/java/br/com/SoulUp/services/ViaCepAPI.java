package br.com.SoulUp.services;



import br.com.SoulUp.api.Endereco;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ViaCepAPI {

    public Endereco getEndereco(String cep) {
        Endereco endereco = null;
        try {

            HttpGet get = new HttpGet("https://viacep.com.br/ws/" + cep + "/json/");

            CloseableHttpClient client = HttpClientBuilder.create().disableRedirectHandling().build();

            CloseableHttpResponse response = client.execute(get);

            HttpEntity entity = response.getEntity();

            if (entity != null) {


                String result = EntityUtils.toString(entity);

                Gson gson = new Gson();

                endereco = gson.fromJson(result, Endereco.class);
            }


        } catch (ClientProtocolException e) {
            System.out.println("Erro detectado no client: " + e);
        } catch (IOException e) {
            System.out.println("Erro detectado no response: " + e);
        } catch (Error e) {
            System.out.println("Erro desconhecido detectado: " + e);
        }


        return endereco;

    }
}
