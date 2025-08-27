# ✅ Native Android Take-home Assignment — "tasked"

## 🧠 Objective

This assignment is intended to evaluate your understanding of **UI development, local state management, database persistence, and Android app configuration** in **Native Android (Kotlin/Java)**.

You will build a TODO list app based on a provided Figma design, with a focus on **styling, interactions, persistence, and app build configurations**.

---

## 🎨 Design

Please use the following Figma file as the reference for your design:

👉 [Figma Design – tasked](https://www.figma.com/design/snheK7vmaMYoedBuBPACWo/tasked-%E2%80%94-the-to-do-list-app?node-id=0-1&p=f&m=dev)

Aim to match the layout, spacing, font weights, and interaction patterns as closely as possible.

---

## 🚧 Features

The app should allow users to:

- ✅ View a list of TODO items  
- ➕ Add a new TODO item using the input field  
- ✏️ Edit a TODO item’s title  
- ✅ Toggle completion (checked/unchecked)  
- ❌ Delete a TODO item  

---

## 💾 Data Persistence (Required)

- Data must be persisted locally using a **proper database engine** such as:  
  - Room (preferred)  
  - SQLite  
  - Realm  
  - Or any equivalent local database solution  
- **SharedPreferences or simple key-value storage are not acceptable.**  
- Please include a short explanation in your PR describing:
  - Why you chose that database engine  
  - What trade-offs you considered (e.g., performance, scalability, ease of migration)  

---

## 🧠 State Management

- You may use `ViewModel` + `LiveData` / `StateFlow` / `Compose State` (if using Jetpack Compose).  
- Global state management is optional.  
- Please explain in your PR:
  - Why you chose your approach  
  - What benefits it provided in this case  

---

## ⚙️ Build Configuration Requirements

In addition to implementing the TODO app, please configure the project as follows:

### 1. **Build Types**  
- Create **debug** and **release** build types.  
- Configure ProGuard/R8 for release builds.  
- Ensure release builds are **signed** with a placeholder keystore (do not include real keys, just document the process you followed).  

### 2. **Product Flavors / Variants**  
Create **at least 2 variants** of the application with:  
- Different **application IDs / package names**  
- Different **app display names**  
- Different **string resources**  

---

## 📱 Development Guidelines

- You may use **XML layouts or Jetpack Compose** — your choice.  
- Use **Material Design components** where possible.  
- Focus on writing **clean, readable, and maintainable** Kotlin or Java code.  
- MVVM architecture is preferred (but not mandatory).  

---

## 💫 Bonus (Optional)

If you’d like to go the extra mile, consider adding:  
- Animations for list item additions/removals  
- Smooth checkbox toggle transitions  
- Input focus animations or other micro-interactions  

---

## 🧾 Submission Instructions

1. **Fork this repository** into your own GitHub account.  
2. Complete your work in a `develop` branch.  
3. Create a **Pull Request to your fork** (`develop` → `main`). Do **NOT** open your PR to this repository.
4. Fill out the PR using the provided **pull request template**.  
5. Fill [this form](https://coda.io/form/Type-B-Digital-Take-Home-assessment-submission_dXORqP39w2E) to officially submit your work.  
6. Do **NOT** send submissions via direct email.

> ⚠️ Submissions may not be considered if the instructions are not followed correctly.  

---

## ⏳ Time Expectation

We expect this task to take around **6–8 hours**. Please don’t over-optimize — the goal is to assess your ability to build and ship a functional Android app with correct build configurations and database persistence.

---

## ✅ Evaluation Criteria

- Instructions followed  
- Visual accuracy to the Figma design  
- Functional completeness of all features  
- Correct implementation of **database persistence** (not SharedPreferences)  
- Correct implementation of **debug/release build types**  
- Correct implementation of **multiple variants (flavors)**  
- Code structure and modularity (MVVM or equivalent separation preferred)  
- Clarity in PR write-up and reasoning  
- **Transparency in AI usage** (if applicable)  
- (Optional) Effective use of animations  

---

Thank you for your time and effort — we’re excited to see what you build! 🚀  

