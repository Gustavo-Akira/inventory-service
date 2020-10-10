package com.gustavoakira.service.services;

import com.example.events.NewInventoryEvent;
import com.gustavoakira.service.domain.BeerInventory;
import com.gustavoakira.service.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewInventoryListener {
    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = "new-inventory")
    public void listen(NewInventoryEvent event){
        log.debug("Got inventory " +event.toString());
        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(event.getBeerDTO().getId())
                .upc(event.getBeerDTO().getUpc())
                .quantityOnHand(event.getBeerDTO().getQuantityOnHand())
                .build());
    }
}
