package lk.ijse.travelservice.api;

import jakarta.validation.Valid;
import lk.ijse.travelservice.dto.PackageDto;
import lk.ijse.travelservice.dto.RequestTravelDto;
import lk.ijse.travelservice.dto.ResponseTravelDto;
import lk.ijse.travelservice.service.PackageService;
import lk.ijse.travelservice.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/packages")
public class PackageController {

    private final PackageService packageService;
    private final TravelService travelService;

    @Autowired
    public PackageController(PackageService packageService, TravelService travelService) {
        this.packageService = packageService;
        this.travelService = travelService;
    }

    //    Create package
    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPackage(@RequestBody @Valid PackageDto packageInfo) {
        String packageId = packageService.createNewPackage(packageInfo);
        return ResponseEntity.ok(packageId);
    }

    //    Update existing package
    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePackage(@RequestBody @Valid PackageDto packageInfo) {
        String packageId = packageService.updateExistingPackage(packageInfo);
        return ResponseEntity.ok(packageId);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<PackageDto>> getAllPackages() {
        List<PackageDto> allPackages = packageService.getAllPackages();
        return ResponseEntity.ok(allPackages);
    }

    @DeleteMapping(path = "/delete/{packageName}")
    public ResponseEntity<String> deletePackage(@PathVariable("packageName") String packageName) {
        String s = packageService.deleteExistingPackage(packageName);
        return ResponseEntity.ok(s);
    }

    @GetMapping(path = "/alltravels/{packageName}")
    public ResponseEntity<List<ResponseTravelDto>> getTravelsByPackageName(
            @PathVariable("packageName") String packageName) {
        return ResponseEntity.ok(travelService.findTravelsByPackageName(packageName));
    }

    @GetMapping("/{packageName}")
    public ResponseEntity<PackageDto> getPackageByPackageName(
            @PathVariable("packageName") String packageName) {
        return ResponseEntity.ok(packageService.findPackageByPackageName(packageName));
    }


}
