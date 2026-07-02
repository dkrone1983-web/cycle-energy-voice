# Cycle Energy Voice - Technischer Umsetzungsplan

## 1. Projektstruktur und Architektur

### Stack
- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Storage**: Room (lokal), DataStore (Einstellungen)
- **Voice**: Android SpeechRecognizer
- **Architecture**: MVVM + Repository Pattern
- **Target API**: 30+
- **Min API**: 26

### Clean Architecture
```
app/
├── data/          # Repository, DB, Entities, DataStore
├── domain/        # Use Cases, Models (Business Logic)
├── presentation/  # UI (Screens, ViewModels, Composables)
├── core/          # Utils, Constants, Extensions
└── di/            # Dependency Injection
```

---

## 2. Ordnerstruktur (detailliert)

```
CycleEnergyVoice/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── java/com/cycleenergy/
│   │   │   │   ├── CycleEnergyApp.kt         # App Entry Point
│   │   │   │   │
│   │   │   │   ├── data/                     # Data Layer
│   │   │   │   │   ├── db/
│   │   │   │   │   │   ├── CycleDatabase.kt
│   │   │   │   │   │   ├── dao/
│   │   │   │   │   │   │   └── CheckInDao.kt
│   │   │   │   │   │   └── entity/
│   │   │   │   │   │       ├── CheckInEntity.kt
│   │   │   │   │   │       └── CycleSettingsEntity.kt
│   │   │   │   │   ├── datastore/
│   │   │   │   │   │   ├── AppSettings.kt    # DataStore Wrapper
│   │   │   │   │   │   └── SettingsRepository.kt
│   │   │   │   │   └── repository/
│   │   │   │   │       ├── CheckInRepository.kt
│   │   │   │   │       └── SettingsRepository.kt
│   │   │   │   │
│   │   │   │   ├── domain/                   # Domain Layer
│   │   │   │   │   ├── model/
│   │   │   │   │   │   ├── CheckIn.kt
│   │   │   │   │   │   ├── CyclePhase.kt
│   │   │   │   │   │   ├── TimeOfDay.kt
│   │   │   │   │   │   ├── Mood.kt
│   │   │   │   │   │   └── Symptom.kt
│   │   │   │   │   └── usecase/
│   │   │   │   │       ├── ParseVoiceInput.kt
│   │   │   │   │       ├── SaveCheckIn.kt
│   │   │   │   │       ├── GetCheckInsForAnalysis.kt
│   │   │   │   │       └── ExportCheckInsAsCsv.kt
│   │   │   │   │
│   │   │   │   ├── presentation/             # Presentation Layer
│   │   │   │   │   ├── MainActivity.kt
│   │   │   │   │   ├── viewmodel/
│   │   │   │   │   │   ├── HomeViewModel.kt
│   │   │   │   │   │   ├── VoiceCaptureViewModel.kt
│   │   │   │   │   │   ├── ConfirmCheckInViewModel.kt
│   │   │   │   │   │   ├── HistoryViewModel.kt
│   │   │   │   │   │   ├── InsightsViewModel.kt
│   │   │   │   │   │   └── SettingsViewModel.kt
│   │   │   │   │   ├── screen/
│   │   │   │   │   │   ├── OnboardingScreen.kt
│   │   │   │   │   │   ├── HomeScreen.kt
│   │   │   │   │   │   ├── VoiceCaptureScreen.kt
│   │   │   │   │   │   ├── ConfirmCheckInScreen.kt
│   │   │   │   │   │   ├── ManualEntryScreen.kt
│   │   │   │   │   │   ├── HistoryScreen.kt
│   │   │   │   │   │   ├── InsightsScreen.kt
│   │   │   │   │   │   └── SettingsScreen.kt
│   │   │   │   │   ├── component/
│   │   │   │   │   │   ├── MicrophoneButton.kt
│   │   │   │   │   │   ├── CheckInCard.kt
│   │   │   │   │   │   ├── EnergyLevelSlider.kt
│   │   │   │   │   │   ├── CyclePhaseSelector.kt
│   │   │   │   │   │   ├── TimeOfDaySelector.kt
│   │   │   │   │   │   ├── MoodSelector.kt
│   │   │   │   │   │   ├── StressLevelSlider.kt
│   │   │   │   │   │   ├── SymptomChips.kt
│   │   │   │   │   │   └── HeatmapGrid.kt
│   │   │   │   │   └── navigation/
│   │   │   │   │       └── NavGraph.kt
│   │   │   │   │
│   │   │   │   ├── core/                     # Core Layer
│   │   │   │   │   ├── constant/
│   │   │   │   │   │   ├── Moods.kt
│   │   │   │   │   │   ├── Symptoms.kt
│   │   │   │   │   │   └── CyclePhases.kt
│   │   │   │   │   ├── extension/
│   │   │   │   │   │   ├── DateExtensions.kt
│   │   │   │   │   │   └── StringExtensions.kt
│   │   │   │   │   ├── parser/
│   │   │   │   │   │   ├── VoiceParser.kt
│   │   │   │   │   │   ├── EnergyParser.kt
│   │   │   │   │   │   ├── CyclePhaseParser.kt
│   │   │   │   │   │   ├── TimeOfDayParser.kt
│   │   │   │   │   │   ├── MoodParser.kt
│   │   │   │   │   │   ├── StressParser.kt
│   │   │   │   │   │   └── SymptomParser.kt
│   │   │   │   │   └── util/
│   │   │   │   │       ├── CsvExporter.kt
│   │   │   │   │       ├── DateFormatter.kt
│   │   │   │   │       └── LocalizationUtil.kt
│   │   │   │   │
│   │   │   │   └── di/
│   │   │   │       ├── AppModule.kt
│   │   │   │       ├── DatabaseModule.kt
│   │   │   │       ├── RepositoryModule.kt
│   │   │   │       └── ViewModelModule.kt
│   │   │   │
│   │   │   └── res/
│   │   │       ├── values/
│   │   │       │   ├── strings.xml
│   │   │       │   └── colors.xml
│   │   │       ├── values-de/
│   │   │       │   └── strings.xml
│   │   │       └── drawable/
│   │   │           └── ic_microphone.xml
│   │   │
│   │   └── test/ & androidTest/
│   │       ├── unit tests für Parser
│   │       └── UI tests für Screens
│   │
│   └── build.gradle.kts
│
├── build.gradle.kts (Root)
├── settings.gradle.kts
└── README.md
```

---

## 3. Abhängigkeiten (build.gradle.kts)

```kotlin
// Compose
implementation("androidx.compose.ui:ui:1.6.x")
implementation("androidx.compose.material3:material3:1.1.x")
implementation("androidx.compose.foundation:foundation:1.6.x")
implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.x")

// Room
implementation("androidx.room:room-runtime:2.6.x")
implementation("androidx.room:room-ktx:2.6.x")
kapt("androidx.room:room-compiler:2.6.x")

// DataStore
implementation("androidx.datastore:datastore-preferences:1.0.x")

// Navigation
implementation("androidx.navigation:navigation-compose:2.7.x")

// Lifecycle
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.x")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.x")

// Hilt (optional für DI, can be skipped for simplicity)
// implementation("com.google.dagger:hilt-android:2.47")
```

---

## 4. Datenmodelle (Domain Layer)

### CheckIn
```kotlin
data class CheckIn(
    val id: Long = 0,
    val timestamp: Long,
    val energyLevel: Int,           // 1-10
    val cyclePhase: CyclePhase,
    val timeOfDay: TimeOfDay,
    val mood: String,               // z.B. "müde", "ausgeruht"
    val stressLevel: Int,           // 1-10
    val symptoms: List<String>      // z.B. ["Kopfschmerzen", "Müdigkeit"]
)

enum class CyclePhase {
    MENSTRUATION, FOLLICULAR, OVULATION, LUTEAL, UNKNOWN
}

enum class TimeOfDay {
    MORNING, AFTERNOON, EVENING, NIGHT
}
```

### CycleSettings (aus Onboarding)
```kotlin
data class CycleSettings(
    val cycleLengthDays: Int = 28,
    val menstruationLengthDays: Int = 5,
    val lastMenstruationStartDate: Long? = null,
    val isOnboarded: Boolean = false
)
```

---

## 5. Voice Parser Strategy

Der Parser soll deutsche Sätze wie diese erkennen:
- "Energie 3, Lutealphase, abends, müde, Stress 4"
- "Energie 8, Follikulär, morgens, energiegeladen, Kopfschmerzen, Stress 2"
- "Energie 5, Eisprung, nachmittags, neutral, Stress 5"

### Parser-Strategie
1. **Regex-basiert** für strukturierte Teile (Energie 1-10, Stress 1-10)
2. **Keyword-Matching** für Zyklusphase, Tageszeit, Stimmungen
3. **Fallback**: "UNKNOWN" für nicht erkannte Werte

### ParseResult
```kotlin
data class ParseResult(
    val energyLevel: Int? = null,
    val cyclePhase: CyclePhase = CyclePhase.UNKNOWN,
    val timeOfDay: TimeOfDay = TimeOfDay.UNKNOWN,
    val mood: String = "neutral",
    val stressLevel: Int? = null,
    val symptoms: List<String> = emptyList(),
    val confidence: Float = 0.5f
)
```

---

## 6. Implementierungs-Reihenfolge (14 Schritte)

### Phase 1: Foundation (Schritte 1-3)
- [ ] **Schritt 1**: Projektstruktur + build.gradle.kts Setup
- [ ] **Schritt 2**: Datenmodelle + Room Entities
- [ ] **Schritt 3**: Room Datenbank + Dao

### Phase 2: Data Layer (Schritte 4-5)
- [ ] **Schritt 4**: DataStore Preferences für Settings
- [ ] **Schritt 5**: Repository Layer (CheckInRepository, SettingsRepository)

### Phase 3: Voice Parsing (Schritt 6)
- [ ] **Schritt 6**: German Voice Parser mit Regex + Keyword-Matching

### Phase 4: UI Foundation (Schritte 7-9)
- [ ] **Schritt 7**: Navigation Setup + MainActivity
- [ ] **Schritt 8**: Onboarding Screen + ViewModel
- [ ] **Schritt 9**: Home Screen + großer Mikrofon-Button

### Phase 5: Voice Flow (Schritte 10-11)
- [ ] **Schritt 10**: Voice Capture Screen + SpeechRecognizer Integration
- [ ] **Schritt 11**: Confirm Check-in Screen + ViewModel

### Phase 6: Core Features (Schritte 12-13)
- [ ] **Schritt 12**: Manual Entry (Fallback) + History List
- [ ] **Schritt 13**: Insights Screen (simple Heatmap nach Phase + Tageszeit)

### Phase 7: Polish (Schritt 14)
- [ ] **Schritt 14**: CSV Export + Settings (Daten-löschen) + Styling

---

## 7. Architektur-Übersicht

```
┌─────────────────────────────────────────────────┐
│         UI Layer (Jetpack Compose)              │
│  Screens, ViewModels, Navigation                │
└──────────────────┬──────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────┐
│     Domain Layer (Use Cases, Models)            │
│  ParseVoiceInput, SaveCheckIn, etc.             │
└──────────────────┬──────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────┐
│    Data Layer (Repository, DB, DataStore)      │
│  CheckInRepository, SettingsRepository          │
└──────────────────┬──────────────────────────────┘
                   │
        ┌──────────┴───────────┐
        │                      │
┌───────▼─────┐         ┌──────▼──────┐
│ Room DB     │         │ DataStore   │
│ (Check-ins) │         │ (Settings)  │
└─────────────┘         └─────────────┘
```

---

## 8. Key Features per Screen

| Screen | Komponenten | Logik |
|--------|------------|-------|
| **Onboarding** | Cycle length input, Start date picker, Next button | Speichert Cycle Settings in DataStore |
| **Home** | Large Microphone Button, Recent Check-ins (3), Settings | Navigation zu Voice Capture |
| **Voice Capture** | Waveform visual, Recording status, "Sprechen Sie..." | SpeechRecognizer, Parser-Integration |
| **Confirm** | ParseResult as Cards, Edit buttons, Save/Cancel | User Review vor Save |
| **Manual Entry** | Sliders, Dropdowns, Text fields | Fallback-Interface |
| **History** | List von Check-ins, Filter (Phase/Zeit), Delete | Room Query |
| **Insights** | Heatmap (Phase x TimeOfDay), Avg Energy by Phase | Data Aggregation |
| **Settings** | App Version, Delete all data, Privacy notice | DataStore Clear |

---

## 9. Styling & Theme

- **Color Palette**: Warm, calming, minimal (z.B. Pastels)
- **Typography**: Large, readable, accessible
- **Spacing**: Generous, breathing room
- **Icons**: Minimalist, outline style
- **Dark Mode**: Optional für Phase 2

---

## 10. Testing-Strategie

- Unit Tests für Parser (verschiedene Sätze)
- Unit Tests für Repository
- UI Tests für kritische Flows (Onboarding → Home → Voice → Confirm)
- Local database tests (Room)

---

## 11. Next Steps

1. ✅ Technischer Plan (DONE)
2. → Projekt initialisieren + build.gradle.kts
3. → Datenmodelle + Room
4. → Parser
5. → Screens + Navigation
6. → Voice Capture
7. → Insights
8. → Export + Settings
9. → Testing + Polish

---

**Ziel**: Erste lauffähige MVP-Version mit Onboarding → Home → Voice → Confirm → History → simple Insights bis Ende der Phase 6.
