# Guía de Estilo: Git & Flujo de Trabajo

Este documento define las convenciones para mensajes de commit y la estrategia de ramificación (branching) del proyecto.

---

## 1. Tipos de Commit
Los mensajes deben seguir el formato `tipo: descripción corta`. Ayudan a categorizar el cambio y comunicar la intención.

| Tipo | Descripción |
| :--- | :--- |
| **feat** | Nuevas funcionalidades o mejoras significativas. |
| **fix** | Resolución de errores (bugs). |
| **docs** | Cambios o adiciones en la documentación. |
| **style** | Cambios estéticos/formateo que no afectan la lógica. |
| **refactor** | Mejora de estructura sin añadir funciones ni fijar bugs. |
| **test** | Adición o corrección de pruebas unitarias/integración. |
| **chore** | Tareas rutinarias o actualizaciones del proceso de build. |
| **perf** | Cambios para mejorar el rendimiento. |
| **ci** | Modificaciones en procesos de Integración/Despliegue Continuo. |
| **build** | Cambios en el sistema de construcción o dependencias externas. |
| **revert** | Revertir cambios previos. |

---

## 2. Estrategia de Branching (Git Flow)
Utilizamos **Git Flow** para gestionar las fases del ciclo de vida del software.

### Ramas Principales
* **`master` / `main`**: Código estable y listo para producción.
* **`develop`**: Rama de integración para el desarrollo activo.

### Ramas de Apoyo
* **`feature/`**: Desarrollo de nuevas características. 
    * *Ejemplo:* `feature/gestion-eventos`
* **`release/`**: Preparación de una nueva versión para producción. 
    * *Ejemplo:* `release/1.2.0`
* **`hotfix/`**: Arreglos críticos urgentes para la versión de producción. 
    * *Ejemplo:* `hotfix/error-suscripcion`
* **`bugfix/`**: Corrección de errores específicos (opcional).
    * *Ejemplo:* `bugfix/login-error`
* **`support/`**: Soporte a largo plazo para versiones antiguas.
    * *Ejemplo:* `support/1.x`