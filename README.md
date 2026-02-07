# JetpackComposeApp1

**JetpackComposeApp1** is an Android application developed with **Jetpack Compose**.  
It demonstrates the use of modern Compose UI components and a clean architecture to organize screens and reusable components.

# Project Structure

├── app/

│ ├── src/

│ │ └── main/

│ │ ├── java/

│ │ │ └── com/example/jetpackcomposeapp1/

│ │ │ ├── MainActivity.kt

│ │ │ └── components/

│ │ │ ├── CategorySelector.kt

│ │ │ ├── HomeScreen.kt

│ │ │ ├── MainSection.kt

│ │ │ ├── SelectOption.kt

│ │ │ └── ValueField.kt

│ │ └── res/

│ │ ├── drawable/

│ │ ├── mipmap-*/ (ic_launcher images)

│ │ └── values/ (colors, strings, themes)

├── build.gradle.kts

├── settings.gradle.kts

└── .gitignore


### `MainActivity.kt`
MainActivity.kt

- Entry point of the application.

- Sets up the theme and displays the main screen HomeScreen.

### `components/`

Contains the reusable UI components of the application:

1. **`CategorySelector.kt`** 

- Component for selecting a category in the UI.

2. **`HomeScreen.kt`** 

- Main screen that aggregates the app’s different components.


3. **`MainSection.kt`**

- Main section of the interface, used to display key options.

4. **`SelectOption.kt`** 

- Component for selecting an option from a dropdown menu.

5. **`ValueField.kt`** 

- Field that allows the user to input or display values.

# UI and Theme

- Theme files are located in `ui/theme/` :  
  - `Color.kt` : main colors of the app  
  - `Theme.kt` : overall theme (light/dark)  
  - `Type.kt` : text styles
   
# Technologies and Libraries

Kotlin

Jetpack Compose

Android SDK 33+

Gradle Kotlin DSL (build.gradle.kts)

# Features

Simple UI using Jetpack Compose

Modular organization of UI components

Easily extensible to add new features

# Installation

Clone the repository:

 ```
git clone https://github.com/kevinsimadjeu/simple-unit-converter.git
```

