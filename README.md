# JUnit Jupiter Annotations Helper Plugin 🚀

Welcome to the **JUnit Jupiter Annotations Helper Plugin** for IntelliJ IDEA! This plugin helps you keep your JUnit tests clean and
consistent with two powerful code inspections. Built with ❤️ by [andreynaz4renko](https://github.com/andreynaz4renko).

---

## Features ✨

### 1. Disabled Test Inspection 🛑

- **What it does**: Detects mismatches between the `@Disabled` annotation and the `@DisplayName` prefix in JUnit tests.
- **Why it’s cool**: Ensures your disabled tests follow a naming convention (e.g., `"[Disabled]: ..."`) that you can
  customize!
- **Examples**:
  ```java
  @Test
  @Disabled
  @DisplayName("[Disabled] Should work")
  public void test() {} // All is good
  ```
  ```java
  @Test
  @DisplayName("[Disabled] Shouldn't work")
  public void test() {} // Oops, missing @Disabled!
  ```

### 2. Test Class Inspection 🧪

- **What it does**: Checks that JUnit lifecycle methods (`@BeforeAll`, `@AfterEach`, etc.) match their expected names.
- **Why it’s cool**: Keeps your setup and teardown methods consistent across your project, with names you define.
- **Examples**:
  ```java
  @BeforeAll
  public static void beforeAll() {} // All is good
  ```
  ```java
  @BeforeAll
  public static void setup() {} // Should be "beforeAll"!
  ```

### Installation 📦

1. From [Releases page](https://github.com/andreynaz4renko)
    1. Download latest `.zip`
    2. Open in IntelliJ IDEA and install the plugin manually via `.zip`.
2. From source
    1. Clone this repo: `git clone https://github.com/andreynaz4renko/junit-jupiter-annotations-helper` 📥
    2. Open in IntelliJ IDEA, build with Gradle, and install the plugin manually via `.zip`.

### Configuration ⚙️

- Open `Settings > Tools > JUnit Jupiter Annotations Helper` in IntelliJ IDEA.
- Customize prefixes and method names to fit your team’s style!

### Usage 🖥️

- Write your JUnit tests as usual.
- The plugin will highlight issues in real-time with quick-fix suggestions! 🔍
- Adjust settings if you want to tweak the rules.

### Contributing 🤝

Love the plugin? Want to make it better? Contributions are welcome! 🌟

1. Fork this repo 🍴
2. Create a feature branch (`git checkout -b feature/awesome-idea`)
3. Commit your changes (`git commit -m "feat: add something cool"`)
4. Push to your branch (`git push origin feature/awesome-idea`)
5. Open a Pull Request! 🙌

### Issues & Feedback 🐛

Found a bug? Have a suggestion?
Please [open an issue](https://github.com/andreynaz4renko/junit-jupiter-annotations-helper/issues/new) and let me know!
I,m all ears. 👂


