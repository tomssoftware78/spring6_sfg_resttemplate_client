package be.tvde.resttemplateclient.client;

import java.net.URI;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import be.tvde.resttemplateclient.model.BeerDto;
import be.tvde.resttemplateclient.model.BeerDtoRestPageImpl;
import be.tvde.resttemplateclient.model.BeerStyle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BeerClientImpl implements BeerClient {

   private static final String GET_BEER_PATH = "/api/v1/beer";
   private static final String GET_BEER_BY_ID_PATH = "/api/v1/beer/{beerId}";

   @Autowired
   private RestTemplateBuilder restTemplateBuilder;

   @Override
   public void deleteBeer(final UUID beerId) {
      RestTemplate restTemplate = restTemplateBuilder.build();

      restTemplate.delete(GET_BEER_BY_ID_PATH, beerId);
   }

   @Override
   public BeerDto getBeerById(final UUID beerId) {
      RestTemplate restTemplate = restTemplateBuilder.build();
      return restTemplate.getForObject(GET_BEER_BY_ID_PATH, BeerDto.class, beerId);
   }

   @Override
   public Page<BeerDto> listBeers() {
      return this.listBeers(null, null, null, null, null);
   }

   @Override
   public Page<BeerDto> listBeers(final String beerName, final BeerStyle beerStyle, final Boolean showInventory, final Integer pageNumber, final Integer pageSize) {
      final RestTemplate restTemplate = restTemplateBuilder.build();

      UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

      if (beerName != null) {
         uriComponentsBuilder.queryParam("beerName", beerName);
      }

      if (beerStyle != null) {
         uriComponentsBuilder.queryParam("beerStyle", beerStyle);
      }

      if (showInventory != null) {
         uriComponentsBuilder.queryParam("showInventory", showInventory);
      }

      if (pageNumber != null) {
         uriComponentsBuilder.queryParam("pageNumber", pageNumber);
      }

      if (pageSize != null) {
         uriComponentsBuilder.queryParam("pageSize", pageSize);
      }

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
      return pageResponseEntity.getBody();
   }

   @Override
   public BeerDto createBeer(final BeerDto beerDto) {
      RestTemplate restTemplate = restTemplateBuilder.build();

      //final ResponseEntity<BeerDto> response = restTemplate.postForEntity(GET_BEER_PATH, beerDto, BeerDto.class);
      URI uri = restTemplate.postForLocation(GET_BEER_PATH, beerDto);
      return restTemplate.getForObject(uri.getPath(), BeerDto.class);
   }

   @Override
   public BeerDto updateBeer(final BeerDto beerDto) {
      RestTemplate restTemplate = restTemplateBuilder.build();

      restTemplate.put(GET_BEER_BY_ID_PATH, beerDto, beerDto.getId());
      return getBeerById(beerDto.getId());
   }
}
