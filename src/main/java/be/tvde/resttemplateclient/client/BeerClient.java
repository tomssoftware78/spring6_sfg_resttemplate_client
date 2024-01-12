package be.tvde.resttemplateclient.client;

import org.springframework.data.domain.Page;
import be.tvde.resttemplateclient.model.BeerDto;

public interface BeerClient {

   Page<BeerDto> listBeers();
}
