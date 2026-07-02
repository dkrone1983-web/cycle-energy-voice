# Project Context: Cycle Energy Voice

# Claude Project Instructions
Read `docs/product-brief.md before implementing features.

Build a native Android MVP in Kotlin with Jetpack Compose.

Core principles:
- Local-first
- No account
- No cloud
- No analytics
- No saved audio files
- Voice-first check-in flow
- Calm, minimal, low-friction UX

Start with the MVP flow:
Onboarding → Home → Voice Check-in → Confirm → Save locally → History → Insights.

## Product Goal
Android-App für niedrigschwelliges Tracking des Energielevels per Spracheingabe im Kontext von Zyklusphase und Tageszeit.

## Core User Flow
Home öffnen → Mikrofon tippen → Satz sprechen → erkannte Daten prüfen → speichern.

## MVP Scope
- Kotlin
- Jetpack Compose
- Android SpeechRecognizer
- Room
- DataStore
- lokale Speicherung
- keine Cloud
- keine Accounts
- keine Audiodateien speichern
- CSV-Export
- Heatmap-Auswertung

## UX Principles
- ruhig
- minimalistisch
- große Touch-Flächen
- warm und wertfrei formuliert
- kein medizinischer Diagnose-Ton

## Screens
- Onboarding
- Home
- Voice Capture
- Confirm Check-in
- Manual Entry
- History
- Insights
- Settings

## Voice Parsing
Die App soll deutsche Spracheingaben wie „Energie 3, Lutealphase, abends, müde, Stress 4“ in strukturierte Daten umwandeln.

## Privacy
Local-first. Keine Cloud, keine Analytics, keine Werbung, keine gespeicherten Audiodateien.