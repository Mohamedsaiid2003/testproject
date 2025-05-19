package com.example.TibaCare.Controller;

import com.example.TibaCare.pharmacy.Pharmacy;
import com.example.TibaCare.pharmacy.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/pharmacy")
public class PharmacyController {


    private PharmacyService pharmacyService;
    @Autowired
    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping
    public List<Pharmacy> getPharmacy(){
        return pharmacyService.getpharmacy();
    }
    @PostMapping(path = "/addpharmacy")
    public void registerNewPharmacy(@RequestBody Pharmacy pharmacy){
        pharmacyService.addNewPharmacy(pharmacy);
    }
    @DeleteMapping(path = "{deletepharmacy}")
    public void deletePharmacy(@RequestBody int pharmacyId){
        pharmacyService.deletepharmacy(pharmacyId);
    }
    @PutMapping(path = "{updatepharmacy}")
    public void updatePharmacy(@RequestBody int pharmacyId , @RequestBody String medicinename ){
        pharmacyService.updatepharmacy(pharmacyId,medicinename,null);
    }
}
