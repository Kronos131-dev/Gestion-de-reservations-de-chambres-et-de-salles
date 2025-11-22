document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("#login-form");
    const errorBox = document.querySelector("#error-box");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const email = document.querySelector("#email").value;
        const password = document.querySelector("#password").value;

        const res = await fetch("/api/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password })
        });

        const data = await res.json();

        if (res.ok && data.token) {
            localStorage.setItem("jwt", data.token);
            localStorage.setItem("user", email);
            window.location.href = "/users";
        } else {
            errorBox.style.display = "block";
            errorBox.textContent = data.message || "Identifiants incorrects";
        }
    });
});