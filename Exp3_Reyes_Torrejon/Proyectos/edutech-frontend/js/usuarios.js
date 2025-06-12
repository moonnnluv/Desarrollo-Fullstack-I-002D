fetch("http://localhost:8083/usuarios")
  .then(res => res.json())
  .then(data => {
    const contenido = document.getElementById("contenido");
    contenido.innerHTML = "<h2>Gestión de Usuarios</h2>";
    if (data.length === 0) {
      contenido.innerHTML += "<p>No hay usuarios registrados.</p>";
    } else {
      const tabla = document.createElement("table");
      tabla.innerHTML = `
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Email</th>
          <th>Rol</th>
        </tr>
      `;
      data.forEach(usuario => {
        tabla.innerHTML += `
          <tr>
            <td>${usuario.id}</td>
            <td>${usuario.nombre}</td>
            <td>${usuario.email}</td>
            <td>${usuario.rol}</td>
          </tr>
        `;
      });
      contenido.appendChild(tabla);
    }
  })
  .catch(err => {
    document.getElementById("contenido").innerHTML = "<p>Error al cargar usuarios.</p>";
    console.error(err);
  });
