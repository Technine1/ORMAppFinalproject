Платформа онлайн-обучения «EduPlatform»
Учебный проект — полноценная система управления онлайн-курсами, созданная для демонстрации глубоких возможностей Spring Boot, JPA/Hibernate и современных практик разработки бэкенда.
Возможности системы

Роли пользователей: студенты, преподаватели, администраторы
Полноценный каталог курсов с категориями и тегами
Гибкая структура курса: модули → уроки → материалы и задания
Встроенная система тестирования (викторины любого типа)
Механизм записи и отписки студентов от курсов
Проверка домашних заданий с выставлением оценок и комментариями
Отзывы и рейтинг курсов

Архитектурный подход
Приложение построено по классической многослойной схеме:
textController (REST API) 
    ↓
Service (бизнес-логика + @Transactional) 
    ↓
Repository (Spring Data JPA) 
    ↓
Entity (JPA + Hibernate)
Стек технологий

Java 17
Spring Boot 3.2.0
Spring Data JPA + Hibernate
PostgreSQL (в продакшене) / H2 (для быстрого запуска)
Gradle + Wrapper
Testcontainers для интеграционных тестов
Lombok, SLF4J

Модель данных
В проекте реализовано более 17 сущностей с различными видами связей:

User ↔ Profile (OneToOne)
Course ↔ Tag (ManyToMany)
User ↔ Course (ManyToMany через Enrollment)
Course → Module → Lesson → Assignment / Quiz
Quiz → Question → AnswerOption
Submission, QuizSubmission, CourseReview и др.

Все связи используют FetchType.LAZY — специально для показа типичных задач при работе с ORM и способов их решения через DTO, @EntityGraph и Open Session in View.
Как запустить
Требования

JDK 17+
Gradle (или используйте ./gradlew)

Вариант А — самый быстрый (H2 в памяти)
Bash./gradlew bootRun --args='--spring.profiles.active=h2'
→ Приложение запустится мгновенно, база создастся автоматически.
Вариант Б — с PostgreSQL (через Docker Compose)
Bashdocker-compose up -d         
# поднимет PostgreSQL + приложение
# или только базу:
docker-compose up postgres -d
После этого приложение доступно по адресу http://localhost:8080
Сборка и тесты
Bash./gradlew clean build    # сборка + все тесты
./gradlew test           # только тесты
H2-консоль (только при профиле h2)

http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
User: sa / Password: (пусто)

Основные REST-эндпоинты (примеры)
httpPOST   /api/courses                  → создать курс
GET    /api/courses                  → список всех курсов
GET    /api/courses/{id}/with-modules → курс со всеми модулями

POST   /api/enrollments?studentId=1&courseId=1   → записаться
DELETE /api/enrollments?studentId=1&courseId=1   → отписаться

POST   /api/assignments/{id}/submit   → сдать задание
PUT    /api/assignments/submissions/{id}/grade → оценить

POST   /api/quizzes/{quizId}/take     → пройти тест
Демо-данные
При первом запуске автоматически создаются:

администратор, преподаватель и несколько студентов
2–3 готовых курса с модулями, уроками, заданиями и тестами
записи студентов на курсы
примеры решений и оценок

Тестирование
Более 20 тестов разных уровней:

Unit-тесты сервисов с Mockito
Интеграционные тесты репозиториев и контроллеров с Testcontainers
End-to-end сценарии в EducationPlatformIntegrationTest

Покрытие кода > 85 % (JaCoCo + GitHub Actions).
Особенности реализации

Ленивая загрузка везде — специально для демонстрации N+1 и решений
Полная валидация входных данных (@Valid, Bean Validation)
Глобальный обработчик ошибок → красивые JSON-ответы
Все критичные операции в сервисах обернуты в @Transactional
Чистый и понятный код с Javadoc там, где это важно

Структура проекта
textsrc/main/java/ru/vasmarfas/educationplatform/
├─ entity/      → все JPA-сущности
├─ repository/  → интерфейсы Spring Data
├─ service/     → бизнес-логика
├─ controller/  → REST-контроллеры
└─ config/      → конфигурации и обработчики

src/test/java/  → тесты всех уровней
Docker
В корне проекта:

Dockerfile (образ < 130 МБ)
docker-compose.yml (PostgreSQL + приложение)

Bashdocker-compose up -d   # всё готово за 20 секунд
CI/CD
GitHub Actions автоматически:

собирает проект
прогоняет тесты
публикует отчёт JaCoCo

Выполненные требования курсовой работы

17+ сущностей со всеми видами связей
Полноценный REST API
Spring Data JPA репозитории
Интеграционные тесты с Testcontainers
Поддержка PostgreSQL и H2
Ленивая загрузка + решения N+1
Валидация и обработка ошибок
Демо-данные при старте
Docker и docker-compose
Подробный README и документация