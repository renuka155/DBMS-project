const API_URL = "http://localhost:8082";

document.getElementById("registerForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const user = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        phone: document.getElementById("phone").value,
        role: "USER"
    };

    try {
        const response = await fetch(`${API_URL}/users`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        });

        if (!response.ok) {
            throw new Error("Registration failed");
        }

        alert("Registration successful!");

        document.getElementById("registerForm").reset();

    } catch (error) {
        alert("Unable to register. Please check the backend.");
        console.error(error);
    }
});