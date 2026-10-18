package LostFoundBackend.repository;

import LostFoundBackend.model.Claim;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClaimRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClaimRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Claim> getAllClaims() {

        String sql = "SELECT * FROM claims";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Claim claim = new Claim();

            claim.setClaimId(rs.getInt("claim_id"));
            claim.setItemId(rs.getInt("item_id"));
            claim.setClaimantId(rs.getInt("claimant_id"));
            claim.setDescription(rs.getString("description"));
            claim.setStatus(rs.getString("status"));
            claim.setCreatedAt(rs.getString("created_at"));

            return claim;
        });
    }
    public Claim save(Claim claim) {

    String sql = """
            INSERT INTO claims
            (item_id, claimant_id, description, status)
            VALUES (?, ?, ?, ?)
            """;

    jdbcTemplate.update(
            sql,
            claim.getItemId(),
            claim.getClaimantId(),
            claim.getDescription(),
            claim.getStatus() == null ? "PENDING" : claim.getStatus()
    );

    return claim;
  }
 public void updateClaimStatus(int claimId, String status) {
    String sql = "UPDATE claims SET status = ? WHERE claim_id = ?";
    jdbcTemplate.update(sql, status, claimId);
}
}