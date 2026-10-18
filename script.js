const API_URL = "http://localhost:8082";
let allItems = [];
let selectedType = "ALL";

async function loadItems() {
    const itemList = document.getElementById("item-list");

    try {
        const response = await fetch(`${API_URL}/items`);

        if (!response.ok) {
            throw new Error("Unable to load items");
        }

        allItems = await response.json();
        displayItems(allItems);

    } catch (error) {
        itemList.innerHTML =
            "<p>Unable to connect to the server. Please check whether Spring Boot is running.</p>";
        console.error(error);
    }
}

loadItems();
function displayItems(items) {
    const itemList = document.getElementById("item-list");
    itemList.innerHTML = "";

    if (items.length === 0) {
        itemList.innerHTML = "<p>No items found.</p>";
        return;
    }

    items.forEach(item => {
        const card = document.createElement("div");
        card.className = "item-card";

        card.innerHTML = `
            <h3>${item.title}</h3>
            <p><strong>Type:</strong> ${item.type}</p>
            <p><strong>Category:</strong> ${item.category || "Not specified"}</p>
            <p><strong>Location:</strong> ${item.location || "Not specified"}</p>
            <p><strong>Description:</strong> ${item.description || "No description"}</p>
            <p><strong>Status:</strong> ${item.status}</p>
            <button onclick="claimItem(${item.itemId})">
    Claim This Item
</button>
        `;

        itemList.appendChild(card);
    });
}

function filterByType(type) {
    selectedType = type;
    filterItems();
}

function filterItems() {
    const searchText = document.getElementById("searchInput").value.toLowerCase();

    const filteredItems = allItems.filter(item => {
        const matchesType = selectedType === "ALL" || item.type.trim().toUpperCase() === selectedType;

        const matchesSearch =
            item.title.toLowerCase().includes(searchText);

        return matchesType && matchesSearch;
    });

    displayItems(filteredItems);
}
function showForm(type) {
    const form = document.getElementById("report-form");

    form.style.display = "block";

    document.getElementById("itemType").value = type;

    document.getElementById("form-title").textContent =
        type === "LOST" ? "Report a Lost Item" : "Report a Found Item";

    form.scrollIntoView({ behavior: "smooth" });
}

function hideForm() {
    document.getElementById("report-form").style.display = "none";
}
document.getElementById("itemForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const item = {
        userId: 1,
        title: document.getElementById("title").value,
        description: document.getElementById("description").value,
        category: document.getElementById("category").value,
        location: document.getElementById("location").value,
        date: document.getElementById("date").value,
        type: document.getElementById("itemType").value,
        status: "ACTIVE"
    };

    try {
        const response = await fetch(`${API_URL}/items`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(item)
        });

        if (!response.ok) {
            throw new Error("Failed to save item");
        }

        alert("Item reported successfully!");

        document.getElementById("itemForm").reset();
        hideForm();
        loadItems();

    } catch (error) {
        alert("Unable to save item. Please check the backend.");
        console.error(error);
    }
});
async function claimItem(itemId) {
    const description = prompt("Describe how you can identify this item:");

    if (!description || description.trim() === "") {
        alert("Please enter a description.");
        return;
    }

    const claim = {
        itemId: itemId,
        claimantId: 1,
        description: description,
        status: "PENDING"
    };

    try {
        const response = await fetch(`${API_URL}/claims`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(claim)
        });

        if (!response.ok) {
            throw new Error("Failed to submit claim");
        }

        alert("Claim submitted successfully! Status: PENDING");

    } catch (error) {
        alert("Unable to submit claim.");
        console.error(error);
    }
}