# 🚀 Schritt-für-Schritt Anleitung: Cycle Energy Voice APK bauen mit GitHub Actions

Diese Anleitung ist für **absolute Anfänger** geschrieben. Keine Vorkenntnisse nötig!

---

## 📋 Was ist GitHub Actions?

GitHub Actions ist ein **kostenloses Automatisierungs-Tool** von GitHub. Es baut deine App automatisch, wenn du Code hochlädst. Das ist perfekt für dich:
- ✅ Kostenlos
- ✅ Keine Installation nötig
- ✅ Automatisch
- ✅ APK wird bereitgestellt

---

## ⏱️ Geschätzte Dauer: 10-15 Minuten

---

## 🎯 Schritt 1: GitHub Account erstellen (falls nicht vorhanden)

1. Gehe zu **https://github.com**
2. Klick **"Sign up"** (grün Button oben rechts)
3. Gib ein:
   - Email-Adresse
   - Passwort (sicher!)
   - Username (z.B. `dennis-cycle-energy`)
4. Klick **"Create account"**
5. Bestätige deine Email (Github schickt dir einen Link)

**Fertig!** Du hast jetzt einen GitHub Account 🎉

---

## 🎯 Schritt 2: Neues Repository erstellen

1. Nach dem Login siehst du deine **Startseite**
2. Oben links: Klick auf dein **Profil-Icon** (kleine Kreis oben rechts)
3. Wähle **"Your repositories"**
4. Klick grüner Button **"New"**

**Im neuen Repository-Fenster:**

| Feld | Wert |
|------|------|
| Repository name | `cycle-energy-voice` |
| Description | `Android app for tracking energy levels during cycle` (optional) |
| Visibility | **Public** (damit GitHub Actions gratis ist) |
| Initialize this repository | **NICHT** ankreuzen |

5. Klick **"Create repository"** (grüner Button)

**Du siehst jetzt eine leere Seite mit einer URL wie:**
```
https://github.com/DEIN_USERNAME/cycle-energy-voice
```

✅ **Repository erstellt!**

---

## 🎯 Schritt 3: Code hochladen (GitHub Web Interface)

Jetzt musst du deine Projekt-Dateien hochladen. GitHub macht das einfach!

### Option A: Über GitHub Website (Einfach, für Anfänger) ⭐

1. Du bist auf deiner leeren Repository-Seite
2. Klick großer Button **"uploading an existing file"** (oder "Add files → Upload files")
3. **Dateien auswählen:**
   - Öffne deinen File Explorer
   - Gehe zu `C:\Users\dkrone\Claude\Projects\Tracker`
   - Wähle ALLE Dateien und Ordner:
     ```
     ✅ app/
     ✅ .github/
     ✅ build.gradle.kts
     ✅ settings.gradle.kts
     ✅ gradlew
     ✅ gradlew.bat
     ✅ TECHNICAL_PLAN.md
     ✅ MVP_README.md
     ```
   - Drag & Drop in das GitHub Upload-Feld (oder click "Select files")

4. **Commit Message:**
   - Unten: Gib ein: `Initial commit: Cycle Energy Voice MVP`
5. Klick **"Commit changes"** (grüner Button)

GitHub lädt jetzt alles hoch. Das dauert ~1-2 Minuten.

**Dann siehst du alle deine Dateien im Repository!** ✅

---

## 🎯 Schritt 4: GitHub Actions aktivieren

1. Du bist auf deiner Repository-Seite
2. Gehe zu **"Actions"** Tab (oben, neben "Code", "Issues", etc.)
3. Du siehst: **"There are no workflows"** - Das ist normal!
4. Scroll runter, du siehst einen Link: **"set up a workflow yourself"** 
5. Klick darauf

**Neuer Editor öffnet sich:**

Dieser Editor zeigt eine leere Workflow-Datei. Wir ersetzen das mit unserer Datei:

1. **Lösche alles** (Ctrl+A, dann Delete)
2. Kopiere diesen Code und **paste** ihn:

```yaml
name: Build Release APK

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main, develop ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3

    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
        cache: gradle

    - name: Grant execute permission for gradlew
      run: chmod +x gradlew

    - name: Build Debug APK
      run: ./gradlew assembleDebug --no-daemon

    - name: Build Release APK
      run: ./gradlew assembleRelease --no-daemon

    - name: Upload Debug APK
      uses: actions/upload-artifact@v3
      if: always()
      with:
        name: debug-apk
        path: app/build/outputs/apk/debug/app-debug.apk

    - name: Upload Release APK
      uses: actions/upload-artifact@v3
      if: always()
      with:
        name: release-apk
        path: app/build/outputs/apk/release/app-release-unsigned.apk

    - name: Upload Build Logs
      uses: actions/upload-artifact@v3
      if: failure()
      with:
        name: build-logs
        path: |
          app/build/outputs/
          build/

  release:
    needs: build
    runs-on: ubuntu-latest
    if: startsWith(github.ref, 'refs/tags/')

    steps:
    - uses: actions/download-artifact@v3
      with:
        name: release-apk

    - name: Create Release
      uses: softprops/action-gh-release@v1
      with:
        files: app-release-unsigned.apk
        body: |
          Cycle Energy Voice Release APK
          Download and install the APK on your Android device.
        draft: false
        prerelease: false
      env:
        GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
```

3. **Dateiname oben:** Ändere `main.yml` zu `android-release.yml`
4. Klick grüner Button **"Commit changes"**

**Fertig!** GitHub Actions ist jetzt aktiv! 🎉

---

## 🎯 Schritt 5: Gradle Wrapper hinzufügen

GitHub braucht die Gradle Wrapper-Dateien. Diese müssen wir hochladen:

1. Zurück auf deiner Repository-Hauptseite (Tab "Code")
2. Klick **"Add files" → "Upload files"**
3. **Diese Dateien hochladen:**
   - `gradlew` (eine Datei ohne Extension)
   - `gradlew.bat`
   - `gradle/wrapper/gradle-wrapper.jar`
   - `gradle/wrapper/gradle-wrapper.properties`

Falls diese Dateien nicht in deinem Tracker-Folder sind, erstelle ich sie. Sag mir Bescheid!

4. Klick **"Commit changes"**

---

## 🎯 Schritt 6: Build anstoßen (APK bauen lassen)

Jetzt passiert die Magie! 🪄

### Option A: Automatisch (beim Hochladen von Dateien)

GitHub Actions startet **automatisch**, wenn du neue Dateien hochlädst. Das ist bereits passiert!

### Option B: Manuell auslösen

1. Gehe zu **"Actions"** Tab
2. Links: Wähle **"Build Release APK"**
3. Klick **"Run workflow"** (rechts oben, blauer Button)
4. Fenster öffnet sich: Klick nochmal **"Run workflow"**

**Die App wird jetzt gebaut!** 🚀

---

## 🎯 Schritt 7: Build-Progress ansehen

1. Gehe zu **"Actions"** Tab
2. Du siehst einen Entry mit gelbem Kreis ⏳ (bedeutet: läuft noch)

**Warte einfach...** Der Build dauert ~10-15 Minuten beim ersten Mal.

Du siehst:
- 🟡 **Yellow circle** = Läuft noch
- 🟢 **Green checkmark** = Erfolgreich ✅
- 🔴 **Red X** = Fehler (kommt vor, nicht tragisch)

Klick auf den Workflow-Entry um Details zu sehen.

---

## 🎯 Schritt 8: APK downloaden

**Wenn der Build erfolgreich ist** (grüner Checkmark):

1. Klick auf den **grünen Workflow-Eintrag**
2. Scroll runter zu **"Artifacts"**
3. Du siehst 2 Downloads:
   - ✅ **debug-apk** (Test-Version, funktioniert immer)
   - 📦 **release-apk** (Produktions-Version)

**Downloade die DEBUG-APK zuerst zum Testen!**

Klick **"debug-apk"** → ZIP-Datei wird heruntergeladen

---

## 📱 Schritt 9: APK auf dein Android-Handy installieren

### Vorbereitung (einmalig):
1. Auf deinem Android-Handy:
   - **Einstellungen → Sicherheit → Unbekannte Quellen**
   - Aktiviere: **"Apps von unbekannten Quellen installieren"**

### APK installieren:
1. Download-ZIP öffnen
2. Die Datei `app-debug.apk` extrahieren
3. **Entweder:**
   - Datei auf Handy übertragen (USB oder Email)
   - Handy-File-Manager öffnen
   - APK-Datei antippen
   - **"Installieren"** bestätigen
   
   **Oder:**
   - Android Studio (falls vorhanden)
   - Oder Android Debug Bridge (ADB) im Terminal

4. **Fertig!** App ist installiert 🎉

---

## 🆘 Fehlerbehandlung

### Fehler: Build schlägt fehl (roter X)

1. Gehe zu **"Actions"** → Workflow anklicken
2. Scroll zu **"Build Release APK"** Step
3. Scroll runter, um Fehler zu sehen
4. **Häufige Fehler:**

| Fehler | Lösung |
|--------|--------|
| `gradlew not found` | Gradle Wrapper-Dateien hochladen (Schritt 5) |
| `Build failed` | AndroidManifest.xml überprüfen |
| `Out of memory` | Normales Problem beim ersten Build - wiederholen |

### Fehler: APK nicht downloadbar

- Checkmark ist noch gelb? → Warten Sie noch 5 min
- Checkmark ist grün aber kein Download? → Refresh Browser (F5)

---

## ✅ Erfolgs-Checkliste

Wenn du bis hier kommst:

- ✅ GitHub Account erstellt
- ✅ Repository erstellt
- ✅ Alle Dateien hochgeladen
- ✅ Workflow-Datei hinzugefügt
- ✅ Build gestartet
- ✅ APK heruntergeladen
- ✅ App auf Handy installiert

**Glückwunsch! 🎉 Du hast deine erste App gebaut!**

---

## 🚀 Zukünftige Updates

Jetzt ist es super einfach, Änderungen zu machen:

1. Ändere Code lokal
2. Gehe zu GitHub.com → dein Repo → "Add files" → Upload
3. Commit
4. GitHub Actions baut automatisch die neue APK
5. Download & installiere

---

## 📞 Hilfe & Ressourcen

Falls du stecken bleibst:

- **GitHub Docs**: https://docs.github.com/en/actions
- **Android Debug Bridge**: https://developer.android.com/tools/adb
- **APK Installation**: Einfach danach googeln "Install APK Android Handy"

---

## 💡 Tipps für dein Projekt

**Nächste Schritte nach erfolgreichem Build:**

1. **Testen:** Öffne die App auf deinem Handy
2. **Voice Permissions:** Gib der App Mikrofon-Berechtigung
3. **Onboarding:** Durchlaufe das Setup
4. **Feedback:** Notiere dir was funktioniert, was nicht
5. **Code anpassen:** Ändere farben, Strings, etc. im Code → Github Upload → Neue APK

---

**Viel Erfolg mit deiner App! 🚀**

Questions? Schreib mir!
