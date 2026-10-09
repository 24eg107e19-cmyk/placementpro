
package placementpro_backend.controller;

import placementpro_backend.entity.Application;
import placementpro_backend.repository.ApplicationRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;

    public ApplicationController(
            ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    // Get all applications
    @GetMapping
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // Get application by ID
    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Application not found"));
    }

    // Add a new application
    @PostMapping
    public Application addApplication(
            @RequestBody Application application) {
        return applicationRepository.save(application);
    }

    // Update an application
    @PutMapping("/{id}")
    public Application updateApplication(
            @PathVariable Long id,
            @RequestBody Application updatedApplication) {

        Application application = applicationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Application not found"));

        // Copy fields from updatedApplication here if your
        // Application entity has editable fields.

        return applicationRepository.save(application);
    }

    // Delete an application
    @DeleteMapping("/{id}")
    public Map<String, String> deleteApplication(
            @PathVariable Long id) {

        Application application = applicationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Application not found"));

        applicationRepository.delete(application);

        return Map.of(
                "message", "Application deleted successfully");
    }
}