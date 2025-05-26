fetch("http://localhost:8082/evaluaciones")
  .then(res => res.json())
  .then(data => {
    const contenido = document.getElementById("contenido");
    contenido.innerHTML = "<h2>Gestión de Evaluaciones</h2>";
    if (data.length === 0) {
      contenido.innerHTML += "<p>No hay evaluaciones registradas.</p>";
    } else {
      const tabla = document.createElement("table");
      tabla.innerHTML = `
        <tr>
          <th>ID</th>
          <th>Estudiante</th>
          <th>Nota 1</th>
          <th>Nota 2</th>
          <th>Nota 3</th>
          <th>Promedio</th>
        </tr>
      `;
      data.forEach(e => {
        tabla.innerHTML += `
          <tr>
            <td>${e.id}</td>
            <td>${e.nombreEstudiante}</td>
            <td>${e.nota1}</td>
            <td>${e.nota2}</td>
            <td>${e.nota3}</td>
            <td>${e.promedio}</td>
          </tr>
        `;
      });
      contenido.appendChild(tabla);
    }
  })
  .catch(err => {
    document.getElementById("contenido").innerHTML = "<p>Error al cargar evaluaciones.</p>";
    console.error(err);
  });
