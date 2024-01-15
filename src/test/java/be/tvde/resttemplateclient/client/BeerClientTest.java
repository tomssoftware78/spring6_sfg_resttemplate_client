package be.tvde.resttemplateclient.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.client.HttpClientErrorException;
import be.tvde.resttemplateclient.model.BeerDto;
import be.tvde.resttemplateclient.model.BeerStyle;

@SpringBootTest
class BeerClientTest {

   @Autowired
   private BeerClient beerClient;

   @Test
   void getBeerById() {
      Page<BeerDto> beerDTOS = beerClient.listBeers();
      BeerDto dto = beerDTOS.getContent().get(0);
      BeerDto byId = beerClient.getBeerById(dto.getId());
      assertNotNull(byId);
   }

   @Test
   void listBeersNoName() {
      final Page<BeerDto> beerDtos = beerClient.listBeers(null, null, null, null, null);

      assertThat(beerDtos).isNotNull();
      assertThat(beerDtos.getContent().size()).isEqualTo(25);
      assertThat(beerDtos.getTotalElements()).isEqualTo(2413);
   }

   @Test
   void listBeers() {
      final Page<BeerDto> beerDtos = beerClient.listBeers("ALE", null, null, null, null);

      assertThat(beerDtos).isNotNull();
      assertThat(beerDtos.getContent().size()).isEqualTo(25);
      assertThat(beerDtos.getTotalElements()).isEqualTo(636);
   }

   @Test
   void createBeer() {
      BeerDto newDto = BeerDto.builder()
                              .price(new BigDecimal(10.99))
                              .beerName("Mango Bobs")
                              .beerStyle(BeerStyle.IPA)
                              .quantityOnHand(500)
                              .upc("123456")
                              .build();

      final BeerDto savedDto = beerClient.createBeer(newDto);
      assertNotNull(savedDto);
   }

   @Test
   void updateBeer() {
      BeerDto newDto = BeerDto.builder()
                              .price(new BigDecimal(10.99))
                              .beerName("Mango Bobs 2")
                              .beerStyle(BeerStyle.IPA)
                              .quantityOnHand(500)
                              .upc("123456")
                              .build();

      final BeerDto beerDto = beerClient.createBeer(newDto);

      final String newName = "Mango Bobs 3";
      beerDto.setBeerName(newName);
      BeerDto updatedBeer = beerClient.updateBeer(beerDto);

      assertEquals(newName, updatedBeer.getBeerName());
   }

   @Test
   void deleteBeer() {
      BeerDto newDto = BeerDto.builder()
                              .price(new BigDecimal(10.99))
                              .beerName("Mango Bobs 2")
                              .beerStyle(BeerStyle.IPA)
                              .quantityOnHand(500)
                              .upc("123456")
                              .build();

      final BeerDto beerDto = beerClient.createBeer(newDto);

      beerClient.deleteBeer(beerDto.getId());

      assertThrows(HttpClientErrorException.class, () -> {

         beerClient.getBeerById(beerDto.getId());
      });
   }
}