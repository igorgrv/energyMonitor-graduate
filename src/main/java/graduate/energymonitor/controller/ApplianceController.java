
package graduate.energymonitor.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graduate.energymonitor.controller.dto.ApplianceDto;
import graduate.energymonitor.entity.Appliance;
import graduate.energymonitor.service.ApplianceService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/appliance")
public class ApplianceController {

    @Autowired
    private ApplianceService service;

    @GetMapping("/findAll")
    public ResponseEntity<Set<Appliance>> getAllAppliances() {
        Set<Appliance> appliances = service.findAll();
        return ResponseEntity.ok().body(appliances);
    }

    @GetMapping("/findBy/name/{name}")
    public ResponseEntity<Appliance> getApplianceByAddress(@PathVariable String name) {
        Appliance appliance = service.findByName(name);
        return ResponseEntity.ok().body(appliance);
    }

    @PostMapping("/create")
    public ResponseEntity<Appliance> createAppliance(@Valid @RequestBody ApplianceDto request) {
        Appliance appliance = service.add(request);
        return ResponseEntity.ok().body(appliance);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAppliance(@Valid @RequestBody ApplianceDto request) {
        service.delete(request);
        return ResponseEntity.ok().body("Appliance was deleted");
    }
}
