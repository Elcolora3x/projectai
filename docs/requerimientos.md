# Especificación de Requerimientos: Sistema de Gestión de Turnos

## 1. Introducción
El objetivo de este sistema es permitir la administración de espacios físicos (**Edificios**) donde se desarrollan actividades (**Eventos**) que requieren una gestión de asistencia o turnos para las personas (**Gente**).

---

## 2. Modelo de Dominio (Jerarquía)
El sistema se basa en una estructura jerárquica de tres niveles de relación uno a muchos ($1:N$):

1.  **Edificio:** Entidad raíz que contiene múltiples eventos.
2.  **Evento:** Actividad programada con horario y cupo perteneciente a un edificio.
3.  **Persona (Gente):** Usuarios suscritos a un evento específico.

---

## 3. Requerimientos Funcionales (RF)

### 3.1 Panel de Administración de Eventos
El administrador debe poder gestionar el ciclo de vida de las actividades:

* **RF1 - Crear Evento:** Definir nombre, descripción, fecha, hora, cupo máximo y asignar el **Edificio** correspondiente.
* **RF2 - Modificar Evento:** Editar detalles de un evento existente (ajuste de horarios o descripción).
* **RF3 - Eliminar Evento:** Baja del evento del sistema (borrado lógico para preservar histórico).
* **RF4 - Notificar Evento:** Enviar una comunicación (push/email) a todos los usuarios suscritos a un evento particular.

### 3.2 Gestión de Usuarios y Suscripciones
* **RF5 - Suscribirse a Evento:** Permitir a una persona registrarse en un evento que tenga cupo disponible.
* **RF6 - Restricción de Unicidad:** El sistema debe validar que un usuario **no pueda suscribirse más de una vez** al mismo evento (evitar duplicados).
* **RF7 - Control de Aforo:** No permitir nuevas suscripciones si se ha alcanzado el límite de "Gente" definido en el evento.

---

## 4. Sugerencias Técnicas y de Negocio

Para darle más robustez a este MVP (Producto Mínimo Viable), se sugieren las siguientes implementaciones:

* **Estados de Cupo:** Implementar un indicador visual (Verde/Amarillo/Rojo) según el porcentaje de ocupación del evento.
* **Cancelación de Suscripción:** Permitir que el usuario libere su lugar si no puede asistir, actualizando el cupo en tiempo real.
* **Validación de Superposición:** Evitar que un mismo **Edificio** tenga dos eventos en el mismo rango horario y salón (si se añade ese nivel de detalle).
* **Historial de Notificaciones:** Registro de qué comunicaciones se enviaron y en qué fecha para auditoría del administrador.

---

## 5. Requerimientos No Funcionales (RNF)
* **Concurrencia:** El proceso de suscripción debe manejar transacciones para evitar que dos personas ocupen el último cupo disponible simultáneamente.
* **Escalabilidad:** La jerarquía debe permitir añadir N edificios sin degradar el rendimiento de la búsqueda de eventos.