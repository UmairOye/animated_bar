# Nike Shoes Animated Specs (Jetpack Compose)

A lightweight Android demo built with Jetpack Compose that showcases a smooth, interactive shoe-spec experience.

The app displays four shoe icons. Tapping an icon animates floating spec cards above it. Tapping any popup image opens a fullscreen preview with a smooth transition, and tapping again returns to the main screen.

## Features

- White, minimal UI focused on interaction
- Four shoe items using drawable assets
- Smooth icon press/expand animation
- Staggered popup card animations
- Image-based popup cards for selected shoes
- Fullscreen image preview overlay with animated enter/exit
- Clean data separation (models/data/UI)

## Tech Stack

- Kotlin
- Jetpack Compose (Material 3)
- Android Gradle (Kotlin DSL)

## Project Structure

`app/src/main/java/com/example/animated_bar/`

- `MainActivity.kt` - app entry point
- `ShowcaseScreen.kt` - main screen and fullscreen preview overlay
- `AnimatedBar.kt` - interactive animated bar UI
- `AnimatedBarModels.kt` - data models (`PopupElement`, `AnimatedFeature`)
- `AnimatedBarData.kt` - shoe/spec data source
- `ui/theme/*` - Compose theme setup

## Drawable Assets Used

Main shoes:

- `one`
- `two`
- `three`
- `four`

Pose/spec images currently wired:

- `two_one`, `two_two`, `two_three`
- `three_first`, `three_second`, `three_third`

## Demo

<video src="demo/demo.mov" controls muted playsinline width="100%"></video>

If inline playback is blocked on your repo view, use: [`demo/demo.mov`](demo/demo.mov)

## Getting Started

### Prerequisites

- Android Studio (latest stable recommended)
- JDK 17+
- Android SDK installed

### Run

1. Open this folder in Android Studio.
2. Let Gradle sync complete.
3. Run the `app` configuration on an emulator/device.

## Interaction Flow

1. App opens with no item expanded.
2. Tap a shoe icon to reveal animated spec cards.
3. Tap another shoe to switch content with smooth transitions.
4. Tap a popup image to open fullscreen.
5. Tap fullscreen preview to close and return.

## Notes

- Popup visuals and offsets are configured in `AnimatedBarData.kt`.
- Animation tuning (timing, scale, easing) is in `AnimatedBar.kt`.
- You can replace drawables with your own assets while keeping the same naming pattern.

