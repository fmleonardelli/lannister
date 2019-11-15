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

import javax.validation.Valid;

@RestController
public class ChargesController implements Controller<EventApi>{

    Logger logger = LoggerFactory.getLogger(ChargesController.class);

    @Autowired
    private ChargesService chargesService;

    @PostMapping("charges")
    public ResponseEntity<EventApi> notifyCharge(@Valid @RequestBody EventApi event) throws Throwable {
        logger.info("Notify Charge with params: " + event.toString());
        return convertToResponse(chargesService.notifyCharge(event));
    }

    @GetMapping("charges")
    public ResponseEntity<Paginated<EventApi>> getCharges(@RequestParam(required = false) String event_type,
                                                          @RequestParam(required = false) String event_id,
                                                          @RequestParam(required = false) String user_id,
                                                          @RequestParam(required = false) String date_from,
                                                          @RequestParam(required = false) String date_to,
                                                          @RequestParam(required = false) Integer limit,
                                                          @RequestParam(required = false) Integer offset) throws Throwable {
        val parametersApi = new ChargesParametersApi(
                Option.of(event_type),
                Option.of(event_id),
                Option.of(user_id),
                Option.of(date_from),
                Option.of(date_to),
                Option.of(limit),
                Option.of(offset));
        logger.info("Get charges event api: " + parametersApi.toString());
        return convertToResponsePaginated(chargesService.findCharges(parametersApi));
    }
}
