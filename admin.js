const API_URL = "http://localhost:8082";

async function loadClaims() {
    const claimList = document.getElementById("claim-list");

    try {
        const response = await fetch(`${API_URL}/claims`);

        if (!response.ok) {
            throw new Error("Unable to load claims");
        }

        const claims = await response.json();
        document.getElementById("totalClaims").textContent = claims.length;

document.getElementById("pendingClaims").textContent =
    claims.filter(claim => claim.status === "PENDING").length;

document.getElementById("approvedClaims").textContent =
    claims.filter(claim => claim.status === "APPROVED").length;

document.getElementById("rejectedClaims").textContent =
    claims.filter(claim => claim.status === "REJECTED").length;

        claimList.innerHTML = "";

        if (claims.length === 0) {
            claimList.innerHTML = "<p>No claims submitted yet.</p>";
            return;
        }

        claims.forEach(claim => {
            const card = document.createElement("div");
            card.className = "item-card";

            card.innerHTML = `
                <h3>Claim ID: ${claim.claimId}</h3>
                <p><strong>Item ID:</strong> ${claim.itemId}</p>
                <p><strong>Claimant ID:</strong> ${claim.claimantId}</p>
                <p><strong>Description:</strong> ${claim.description || "No description"}</p>
                <p><strong>Status:</strong> ${claim.status}</p>

<button onclick="updateStatus(${claim.claimId}, 'APPROVED')">
    Approve
</button>

<button onclick="updateStatus(${claim.claimId}, 'REJECTED')">
    Reject
</button>
            `;

            claimList.appendChild(card);
        });

    } catch (error) {
        claimList.innerHTML =
            "<p>Unable to load claims. Please check the backend.</p>";

        console.error(error);
    }
}

loadClaims();
async function updateStatus(claimId, status) {
    try {
        const response = await fetch(
            `${API_URL}/claims/${claimId}?status=${status}`,
            {
                method: "PUT"
            }
        );

        if (!response.ok) {
            throw new Error("Failed to update claim");
        }

        alert("Claim status updated to " + status);

        loadClaims();

    } catch (error) {
        alert("Unable to update claim status.");
        console.error(error);
    }
}