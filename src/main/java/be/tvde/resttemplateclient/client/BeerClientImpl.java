package be.tvde.resttemplateclient.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import be.tvde.resttemplateclient.model.BeerDto;
import be.tvde.resttemplateclient.model.BeerDtoRestPageImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BeerClientImpl implements BeerClient {

   private static final String GET_BEER_PATH = "/api/v1/beer";

   @Autowired
   private RestTemplateBuilder restTemplateBuilder;

   @Override
   public Page<BeerDto> listBeers() {
      final RestTemplate restTemplate = restTemplateBuilder.build();

      UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

//      final ResponseEntity<String> response = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), String.class);
//      log.debug(response.toString());
//
//      final ResponseEntity<Map> mapResponse = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), Map.class);
//      log.debug(mapResponse.toString());
//
//      final ResponseEntity<JsonNode> jsonResponse = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), JsonNode.class);
//
//      jsonResponse.getBody().findPath("content")
//                  .elements().forEachRemaining(node -> {
//                     System.out.println(node.get("beerName").asText());
//                  });

      final ResponseEntity<BeerDtoRestPageImpl> pageResponseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), BeerDtoRestPageImpl.class);
      pageResponseEntity.getBody().getContent();
      return null;
   }
}
