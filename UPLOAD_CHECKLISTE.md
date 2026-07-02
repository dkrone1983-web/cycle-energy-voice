# ✅ GitHub Upload Checkliste für Cycle Energy Voice

Folge dieser Liste genau, um dein Projekt auf GitHub hochzuladen!

---

## 🎯 Schritt 0: Vorbereitung

**Das habe ich bereits für dich erstellt:**
- ✅ `.github/workflows/android-release.yml` - GitHub Actions Workflow
- ✅ `gradlew` - Gradle Wrapper (Unix)
- ✅ `gradlew.bat` - Gradle Wrapper (Windows)
- ✅ `gradle/wrapper/gradle-wrapper.properties` - Gradle Config
- ✅ Alle Kotlin-Source-Dateien
- ✅ Alle Ressourcen (strings.xml, etc.)
- ✅ `TECHNICAL_PLAN.md` - Technischer Plan
- ✅ `IMPLEMENTATION_SUMMARY.md` - Implementierungs-Details
- ✅ `MVP_README.md` - User-Dokumentation
- ✅ `GITHUB_ANLEITUNG.md` - Diese Anleitung

---

## 📁 Was du hochladen musst

Öffne deinen Tracker Folder:
```
C:\Users\dkrone\Claude\Projects\Tracker
```

Du siehst:

```
Tracker/
├── .github/
│   └── workflows/
│       └── android-release.yml          ✅ MUSS hochgeladen werden
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/cycleenergy/   ✅ ALLE Kotlin Files
│   │   │   ├── res/                     ✅ Strings, Drawables
│   │   │   └── AndroidManifest.xml     ✅ MUSS hochgeladen werden
│   │   └── test/
│   └── build.gradle.kts                ✅ MUSS hochgeladen werden
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar           ✅ MUSS hochgeladen werden
│       └── gradle-wrapper.properties    ✅ MUSS hochgeladen werden
├── build.gradle.kts                     ✅ MUSS hochgeladen werden
├── settings.gradle.kts                  ✅ MUSS hochgeladen werden
├── gradlew                              ✅ MUSS hochgeladen werden (wichtig!)
├── gradlew.bat                          ✅ MUSS hochgeladen werden
├── proguard-rules.pro                   ✅ Optional
└── README.md (oder MVP_README.md)       ✅ Optional
```

---

## 🚀 Schritt 1: GitHub Account & Repository (5 min)

### 1.1 GitHub Account
1. Gehe zu **https://github.com**
2. Klick **"Sign up"**
3. Email, Passwort, Username eingeben
4. Email bestätigen (Link im Postfach)

### 1.2 Neues Repository
1. Nach Login: Klick **"Your repositories"** (Profil-Icon → "Your repositories")
2. Klick grüner Button **"New"**
3. Fülle aus:
   - **Repository name**: `cycle-energy-voice`
   - **Description**: `Android app for tracking energy with voice input`
   - **Visibility**: Public ← **WICHTIG!** (kostenlose Actions)
   - **Initializing**: NICHT ankreuzen

4. Klick **"Create repository"**

✅ **Fertig! Du siehst jetzt:**
```
https://github.com/DEIN_USERNAME/cycle-energy-voice
```

---

## 📤 Schritt 2: Alle Dateien hochladen (5 min)

Du bist auf deiner leeren Repository-Seite. Jetzt Upload!

### 2.1 Große Datei-Gruppen hochladen (Einfacher Weg)

1. Klick grüner Button **"Add files"** (rechts oben)
2. Wähle **"Upload files"**
3. **Jetzt kommt der wichtige Part:**

**Öffne FILE EXPLORER auf deinem PC:**
```
C:\Users\dkrone\Claude\Projects\Tracker
```

**Markiere ALLE Dateien und Ordner:**
- Drücke `Ctrl+A` (oder select manually)

**WICHTIG: Muss markiert sein:**
- ✅ app/ (der ganze Ordner!)
- ✅ .github/ (der ganze Ordner!)
- ✅ gradle/ (der ganze Ordner!)
- ✅ build.gradle.kts
- ✅ settings.gradle.kts
- ✅ gradlew (OHNE Extension!)
- ✅ gradlew.bat
- ✅ proguard-rules.pro
- ✅ AndroidManifest.xml
- ✅ Alle .md Dateien (README, TECHNICAL_PLAN, etc.)

4. **Drag & Drop** in das GitHub Upload-Feld
   - Oder: Klick **"select files"** und browse

5. **Warten** bis alle Dateien hochgeladen sind (grüne Checkmarks)

6. **Commit Message:** Unten bei "Commit message" schreib:
   ```
   Initial commit: Cycle Energy Voice MVP
   ```

7. Klick **"Commit changes"** (grüner Button)

**Das dauert 1-2 Minuten...** Du siehst dann alle Dateien im Repo! ✅

---

## ⚙️ Schritt 3: GitHub Actions aktivieren (3 min)

GitHub Actions sollte automatisch aktiviert sein, aber lass uns sichergehen:

1. Gehe zu Tab **"Actions"** (oben, neben "Code")
2. Du siehst entweder:
   - **"Build Release APK"** Workflow → Klick darauf
   - **"There are no workflows"** → Keine Sorge, wird bald angezeigt

3. Wenn du den Workflow siehst:
   - Klick **"Run workflow"** (rechts oben, blauer Button)
   - Fenster öffnet: **"Run workflow"** bestätigen
   - **BUILD STARTET!** 🚀

---

## ⏳ Schritt 4: Build-Progress ansehen (10-15 min)

Der Build läuft jetzt! So tracker du den Fortschritt:

1. Gehe zu **"Actions"** Tab
2. Du siehst einen Workflow-Eintrag mit:
   - 🟡 **Yellow Kreis** = Läuft noch (wartet!)
   - 🟢 **Green Checkmark** = Erfolgreich! ✅
   - 🔴 **Red X** = Fehler

3. **Klick auf den Workflow** um Details zu sehen
4. **Scroll runter** um den Build-Fortschritt zu sehen

**Typische Build-Schritte:**
```
✓ Checkout code
✓ Set up JDK 17
⏳ Build Debug APK (dauert am längsten)
⏳ Build Release APK
✓ Upload Artifacts
```

**Warte geduldig!** Der erste Build dauert am längsten (~15 min). Nächste Builds sind schneller.

---

## 📥 Schritt 5: APK herunterladen (2 min)

**Wenn der Build erfolgreich ist** (grüner Checkmark):

1. Klick auf den **grünen Workflow-Eintrag**
2. Scroll **bis ganz runter** zu **"Artifacts"**
3. Du siehst 2 Download-Links:
   ```
   📦 debug-apk (test version - downloade das zuerst!)
   📦 release-apk (production version)
   ```

4. **Klick "debug-apk"** um herunterzuladen
   - ZIP-Datei wird auf deinem PC gespeichert

5. **ZIP extrahieren:**
   - Rechtsklick auf ZIP
   - "Extrahieren nach..." → einen Ordner wählen
   - Du siehst jetzt: `app-debug.apk`

✅ **Du hast deine APK!** 🎉

---

## 📱 Schritt 6: APK auf Android-Handy installieren (5 min)

### Schritt 6.1: Handy vorbereiten (einmalig)

1. **Öffne auf deinem Handy:**
   - Einstellungen
   - Suche nach "Sicherheit" oder "Datenschutz"
   - Finde: "Unbekannte Quellen" oder "Installation von Unbekannten Apps"
   - **AKTIVIERE:** "Apps von unbekannten Quellen installieren"
   
2. **Bestätige** die Warnung

### Schritt 6.2: APK installieren

**Methode A: Über USB-Kabel (einfach)**
1. Handy mit USB-Kabel an PC anschließen
2. APK-Datei auf Handy kopieren
3. Handy-File-Manager öffnen
4. `app-debug.apk` antippen
5. **"Installieren"** klicken
6. Warten...
7. **"Öffnen"** klicken um zu testen ✅

**Methode B: Über Email/Drive**
1. APK per Email an dich schicken
2. Auf Handy: Email öffnen → APK herunterladen
3. Handy-File-Manager → APK antippen
4. "Installieren" → Fertig ✅

**Methode C: Über ADB (für Fortgeschrittene)**
```bash
adb install app-debug.apk
```

---

## 🧪 Schritt 7: App testen

Wenn die App installiert ist:

1. **Home-Screen:** App antippen **"Cycle Energy Voice"**
2. **Onboarding:** Zykluslänge eingeben, Datum wählen
3. **Mikrofon-Button:** Antippen und sprechen: "Energie 7, abends, müde"
4. **Prüfen:** Erkannte Werte überprüfen
5. **Speichern:** "Speichern" klicken
6. **Erlaubnis:** Handy fragt nach Mikrofon-Berechtigung → "Erlauben"
7. **Fertig!** 🎉

---

## ❌ Fehlerbehandlung

| Problem | Lösung |
|---------|--------|
| **Build schlägt fehl (roter X)** | Warte 10 Sekunden, dann klick "Run workflow" nochmal |
| **"gradlew not found"** | Überprüfe, dass `gradlew` und `gradlew.bat` hochgeladen wurden |
| **APK nicht im Artifacts** | Build noch nicht fertig? Oder Fehler - check "Build Logs" |
| **APK installiert aber App startet nicht** | Kontrolliere AndroidManifest.xml ist hochgeladen |
| **Mikrofon funktioniert nicht** | Gib der App Berechtigung in Handy-Einstellungen |

---

## 💡 Was du jetzt tun kannst

Jetzt hast du eine **automatisierte Pipeline**! 🚀

### Code ändern + neue APK bauen:
1. Öffne deine lokale Dateien
2. Ändere Code (z.B. Theme-Farben in `Theme.kt`)
3. Speichern
4. GitHub.com → Repo → "Add files" → Upload → Commit
5. GitHub Actions baut automatisch neue APK
6. Download + Installiere auf Handy
7. Teste die Änderungen

### Beispiel-Änderungen:
- Theme-Farben anpassen
- Strings übersetzen (in `strings.xml`)
- Parser verbessern
- Neue Features hinzufügen

---

## ✅ Final Checklist

Bevor du anfängst:

- [ ] GitHub Account erstellt
- [ ] Repository erstellt (`cycle-energy-voice`)
- [ ] Alle Dateien hochgeladen
- [ ] Workflow-Datei (`.github/workflows/android-release.yml`) ist im Repo
- [ ] Build startet & wird grün (✅)
- [ ] APK heruntergeladen
- [ ] Handy Einstellung "Unbekannte Quellen" aktiviert
- [ ] APK auf Handy installiert
- [ ] App startet und funktioniert

---

## 🎯 Geschätzte Gesamtzeit: **30-45 Minuten** (beim ersten Mal)

Danach dauert ein neuer Build ~10 Minuten.

---

## 📞 Falls was schiefgeht:

Schreib mir eine Nachricht mit:
1. Welcher Schritt?
2. Was ist die Fehlermeldung?
3. Screenshot wäre hilfreich

---

**Du schaffst das! 💪 Viel Erfolg mit deiner App!** 🚀
