fetch("http://localhost:8081/cursos")
  .then(res => res.json())
  .then(data => {
    const contenido = document.getElementById("contenido");
    contenido.innerHTML = "<h2>Gestión de Cursos</h2>";
    if (data.length === 0) {
      contenido.innerHTML += "<p>No hay cursos disponibles.</p>";
    } else {
      const tabla = document.createElement("table");
      tabla.innerHTML = `
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Profesor</th>
        </tr>
      `;
      data.forEach(curso => {
        tabla.innerHTML += `
          <tr>
            <td>${curso.id}</td>
            <td>${curso.nombre}</td>
            <td>${curso.profesor}</td>
          </tr>
        `;
      });
      contenido.appendChild(tabla);
    }
  })
  .catch(err => {
    document.getElementById("contenido").innerHTML = "<p>Error al cargar cursos.</p>";
    console.error(err);
  });
