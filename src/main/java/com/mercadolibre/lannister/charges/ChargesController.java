package com.mercadolibre.lannister.charges;

import com.mercadolibre.lannister.api.Paginated;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChargesController implements Controller<EventApi>{

    @Autowired
    private ChargesService chargesService;

    @PostMapping("charges")
    public ResponseEntity<EventApi> notifyCharge(@RequestBody EventApi event) {
        return convertToResponse(chargesService.notifyCharge(event));
    }

    @GetMapping("charges")
    public ResponseEntity<Paginated<EventApi>> getAll() throws Throwable {
        return convertToResponsePaginated(chargesService.findAll());
    }
}
