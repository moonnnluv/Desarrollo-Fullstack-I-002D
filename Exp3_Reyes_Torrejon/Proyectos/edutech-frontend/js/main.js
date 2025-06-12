function mostrar(tipo) {
  let archivo = "";
  if (tipo === "cursos") archivo = "cursos.js";
  if (tipo === "usuarios") archivo = "usuarios.js";
  if (tipo === "evaluaciones") archivo = "evaluaciones.js";
  
  const script = document.createElement("script");
  script.src = archivo + "?t=" + new Date().getTime(); // evitar caché
  document.body.appendChild(script);
}
