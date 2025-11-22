function handleUnauthorized(res) {
    if (res.status === 401) {
        localStorage.removeItem("jwt");
        window.location.href = "/login";
    }
}

async function loadUtilisateurs() {

    const res = await fetch('/api/utilisateurs', {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("jwt")
        }
    });
    if (!res.ok) handleUnauthorized(res);

    const utilisateurs = await res.json();
    const tbody = document.querySelector("#utilisateurs-table tbody");
    tbody.innerHTML = "";

    utilisateurs.forEach(u => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${u.id}</td>
            <td>${u.nom}</td>
            <td>${u.prenom}</td>
            <td>${u.email}</td>
            <td>${u.tel ?? ''}</td>
            <td>${u.dateNaissance ?? ''}</td>
            <td>${u.role.nom ?? ''}</td>
            <td>${u.adresse.num ?? ''}</td>
            <td>${u.adresse.rue ?? ''}</td>
            <td>${u.adresse.ville ?? ''}</td>
            <td>${u.adresse.codePostal ?? ''}</td>
            <td>${u.adresse.pays ?? ''}</td>

            <td>
                <button onclick="createEditUtilisateurSection(${u.id})">Modifier</button>
                <button onclick="deleteUtilisateur(${u.id})">Supprimer</button>
            </td>`;
        tbody.appendChild(tr);
    });
}

async function loadRolesAndAdresses(prefix) {
    const token = localStorage.getItem("jwt");

    const [rolesRes, adressesRes] = await Promise.all([
        fetch('/api/roles', {
            headers: {
                "Authorization": "Bearer " + token
            }
        }),
        fetch('/api/adresses', {
            headers: {
                "Authorization": "Bearer " + token
            }
        })
    ]);

    const roles = await rolesRes.json();
    const adresses = await adressesRes.json();

    const roleSelect = document.querySelector(`#${prefix}-role`);
    const adresseSelect = document.querySelector(`#${prefix}-adresse`);

    roleSelect.innerHTML = roles.map(r => `<option value="${r.id}">${r.nom}</option>`).join('');
    adresseSelect.innerHTML = adresses
        .map(a => `<option value="${a.id}">${a.num ?? ''} ${a.rue ?? ''}, ${a.ville ?? ''}</option>`)
        .join('');
}

async function createUtilisateur() {
    let idAdresse = document.querySelector("#create-adresse").value;
    const newAddressVisible = document.querySelector("#create-new-address").style.display === 'block';

    if (newAddressVisible) {
        const adresse = {
            num: document.querySelector("#create-num").value,
            rue: document.querySelector("#create-rue").value,
            ville: document.querySelector("#create-ville").value,
            codePostal: document.querySelector("#create-codePostal").value,
            pays: document.querySelector("#create-pays").value
        };

        const resAddr = await fetch('/api/adresses', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + localStorage.getItem("jwt")
            },
            body: JSON.stringify(adresse)
        });

        const newAddr = await resAddr.json();
        idAdresse = newAddr.id;
    }

    const dto = {
        nom: document.querySelector("#create-nom").value,
        prenom: document.querySelector("#create-prenom").value,
        email: document.querySelector("#create-email").value,
        password: document.querySelector("#create-password").value,
        tel: document.querySelector("#create-tel").value,
        dateNaissance: document.querySelector("#create-dateNaissance").value,
        idRole: parseInt(document.querySelector("#create-role").value),
        idAdresse: parseInt(idAdresse)
    };

    const res = await fetch('/api/utilisateurs', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': "Bearer " + localStorage.getItem("jwt")
        },
        body: JSON.stringify(dto)
    });

    if (res.ok) {
        alert("Utilisateur créé !");
        document.querySelector("#create-section").style.display = 'none';
        await loadUtilisateurs();
    } else {
        alert("Erreur !");
        handleUnauthorized(res);
    }
}

async function updateUtilisateur() {
    const id = document.querySelector("#edit-id").value;
    let idAdresse = document.querySelector("#edit-adresse").value;
    const newAddressVisible = document.querySelector("#edit-new-address").style.display === 'block';

    if (newAddressVisible) {
        const adresse = {
            num: document.querySelector("#edit-num").value,
            rue: document.querySelector("#edit-rue").value,
            ville: document.querySelector("#edit-ville").value,
            codePostal: document.querySelector("#edit-codePostal").value,
            pays: document.querySelector("#edit-pays").value
        };

        const resAddr = await fetch('/api/adresses', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + localStorage.getItem("jwt")
            },
            body: JSON.stringify(adresse)
        });

        const newAddr = await resAddr.json();
        idAdresse = newAddr.id;
    }

    const dto = {
        nom: document.querySelector("#edit-nom").value,
        prenom: document.querySelector("#edit-prenom").value,
        email: document.querySelector("#edit-email").value,
        password: document.querySelector("#edit-password").value,
        tel: document.querySelector("#edit-tel").value,
        dateNaissance: document.querySelector("#edit-dateNaissance").value,
        idRole: parseInt(document.querySelector("#edit-role").value),
        idAdresse: parseInt(idAdresse)
    };

    await fetch(`/api/utilisateurs/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': "Bearer " + localStorage.getItem("jwt")
        },
        body: JSON.stringify(dto)
    });

    document.querySelector("#edit-section").style.display = 'none';
    await loadUtilisateurs();
}

async function deleteUtilisateur(id) {
    if (confirm("Supprimer cet utilisateur ?")) {
        await fetch(`/api/utilisateurs/${id}`, {
            method: 'DELETE',
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("jwt")
            }
        });
        await loadUtilisateurs();
    }
}

function toggleNewAddress(prefix) {
    const div = document.querySelector(`#${prefix}-new-address`);
    div.style.display = div.style.display === 'none' ? 'block' : 'none';
}

async function showCreateForm() {
    await loadRolesAndAdresses('create');
    document.querySelector("#create-section").style.display = 'block';
}

async function createEditUtilisateurSection(id) {
    const res = await fetch(`/api/utilisateurs/${id}`, {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("jwt")
        }
    });
    if (!res.ok) handleUnauthorized(res);

    const u = await res.json()

    await loadRolesAndAdresses('edit');

    document.querySelector("#edit-id").value = u.id;
    document.querySelector("#edit-nom").value = u.nom;
    document.querySelector("#edit-prenom").value = u.prenom;
    document.querySelector("#edit-email").value = u.email;
    document.querySelector("#edit-tel").value = u.tel ?? '';
    document.querySelector("#edit-password").value = u.password ?? '';
    document.querySelector("#edit-dateNaissance").value = u.dateNaissance ?? '';
    document.querySelector("#edit-role").value = u.role?.id ?? '';
    document.querySelector("#edit-adresse").value = u.adresse?.id ?? '';

    document.querySelector("#edit-section").style.display = 'block';
}

document.addEventListener("DOMContentLoaded", loadUtilisateurs);