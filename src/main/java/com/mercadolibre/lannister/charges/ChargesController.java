package com.mercadolibre.lannister.charges;

import com.mercadolibre.lannister.charges.api.ChargesParametersApi;
import com.mercadolibre.lannister.charges.api.Paginated;
import io.vavr.control.Option;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChargesController implements Controller<EventApi>{

    Logger logger = LoggerFactory.getLogger(ChargesController.class);

    @Autowired
    private ChargesService chargesService;

    @PostMapping("charges")
    public ResponseEntity<EventApi> notifyCharge(@RequestBody EventApi event) throws Throwable {
        logger.info("Notify Charge with params: " + event.toString());
        return convertToResponse(chargesService.notifyCharge(event));
    }

    @GetMapping("charges")
    public ResponseEntity<Paginated<EventApi>> getCharges(@RequestParam(required = false) String eventType,
                                                          @RequestParam(required = false) String eventId,
                                                          @RequestParam(required = false) String user_id,
                                                          @RequestParam(required = false) Integer limit,
                                                          @RequestParam(required = false) Integer offset) throws Throwable {
        val parametersApi = new ChargesParametersApi(Option.of(eventType), Option.of(eventId), Option.of(user_id), Option.of(limit), Option.of(offset));
        logger.info("Get charges event api: " + parametersApi.toString());
        return convertToResponsePaginated(chargesService.findCharges(parametersApi));
    }
}
