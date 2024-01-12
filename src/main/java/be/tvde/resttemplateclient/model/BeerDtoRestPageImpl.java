package be.tvde.resttemplateclient.model;

import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true, value = "pageable")
public class BeerDtoRestPageImpl<BeerDto> extends PageImpl<be.tvde.resttemplateclient.model.BeerDto> {

   @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
   public BeerDtoRestPageImpl(@JsonProperty("content") final List<be.tvde.resttemplateclient.model.BeerDto> content,
                              @JsonProperty("number") final int page,
                              @JsonProperty("size") final int size,
                              @JsonProperty("totalElements") final long total) {
      super(content, PageRequest.of(page, size), total);
   }

   public BeerDtoRestPageImpl(final List<be.tvde.resttemplateclient.model.BeerDto> content, final Pageable page, final long total) {
      super(content, page, total);
   }

   public BeerDtoRestPageImpl(final List<be.tvde.resttemplateclient.model.BeerDto> content) {
      super(content);
   }
}
