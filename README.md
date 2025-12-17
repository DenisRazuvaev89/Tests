# UI Automation Tests

Проект автоматизированного тестирования для веб-приложения и мобильного приложения Wikipedia.

## Содержание

- [Описание проекта](#описание-проекта)
- [Технологический стек](#технологический-стек)
- [Структура проекта](#структура-проекта)
- [Требования к окружению](#требования-к-окружению)
- [Установка и настройка](#установка-и-настройка)
- [Запуск тестов](#запуск-тестов)
- [Тестовые сценарии](#тестовые-сценарии)

## Описание проекта

Проект содержит автоматизированные UI тесты для:
1. **Веб-приложение SauceDemo** - демонстрационный интернет-магазин
2. **Мобильное приложение Wikipedia** - Android-приложение

## Технологический стек

- **Язык:** Java 11+
- **Сборка:** Maven
- **Фреймворк тестирования:** TestNG
- **Веб-автоматизация:** Selenium WebDriver 4.15.0
- **Мобильная автоматизация:** Appium Java Client 9.0.0
- **Управление драйверами:** WebDriverManager 5.6.2

## Структура проекта

```
src/test/java/
├── pages/
│   ├── web/                      # Page Object классы для веб-тестов
│   │   ├── BasePage.java
│   │   ├── LoginPage.java
│   │   ├── ProductsPage.java
│   │   ├── ProductDetailPage.java
│   │   ├── CartPage.java
│   │   └── CheckoutPage.java
│   └── mobile/                   # Page Object классы для мобильных тестов
│       ├── BaseMobilePage.java
│       ├── OnboardingPage.java
│       ├── MainPage.java
│       ├── SearchPage.java
│       ├── ArticlePage.java
│       └── SettingsPage.java
├── tests/
│   ├── web/                      # Веб-тесты
│   │   ├── BaseWebTest.java
│   │   ├── LoginTests.java
│   │   ├── ProductsTests.java
│   │   ├── CartTests.java
│   │   └── CheckoutTests.java
│   └── mobile/                   # Мобильные тесты
│       ├── BaseMobileTest.java
│       ├── SearchTests.java
│       ├── ArticleTests.java
│       └── NavigationTests.java
└── utils/
    ├── ConfigReader.java         # Чтение конфигурации
    ├── DriverFactory.java        # Создание WebDriver
    └── AppiumDriverFactory.java  # Создание AppiumDriver

src/test/resources/
└── config.properties             # Файл конфигурации

testng.xml                        # Конфигурация всех тестов
testng-web.xml                    # Конфигурация веб-тестов
testng-mobile.xml                 # Конфигурация мобильных тестов
```

## Требования к окружению

### Общие требования
- JDK 11 или выше
- Maven 3.6+
- Git

### Для веб-тестов
- Google Chrome (рекомендуется последняя версия)
- Или Firefox / Edge

### Для мобильных тестов
- Node.js 16+
- Appium Server 2.x
- Android SDK
- Android Emulator или реальное устройство
- APK приложения Wikipedia

## Установка и настройка

### 1. Клонирование репозитория

```bash
git clone <repository-url>
cd TestirovaniePril
```

### 2. Установка зависимостей

```bash
mvn clean install -DskipTests
```

### 3. Настройка веб-тестов

Веб-тесты готовы к запуску без дополнительной настройки. WebDriverManager автоматически загрузит нужный драйвер.

Для изменения браузера отредактируйте `src/test/resources/config.properties`:

```properties
web.browser=chrome    # или firefox, edge
```

### 4. Настройка мобильных тестов

#### Установка Appium Server

```bash
npm install -g appium
appium driver install uiautomator2
```

#### Настройка Android эмулятора

1. Установите Android Studio
2. Создайте эмулятор через AVD Manager (рекомендуется Android 13.0)
3. Запустите эмулятор

#### Установка Wikipedia APK

Скачайте APK с [официального источника](https://www.apkmirror.com/apk/wikimedia-foundation/wikipedia/) или из Google Play.

#### Конфигурация Appium

Отредактируйте `src/test/resources/config.properties`:

```properties
appium.server.url=http://127.0.0.1:4723
appium.platform.name=Android
appium.platform.version=13.0
appium.device.name=emulator-5554
appium.app.package=org.wikipedia
appium.app.activity=org.wikipedia.main.MainActivity
```

Для использования APK файла укажите путь:

```properties
appium.app.path=/path/to/wikipedia.apk
```

## Запуск тестов

### Запуск всех тестов

```bash
mvn test
```

### Запуск только веб-тестов

```bash
mvn test -Pweb
```

Или через TestNG XML:

```bash
mvn test -DsuiteXmlFile=testng-web.xml
```

### Запуск только мобильных тестов

1. Запустите Appium Server:
```bash
appium
```

2. Запустите эмулятор Android

3. Выполните тесты:
```bash
mvn test -Pmobile
```

Или через TestNG XML:

```bash
mvn test -DsuiteXmlFile=testng-mobile.xml
```

### Запуск конкретного тестового класса

```bash
mvn test -Dtest=LoginTests
mvn test -Dtest=SearchTests
```

### Запуск конкретного теста

```bash
mvn test -Dtest=LoginTests#testSuccessfulLogin
```

## Тестовые сценарии

### Веб-тесты (SauceDemo)

#### LoginTests (6 тестов)
- Успешная авторизация
- Авторизация с неверным паролем
- Авторизация заблокированного пользователя
- Авторизация с пустыми данными
- Проверка элементов страницы авторизации
- Выход из системы

#### ProductsTests (8 тестов)
- Отображение списка товаров
- Сортировка по имени A-Z
- Сортировка по имени Z-A
- Сортировка по цене (возрастание)
- Сортировка по цене (убывание)
- Открытие карточки товара
- Навигация назад из карточки товара
- Добавление товара в корзину из карточки

#### CartTests (7 тестов)
- Добавление одного товара в корзину
- Добавление нескольких товаров
- Удаление товара из корзины
- Отображение товаров в корзине
- Удаление товара на странице корзины
- Продолжение покупок
- Сохранение корзины при навигации

#### CheckoutTests (9 тестов)
- Отображение страницы оформления
- Успешное оформление заказа
- Валидация пустого имени
- Валидация пустой фамилии
- Валидация пустого индекса
- Отмена оформления заказа
- Проверка расчета цены
- Возврат на главную после заказа
- Параметризованный тест с разными данными

### Мобильные тесты (Wikipedia)

#### SearchTests (7 тестов)
- Поиск статьи по ключевому слову
- Открытие результата поиска
- Проверка заголовка статьи
- Поиск без результатов
- Очистка поискового запроса
- Множественные результаты поиска
- Параметризованный тест с разными запросами

#### ArticleTests (8 тестов)
- Отображение панели инструментов статьи
- Наличие заголовка статьи
- Прокрутка статьи
- Навигация назад из статьи
- Отображение кнопки сохранения
- Отображение кнопки языка
- Открытие оглавления
- Прокрутка до конца статьи

#### NavigationTests (7 тестов)
- Отображение главной страницы
- Отображение панели навигации
- Переход в раздел Explore
- Переход в раздел Saved
- Переход в раздел Search
- Прокрутка главной страницы
- Кликабельность поля поиска

## Отчеты

После выполнения тестов отчет TestNG доступен в:

```
target/surefire-reports/index.html
```

## Примечания

- Веб-тесты проверены на Chrome, Firefox
- Мобильные тесты проверены на Android 13.0 эмуляторе
- При первом запуске WebDriverManager автоматически загрузит драйвер браузера
- Для стабильной работы мобильных тестов рекомендуется использовать эмулятор с достаточным объемом RAM

## Автор

Разуваев Денис Русланович
