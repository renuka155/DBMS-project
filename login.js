const API_URL = "http://localhost:8082";

document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch(`${API_URL}/users`);

        if (!response.ok) {
            throw new Error("Unable to connect");
        }

        const users = await response.json();

        const user = users.find(
            u => u.email === email && u.password === password
        );

        if (user) {
            alert("Login successful! Welcome " + user.name);
            window.location.href = "index.html";
        } else {
            alert("Invalid email or password. Please register first.");
        }

    } catch (error) {
        alert("Unable to connect to server.");
        console.error(error);
    }
});