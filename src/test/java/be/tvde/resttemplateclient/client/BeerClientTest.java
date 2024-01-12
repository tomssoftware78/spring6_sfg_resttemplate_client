package be.tvde.resttemplateclient.client;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import be.tvde.resttemplateclient.model.BeerDto;

@SpringBootTest
class BeerClientTest {

   @Autowired
   private BeerClient beerClient;

   @Test
   void listBeers() {
      final Page<BeerDto> beerDtos = beerClient.listBeers();
      System.out.println(beerDtos.getTotalElements());
      System.out.println(beerDtos.getContent().size());
//      assertThat(beerDtos).isNotNull();
//      assertThat(beerDtos.getContent().size()).isEqualTo(3);
   }
}