package com.mercadolibre.lannister.charges;

import com.mercadolibre.lannister.charges.model.ChargeNotification;
import io.vavr.collection.List;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChargesController {

    @Autowired
    private ChargesService chargesService;

    @PostMapping("charges")
    public String notifyCharge(@RequestBody Event event) {
        val res = chargesService.notifyCharge(event);
        if (res.isRight()) {
            return "OK";
        } else {
            return res.getLeft().getMessage();
        }
    }

    @GetMapping("charges")
    public List<ChargeNotification> getAll() throws Throwable {
        val res = chargesService.findAll();
        if (res.isRight()) return res.get();
        else throw res.getLeft();
    }
}
