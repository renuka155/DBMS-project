package LostFoundBackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import LostFoundBackend.model.Claim;
import LostFoundBackend.repository.ClaimRepository;

@CrossOrigin(origins = "*")
@RestController
public class ClaimController {

    private final ClaimRepository claimRepository;

    public ClaimController(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @GetMapping("/claims")
    public List<Claim> getClaims() {
        return claimRepository.getAllClaims();
    }
    @PostMapping("/claims")
public Claim addClaim(@RequestBody Claim claim) {
    return claimRepository.save(claim);
 }
 @PutMapping("/claims/{id}")
public String updateClaimStatus(
        @PathVariable int id,
        @RequestParam String status) {

    claimRepository.updateClaimStatus(id, status);

    return "Claim status updated successfully";
  }
}