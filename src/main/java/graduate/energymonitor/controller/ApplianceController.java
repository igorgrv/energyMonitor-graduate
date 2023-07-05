
package graduate.energymonitor.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import graduate.energymonitor.controller.dto.ApplianceDto;
import graduate.energymonitor.entity.Appliance;
import graduate.energymonitor.service.ApplianceService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/appliances")
public class ApplianceController {

    @Autowired
    private ApplianceService applianceService;

    @GetMapping
    public ResponseEntity<Set<Appliance>> getAllAppliances() {
        Set<Appliance> appliances = applianceService.findAll();
        return ResponseEntity.ok().body(appliances);
    }

    @GetMapping("/{id_appliance}")
    public ResponseEntity getApplianceById(@PathVariable("id_appliance") Integer idAppliance){
        Optional<Appliance> appliances = applianceService.findById(idAppliance);

        if (appliances.isEmpty()){
            return ResponseEntity.badRequest().body("Appliance not found");
        }

        return ResponseEntity.ok().body(appliances);
    }

    @PostMapping
    public ResponseEntity<Appliance> createAppliance(@Valid @RequestBody ApplianceDto request) {
        Appliance appliance = applianceService.addAppliance(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(appliance);
    }

    @PutMapping("/{id_appliance}")
    public ResponseEntity updateAppliance(@PathVariable("id_appliance") Integer idAppliance, @Valid @RequestBody ApplianceDto request) {

        Optional<Appliance> appliance = applianceService.findById(idAppliance);

        if (appliance.isEmpty()) {
            return ResponseEntity.badRequest().body("Appliance not found");
        }

        applianceService.updateAppliance(appliance.get(), request);

        return ResponseEntity.ok().body(request);

    }

    @DeleteMapping("/{id_appliance}")
    public ResponseEntity<String> deleteAppliance(@PathVariable("id_appliance") Integer idAppliance) {
        Optional<Appliance> appliance = applianceService.findById(idAppliance);

        if (appliance.isEmpty()) {
            return ResponseEntity.badRequest().body("Appliance not found");
        }
        applianceService.deleteAppliance(appliance.get());
        return ResponseEntity.ok().body("Appliance was deleted");
    }
}
