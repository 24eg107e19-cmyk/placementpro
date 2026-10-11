
package placementpro_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import placementpro_backend.entity.Application;
import placementpro_backend.repository.ApplicationRepository;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;

    public ApplicationController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    // Get all applications
    @GetMapping
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // Add a new application
    @PostMapping
    public Application addApplication(@RequestBody Application application) {
        return applicationRepository.save(application);
    }

    // Get an application by ID
    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }

    // Update an application
    @PutMapping("/{id}")
    public Application updateApplication(
            @PathVariable Long id,
            @RequestBody Application updatedApplication) {

        Application existing = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        existing.setStatus(updatedApplication.getStatus());

        return applicationRepository.save(existing);
    }
    @DeleteMapping("/{id}")
    public String deleteApplication(@PathVariable Long id) {
        Application existing = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        applicationRepository.delete(existing);

        return "Application deleted successfully";
    }
}