package com.mercadolibre.lannister.charges;

import com.mercadolibre.lannister.api.Paginated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChargesController implements Controller<EventApi>{

    Logger logger = LoggerFactory.getLogger(ChargesController.class);

    @Autowired
    private ChargesService chargesService;

    @PostMapping("charges")
    public ResponseEntity<EventApi> notifyCharge(@RequestBody EventApi event) {
        logger.info("Notify Charge with params: " + event.toString());
        return convertToResponse(chargesService.notifyCharge(event));
    }

    @GetMapping("charges")
    public ResponseEntity<Paginated<EventApi>> getAll() throws Throwable {
        logger.info("Get All event api");
        return convertToResponsePaginated(chargesService.findAll());
    }
}
