﻿# Task-Manager-App-in-Java
 # 📋 Task Manager App

A comprehensive Android Task Manager application that helps users efficiently manage their daily activities. This app allows users to **add, view, update, and delete tasks** while offering useful features like background reminders, motivational quotes, and customizable notification preferences.

---

## 🚀 Features

### 1. 🗃 SQLite Database (Local Storage)
- Persistent local storage using SQLite.
- Supports full **CRUD operations**:
  - **Create**: Add tasks with `title`, `description`, `due date`, and `priority`.
  - **Read**: View all saved tasks.
  - **Update**: Edit existing tasks.
  - **Delete**: Remove unwanted tasks.

### 2. ⚙️ Background Services (Reminders)
- A background notification service runs periodically.
- Notifies the user about **tasks due in the next 30 minutes**.
- Ensures users stay on top of important tasks without opening the app.

### 3. 🔐 Permissions
- **Internet Permission**: Required to fetch motivational quotes from a public API.
- **Storage Permission**: Enables users to save or retrieve task-related files.

### 4. 🧾 RecyclerView & CardView (UI Display)
- Tasks are displayed in a dynamic **RecyclerView**.
- Each item is wrapped in a **CardView** displaying:
  - Task **title**
  - **Due date**
  - **Priority indicator** (color-coded or icon-based)

### 5. 🧩 Fragments (Modular UI)
- Clean, modular UI using Fragments:
  - **Task List Fragment**: Displays all tasks using RecyclerView.
  - **Task Details Fragment**: Shows full details of a selected task.

### 6. ⚙️ Shared Preferences (Settings)
- Allows users to **enable or disable notifications**.
- Preferences are stored and retrieved via **SharedPreferences**.
- User-friendly toggle option in the settings panel.

### 7. 🌐 Internet Features (Motivational Quotes)
- On app launch or home screen access, the app fetches a **daily motivational quote** from a public **REST API**.
- Keeps users inspired while staying productive.

### 8. 🧭 Bottom Navigation Bar
- Intuitive navigation with three main sections:
  - **🏠 Home**: Displays a motivational quote and a brief app overview.
  - **📝 Tasks**: Displays the list of tasks.
  - **⚙️ Settings**: Manages notification preferences.

---

## 📸 Screenshots
![image](https://github.com/user-attachments/assets/bec628c8-9295-4246-a8f9-0da062ff0dc0)
![image](https://github.com/user-attachments/assets/1fd8d665-7d0d-49d9-8a8b-bf9238b716ec)
![image](https://github.com/user-attachments/assets/7cf89467-c908-476e-afa2-3cb96aa16025)
![image](https://github.com/user-attachments/assets/5572b2fd-eda4-4894-b5d7-9a745d6e7604)





## 🛠 Tech Stack

- **Language**: Java / Kotlin  
- **Database**: SQLite  
- **UI**: XML Layouts, RecyclerView, CardView, Fragments  
- **Background Services**: Android Services / WorkManager  
- **Storage**: SharedPreferences, Internal Storage  
- **API Integration**: Retrofit / Volley (for fetching quotes)

---



