# Cycle Energy Voice - MVP

Eine minimalistische Android-App zum Tracking des Energielevels im Menstruationszyklus per Spracheneingabe.

## 🎯 Features der MVP v0.1.0

### Core Functionality
- ✅ **Sprachbasierte Check-ins**: Deutsche Spracherkennung für schnelle Energielevel-Erfassung
- ✅ **Voice Parser**: Automatisches Erkennen von Energie, Zyklusphase, Tageszeit, Stimmung und Stress aus Spracheingaben
- ✅ **Lokale Speicherung**: Alle Daten bleiben auf dem Gerät (keine Cloud, kein Account)
- ✅ **Bestätigung vor dem Speichern**: Nutzer können erkannte Daten vor dem Speichern überprüfen und editieren
- ✅ **Verlauf**: Alle Check-ins chronologisch sortiert mit Löschfunktion
- ✅ **Einsichten**: Heatmap und Durchschnittswerte nach Zyklusphase und Tageszeit
- ✅ **CSV-Export**: Exportiere deine Daten für Analyse außerhalb der App
- ✅ **Datenschutz**: Lokale Speicherung, Datenlösch-Funktion, keine Tracking

### UI/UX
- ✅ Minimalistisches Design mit warmem, beruhigendem Color-Schema
- ✅ Großer Mikrofon-Button für Touch-freundliche Bedienung
- ✅ Responsive Jetpack Compose UI
- ✅ Deutsche Lokalisierung
- ✅ Intuitive Navigation zwischen Screens

---

## 📱 Screens

1. **Splash Screen**: Initial Loading & Navigation Logic
2. **Onboarding**: Zykluslänge und Menstruationsdatum eingeben
3. **Home**: Großer Mikrofon-Button, letzte Einträge, Navigation
4. **Voice Capture**: Android SpeechRecognizer mit visuellem Feedback
5. **Confirm Check-in**: Überprüfung und Bearbeitung erkannter Daten
6. **Manual Entry**: Fallback für manuelle Eingabe (optional)
7. **History**: Liste aller Check-ins mit Löschfunktion
8. **Insights**: Heatmap und Durchschnittswerte
9. **Settings**: Datenschutz, Export, Datenlöschung

---

## 🏗️ Architektur

```
MVVM + Clean Architecture

UI Layer (Jetpack Compose)
    ↓
ViewModel (State Management)
    ↓
Domain Layer (Use Cases, Models)
    ↓
Data Layer (Repository Pattern)
    ↓
Room Database + DataStore
```

### Wichtigste Komponenten

**Data Layer:**
- `CycleDatabase`: Room-Datenbank für Check-ins
- `AppSettings`: DataStore für Einstellungen (Zyklusdaten)
- `CheckInRepository`: CRUD-Operationen für Check-ins
- `SettingsRepository`: Zugriff auf Benutzer-Einstellungen

**Domain Layer:**
- `CheckIn`: Datenmodell für einen Check-in
- `CyclePhase`, `TimeOfDay`: Enums für strukturierte Daten

**Presentation Layer:**
- ViewModels für jeden Screen
- Compose Screens für UI
- Navigation mit NavGraph

**Core:**
- `VoiceParser`: Intelligent Parser für deutsche Sprachuingaben
- `CsvExporter`: CSV-Export für Datenanalyse

---

## 🗣️ Voice Parser

Der Parser erkennt Eingaben wie:
```
"Energie 3, Lutealphase, abends, müde, Stress 4"
"Energie 8, Follikulär, morgens, energiegeladen, Kopfschmerzen"
```

Erkannte Felder:
- **Energielevel**: 1-10 (Regex: `energie\s*[:]?\s*(\d+)`)
- **Zyklusphase**: Menstruation, Follikulär, Eisprung, Luteal
- **Tageszeit**: Morgens, Nachmittags, Abends, Nachts
- **Stimmung**: Müde, Ausgeruht, Energiegeladen, etc. (Keyword-Matching)
- **Stress**: 1-10 (Regex: `stress\s*[:]?\s*(\d+)`)
- **Symptome**: Kopfschmerzen, Übelkeit, Krämpfe, etc.

**Confidence Score**: Basiert darauf, wie viele Felder erfolgreich geparst wurden.

---

## 🔐 Privacy & Security

- **Lokal-first**: Keine Cloud, keine Server, keine Accounts
- **No Audio Storage**: Sprachaudio wird nicht gespeichert
- **No Analytics**: Keine Tracking, keine Telemetrie
- **No Ads**: Werbefrei
- **Data Deletion**: Nutzer können alle Daten jederzeit löschen
- **Open Data Export**: CSV-Export für Datenkontrolle

---

## 📦 Dependencies

**Core:**
- Jetpack Compose 1.6.x
- Room 2.6.x
- DataStore 1.0.x
- Coroutines 1.7.x
- Lifecycle 2.6.x

**Keine External Analytics oder Third-Party Services**

---

## 🚀 Wie man das Projekt aufbaut

### Requirements
- Android Studio 2023.1+
- Gradle 8.0+
- Kotlin 1.9.20+
- SDK API 30+
- Min SDK 26

### Build
```bash
# Im Projektroot:
./gradlew build

# Run auf Emulator/Device:
./gradlew installDebug
```

### Struktur
```
CycleEnergyVoice/
├── app/
│   ├── src/main/
│   │   ├── java/com/cycleenergy/
│   │   │   ├── data/         # Room, DataStore, Repository
│   │   │   ├── domain/       # Models, Business Logic
│   │   │   ├── presentation/ # UI Screens & ViewModels
│   │   │   ├── core/         # Parser, Utils
│   │   │   └── CycleEnergyApp.kt
│   │   └── res/              # Strings, Drawables
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── MVP_README.md
```

---

## 🧪 Testing

Im MVP sind folgende Tests vorgesehen (noch zu implementieren):
- Unit Tests für VoiceParser (verschiedene deutsche Sätze)
- Unit Tests für Repository-Layer
- UI Tests für kritische Flows
- Room Database Tests

```bash
# Tests ausführen:
./gradlew test
./gradlew connectedAndroidTest
```

---

## 🎨 UI/UX Principles

- **Ruhig & Minimalistisch**: Warme Farben, viel Whitespace
- **Warm & Wertfrei**: Keine medizinischen Diagnosen
- **Große Touch-Flächen**: 56dp+ für Buttons
- **Accessible**: Große Fonts, hohe Kontraste
- **Offline-first**: App funktioniert ohne Internet

### Color Palette
- **Primary**: Soft Purple (#9B7E9F)
- **Secondary**: Light Purple (#C9B1CC)
- **Tertiary**: Very Light Purple (#E8D5E8)
- **Background**: Off-white (#FBF8FB)

---

## 📝 Nächste Schritte (für Phase 2+)

- [ ] Wearable Integration (Smartwatch Sync)
- [ ] Reminder für tägliche Check-ins
- [ ] Erweiterte Datenvisualisierung
- [ ] Import/Sync von anderen Tracking-Apps
- [ ] Widget für Home-Screen
- [ ] Dark Mode Optimierung
- [ ] Zyklusvorhersage
- [ ] Notiz-Funktion pro Check-in
- [ ] Backup/Restore Funktionalität
- [ ] Sharing von anonymisiertem Export

---

## 📄 Lizenz & Attribution

Privates MVP-Projekt.

---

## 👤 Support

Diese App ist ein MVP für Selbstbeobachtung. Sie ist kein Medizinprodukt und ersetzt keine professionelle medizinische Beratung.

---

**Version**: 0.1.0 (MVP)
**Status**: Development
**Last Updated**: 2024-07-02
