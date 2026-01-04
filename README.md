# easyChannel-backend

Backend service for an omnichannel messaging platform. It exposes REST APIs to manage users, channels, contacts, and message flow while enforcing JWT-based security and role permissions.

**What this backend covers**
- Channel-aware messaging model: `From` (origin), `To` (destination/contact), and `ToType` (channel type).
- Message lifecycle tracking: queued outbound (`MessageToSend`), inbound (`MessageReceived`), and unified history (`Message`).
- Conversation ownership and routing via `User`, `Group`, and `Situation`.
- Operational metrics with `Indice` and `RelUserIndice` for user KPIs.
- Centralized configuration storage via `Config`.
- Consistent API responses using a `Response<T>` wrapper for data and errors.

**Architecture and patterns**
- Layered design: controllers -> services -> repositories -> database.
- Spring Data JPA repositories for persistence, paging, and query methods.
- Stateless security with JWT filters and method-level authorization.
- Cross-origin support with a global CORS filter for browser clients.

**Security model**
- Auth endpoints: `POST /api/auth` and `POST /api/refresh`.
- JWT issued on login and validated per request; sessions are stateless.
- BCrypt password hashing and `@PreAuthorize` role checks (e.g., `ROLE_ADMIN`).
- A default admin user is created on startup (`admin` / `123456`).

**API surface (high level)**
- Authentication: `/api/auth`, `/api/refresh`
- Messaging: `/api/message`, `/api/messageReceived`, `/api/messageToSend`
- Channels and contacts: `/api/from`, `/api/to`, `/api/toType`
- Users and groups: `/api/user`, `/api/group`
- Supporting data: `/api/situation`, `/api/indice`, `/api/relUserIndice`, `/api/config`

**Tech stack**
- Java, Spring Boot
- Spring Web, Spring Data JPA, Spring Security
- JWT (jjwt), BCrypt
- MySQL
- Maven wrapper (`mvnw`)

**Run locally**
1. Create a MySQL database named `easychannel`.
2. Update `src/main/resources/application.properties` with your credentials.
3. Start the service:

```bash
./mvnw spring-boot:run
```

**Public repo hygiene**
- `src/main/resources/application.properties` currently contains real credentials and a JWT secret. Replace with placeholders before pushing to GitHub.
- Consider moving secrets to environment variables or an `application-local.properties` that is gitignored.

**Tests**
- Basic Spring context boot test: `EasychannelApplicationTests`.
