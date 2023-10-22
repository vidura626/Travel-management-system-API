package lk.ijse.travelservice.api;

import jakarta.validation.Valid;
import lk.ijse.travelservice.dto.PackageInfoDto;
import lk.ijse.travelservice.dto.TravelPackageDto;
import lk.ijse.travelservice.service.PackageService;
import lk.ijse.travelservice.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/packages")
public class PackageController {

    private final PackageService packageService;
    private final TravelPackageService travelPackageService;

    @Autowired
    public PackageController(PackageService packageService, TravelPackageService travelPackageService) {
        this.packageService = packageService;
        this.travelPackageService = travelPackageService;
    }

    //    Create package
    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPackage(@RequestBody @Valid PackageInfoDto packageInfo) {
        String packageId = packageService.createNewPackage(packageInfo);
        return ResponseEntity.ok(packageId);
    }

    //    Update existing package
    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePackage(@RequestBody @Valid PackageInfoDto packageInfo) {
        String packageId = packageService.updateExistingPackage(packageInfo);
        return ResponseEntity.ok(packageId);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<PackageInfoDto>> getAllPackages() {
        List<PackageInfoDto> allPackages = packageService.getAllPackages();
        return ResponseEntity.ok(allPackages);
    }

    @DeleteMapping(path = "/delete/{packageName}")
    public ResponseEntity<String> deletePackage(@PathVariable("packageName") String packageName) {
        String s = packageService.deleteExistingPackage(packageName);
        return ResponseEntity.ok(s);
    }

    @GetMapping(path = "/alltravels/{packageName}")
    public ResponseEntity<List<TravelPackageDto>> getTravelsByPackageName(
            @PathVariable("packageName") String packageName) {
        return ResponseEntity.ok(travelPackageService.findTravelsByPackageName(packageName));
    }

    @GetMapping("/{packageName}")
    public ResponseEntity<PackageInfoDto> getPackageByPackageName(
            @PathVariable("packageName") String packageName) {
        return ResponseEntity.ok(packageService.findPackageByPackageName(packageName));
    }


}
