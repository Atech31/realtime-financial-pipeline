# Real-Time Financial Transactions Streaming & Analytics Pipeline

An enterprise-grade real-time financial transaction monitoring and analytics engine built with **Spring Boot**, **WebSockets**, **PostgreSQL**, **Docker**, and an interactive **Streamlit** dashboard.

##  Features
- **Real-Time Streaming:** Background scheduled service generating simulated live stock/crypto trades and broadcasting them instantly via WebSockets (`/topic/transactions`).
- **Relational Persistence:** Automatically saves all incoming transactions to a PostgreSQL database using Spring Data JPA.
- **REST API:** Exposes endpoints to query historical transaction logs (`/api/transactions`).
- **Interactive Dashboard:** Streamlit frontend with live auto-refresh, summary metrics, and visual price trend charts.
- **Containerized Architecture:** Fully dockerized with multi-stage builds and Docker Compose orchestration.

##  Tech Stack
- **Backend:** Java 17, Spring Boot 3.2.3, Spring Web, Spring WebSocket, Spring Data JPA, Maven
- **Database:** PostgreSQL
- **Frontend / Analytics:** Python, Streamlit, Pandas, Requests
- **DevOps:** Docker, Docker Compose, Git & GitHub

##  Quick Start (Docker Compose)
1. Clone the repository:
   ```bash
   git clone [https://github.com/your-username/realtime-financial-pipeline.git](https://github.com/your-username/realtime-financial-pipeline.git)
   cd realtime-financial-pipeline
