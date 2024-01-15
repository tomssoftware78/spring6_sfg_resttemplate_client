package be.tvde.resttemplateclient.client;

import java.util.UUID;
import org.springframework.data.domain.Page;
import be.tvde.resttemplateclient.model.BeerDto;
import be.tvde.resttemplateclient.model.BeerStyle;

public interface BeerClient {

   void deleteBeer(UUID beerId);

   BeerDto getBeerById(UUID beerId);

   Page<BeerDto> listBeers();

   Page<BeerDto> listBeers(final String beerName, final BeerStyle beerStyle, final Boolean showInventory, final Integer pageNumber, final Integer pageSize);

   BeerDto createBeer(BeerDto beerDto);

   BeerDto updateBeer(BeerDto beerDto);
}
