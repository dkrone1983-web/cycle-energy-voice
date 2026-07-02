# Product Brief: Cycle Energy Voice

## 1. Produktname

Arbeitstitel: **Cycle Energy Voice**

Eine Android-App, mit der Frauen ihr aktuelles Energielevel per Spracheingabe erfassen und später nach Zyklusphase und Tageszeit auswerten können.

---

## 2. Produktidee

Die App ist ein niedrigschwelliges Voice-Logbuch für zyklusbasiertes Energietracking.

Die Nutzerin soll nicht jeden Tag lange Formulare ausfüllen müssen. Stattdessen soll sie einfach einen Satz sprechen können, zum Beispiel:

> „Energie 3, Lutealphase, abends, müde, Stress 4.“

Die App erkennt daraus strukturierte Informationen:

* Energielevel
* Zyklusphase
* Tageszeit
* Stimmung
* Stresslevel
* Symptome
* optionale Notiz

Danach bestätigt oder korrigiert die Nutzerin den Eintrag und speichert ihn.

Der wichtigste Produktgedanke ist:

**Ein Satz reicht.**

---

## 3. Zielgruppe

Die App richtet sich an Frauen, die ihre Energie im Zusammenhang mit ihrem Zyklus besser verstehen möchten.

Typische Nutzerinnen:

* Frauen, die ihre Leistungsfähigkeit über den Zyklus beobachten möchten
* Frauen, die sich zyklusorientiert organisieren möchten
* Frauen, die Papiertracking zu umständlich finden
* Frauen, die keine komplexe Zyklus-App wollen
* Frauen, die Muster in Energie, Stimmung, Schlaf und Stress erkennen möchten

Die App soll sich nicht medizinisch, nicht belehrend und nicht leistungsoptimierend anfühlen. Sie soll wie ein persönliches, ruhiges Selbstbeobachtungs-Tool wirken.

---

## 4. Hauptproblem

Viele Tracking-Apps sind zu aufwendig. Nutzerinnen müssen zu viele Felder ausfüllen, zu viele Kategorien auswählen oder täglich aktiv daran denken.

Das führt dazu, dass Tracking nach wenigen Tagen abbricht.

Diese App löst das Problem durch eine extrem schnelle Eingabe:

1. App öffnen
2. Mikrofon antippen
3. Energielevel einsprechen
4. prüfen
5. speichern

Der gesamte Check-in soll idealerweise weniger als 15 Sekunden dauern.

---

## 5. Produktziel

Die App soll Nutzerinnen helfen, wiederkehrende Muster zu erkennen:

* Habe ich in bestimmten Zyklusphasen mehr Energie?
* Zu welchen Tageszeiten bin ich in welcher Phase am stärksten?
* Wann sinkt meine Energie besonders häufig?
* Gibt es Zusammenhänge zwischen Schlaf, Stress, Stimmung und Energie?
* Welche Phasen eignen sich eher für Fokus, Ruhe, Kreativität oder Planung?

Die App soll keine medizinischen Diagnosen stellen und keine Gesundheitsversprechen machen.

---

## 6. Designprinzipien

Die App soll ruhig, weich, klar und niedrigschwellig wirken.

### Visuelle Richtung

* sanfte Farben
* viel Weißraum
* große Buttons
* runde Formen
* klare Typografie
* keine überladenen Dashboards
* keine klinische Krankenhaus-Optik
* keine grellen Alarmfarben
* keine Gamification mit Druck

### Sprache und Tonalität

Die App spricht warm, kurz und wertfrei.

Beispiele:

Gut:

* „Wie ist deine Energie gerade?“
* „Ein Satz reicht.“
* „Heute zeigt sich ein ruhiger Energiepunkt.“
* „Gespeichert.“
* „Du kannst den Eintrag jederzeit korrigieren.“

Vermeiden:

* „Du hast dein Ziel verfehlt.“
* „Tracking verpasst.“
* „Deine Energie ist schlecht.“
* „Du solltest dich mehr ausruhen.“
* „Achtung, niedrige Leistung.“

---

## 7. Kern-User-Flow

Der zentrale Flow der App:

1. Nutzerin öffnet die App.
2. Home-Screen zeigt aktuelle geschätzte Zyklusphase und Tageszeit.
3. Nutzerin tippt auf großen Mikrofon-Button.
4. App hört kurze Spracheingabe.
5. App wandelt Sprache in strukturierte Daten um.
6. Nutzerin sieht einen Bestätigungs-Screen.
7. Nutzerin speichert, korrigiert oder spricht erneut.
8. Eintrag wird lokal gespeichert.
9. Auswertungen aktualisieren sich automatisch.

---

## 8. Beispiel-Spracheingaben

Die App soll mit natürlicher deutscher Sprache umgehen können.

Beispiele:

* „Energie 3, Lutealphase, abends, müde, Stress 4.“
* „Ich bin gerade bei einer zwei von fünf, Periode, Kopfschmerzen.“
* „Heute Vormittag Energie vier, Follikelphase, fokussiert.“
* „Abends Energie niedrig, PMS, gereizt und müde.“
* „Energie fünf, Eisprung, kreativ und motiviert.“
* „Drei, nachmittags, wenig geschlafen, etwas überfordert.“
* „Stress fünf, Energie zwei, vor der Periode.“

Die App muss nicht jede Eingabe perfekt verstehen. Unklare Felder sollen im Bestätigungs-Screen bearbeitbar sein.

---

## 9. Energie-Skala

Die Energie wird immer auf einer Skala von 1 bis 5 gespeichert.

1 = sehr niedrig
2 = niedrig
3 = mittel
4 = hoch
5 = sehr hoch

Sprachliche Zuordnung:

* „sehr niedrig“ → 1
* „niedrig“ → 2
* „mittel“ → 3
* „okay“ → 3
* „hoch“ → 4
* „sehr hoch“ → 5
* „voller Energie“ → 5

Zahlen und Zahlwörter sollen erkannt werden:

* eins
* zwei
* drei
* vier
* fünf
* 1
* 2
* 3
* 4
* 5

---

## 10. Zyklusphasen

Die App verwendet diese Phasen:

* Menstruation
* Follikelphase
* Ovulationsphase
* Lutealphase
* Unbekannt

Die App soll anhand der Zykluseinstellungen eine Phase schätzen können. Die Nutzerin kann diese Phase jederzeit überschreiben.

### Synonyme

Menstruation:

* Periode
* Tage
* Blutung
* Menstruation
* Regel

Follikelphase:

* Follikel
* Follikelphase
* nach der Periode
* Aufbauphase

Ovulationsphase:

* Eisprung
* Ovulation
* fruchtbare Phase

Lutealphase:

* Luteal
* Lutealphase
* PMS
* vor der Periode
* zweite Zyklushälfte

---

## 11. Tageszeiten

Die App ordnet die aktuelle Uhrzeit automatisch einem Tageszeitblock zu.

* Morgen: 05:00–09:59
* Vormittag: 10:00–11:59
* Mittag: 12:00–13:59
* Nachmittag: 14:00–17:59
* Abend: 18:00–22:59
* Nacht: 23:00–04:59

Die Nutzerin muss die Tageszeit normalerweise nicht selbst eintragen.

Wenn sie aber etwas sagt wie „heute Morgen Energie 2“, soll die App den genannten Tageszeitblock verwenden.

---

## 12. Daten, die pro Check-in gespeichert werden

Jeder Energie-Check-in soll mindestens diese Daten enthalten:

* ID
* Datum
* Uhrzeit
* Tageszeitblock
* Energielevel 1–5
* Zyklusphase
* Quelle der Zyklusphase: geschätzt, bestätigt oder korrigiert
* Eingabemethode: Sprache oder manuell

Optionale Daten:

* Stimmung
* Stresslevel 1–5
* Schlafdauer
* Symptome
* Tags
* freie Notiz
* erkannter Originaltext der Spracheingabe
* Erkennungs-Konfidenz, falls verfügbar

Es sollen keine Audiodateien gespeichert werden.

---

## 13. Stimmung, Symptome und Tags

Die App soll einfache Tags erkennen und speichern.

### Stimmung

Mögliche Werte:

* ruhig
* müde
* gereizt
* traurig
* fokussiert
* motiviert
* kreativ
* überfordert
* klar
* angespannt
* emotional
* ausgeglichen

### Symptome

Mögliche Werte:

* Krämpfe
* Kopfschmerzen
* Brustspannen
* Rückenschmerzen
* Blähbauch
* Müdigkeit
* Heißhunger
* Übelkeit
* Schlafprobleme
* Unterleibsschmerzen

### Stress

Stress wird optional auf einer Skala von 1 bis 5 gespeichert.

Beispiele:

* „Stress 1“
* „Stresslevel 4“
* „kaum Stress“ → 1
* „sehr gestresst“ → 5

---

## 14. Screens im MVP

### 14.1 Onboarding

Ziel: Die Nutzerin richtet ihre Basisdaten ein.

Felder:

* durchschnittliche Zykluslänge
* durchschnittliche Periodenlänge
* Startdatum der letzten Periode
* kurzer Datenschutz-Hinweis
* Erklärung der Energie-Skala

Ton:

* ruhig
* transparent
* nicht medizinisch

Wichtige Botschaft:

„Die App hilft dir, persönliche Muster zu erkennen. Sie ersetzt keine medizinische Beratung.“

---

### 14.2 Home-Screen

Der Home-Screen ist der wichtigste Screen.

Elemente:

* aktuelles Datum
* geschätzte Zyklusphase
* aktueller Tageszeitblock
* großer Mikrofon-Button
* Text: „Wie ist deine Energie gerade?“
* kleiner Button: „Manuell eintragen“
* letzter Check-in
* Link zu Verlauf und Auswertung

Der Mikrofon-Button soll visuell dominant sein.

---

### 14.3 Voice-Capture-Screen

Dieser Screen erscheint während der Spracheingabe.

Zustände:

* bereit
* hört zu
* verarbeitet
* keine Sprache erkannt
* keine Mikrofonberechtigung
* Spracherkennung nicht verfügbar

Mögliche Texte:

* „Ich höre zu.“
* „Ein Satz reicht.“
* „Sprich zum Beispiel: Energie 3, müde, Stress 4.“
* „Ich konnte nichts erkennen. Versuch es noch einmal.“

---

### 14.4 Confirm-Check-in-Screen

Nach der Spracheingabe sieht die Nutzerin, was erkannt wurde.

Beispiel:

Energie: 3 / 5
Phase: Lutealphase
Tageszeit: Abend
Stimmung: müde
Stress: 4 / 5
Notiz: wenig geschlafen

Buttons:

* Speichern
* Korrigieren
* Noch einmal sprechen

Alle erkannten Werte sollen editierbar sein.

---

### 14.5 Manuelle Eingabe

Fallback, wenn Sprache nicht passt.

Elemente:

* Energie-Slider 1–5
* Phase-Chips
* Stimmung-Chips
* Stress-Slider optional
* Symptome als auswählbare Tags
* Notizfeld

Die manuelle Eingabe soll bewusst kurz bleiben.

---

### 14.6 Verlauf

Liste aller Einträge.

Jeder Eintrag zeigt:

* Datum
* Uhrzeit
* Energielevel
* Phase
* Tageszeit
* wichtigste Tags

Funktionen:

* Eintrag bearbeiten
* Eintrag löschen
* nach Zeitraum filtern
* nach Phase filtern

---

### 14.7 Insights

Die Auswertung soll einfach und visuell sein.

MVP-Auswertungen:

1. Heatmap: durchschnittliches Energielevel nach Zyklusphase und Tageszeit
2. Linienchart: Energie über Zyklustage
3. Musterkarten mit kurzen Texten

Beispiele für Musterkarten:

* „Deine höchste Energie liegt bisher oft vormittags in der Follikelphase.“
* „In der Lutealphase sinkt deine Energie häufiger am Nachmittag.“
* „Bei Stresswerten ab 4 ist dein Energielevel im Durchschnitt niedriger.“
* „Nach wenig Schlaf liegen deine Energieeinträge häufiger bei 1–2.“

Diese Texte müssen neutral formuliert sein und dürfen keine medizinischen Aussagen treffen.

---

### 14.8 Einstellungen

Einstellungen:

* Zyklusdaten bearbeiten
* durchschnittliche Zykluslänge
* Periodenlänge
* letztes Periodenstartdatum
* Reminder aktivieren/deaktivieren
* Reminder-Zeiten
* CSV-Export
* Datenschutzinformationen
* alle Daten löschen

---

## 15. Datenschutz

Die App soll local-first sein.

MVP-Anforderungen:

* kein Account
* keine Cloud
* keine Werbung
* keine Analytics
* keine gespeicherten Audiodateien
* strukturierte Daten lokal speichern
* Export als CSV
* alle Daten löschbar
* Mikrofonzugriff erst beim aktiven Check-in anfragen

Die App soll transparent erklären, dass Spracheingabe je nach Android-Gerät und installierter Spracherkennung lokal oder über Systemdienste verarbeitet werden kann.

---

## 16. Technische MVP-Anforderungen

Plattform:

* Native Android
* Kotlin
* Jetpack Compose

Lokale Speicherung:

* Room für Check-ins
* DataStore für Einstellungen

Spracheingabe:

* Android SpeechRecognizer
* RECORD_AUDIO Permission
* keine kontinuierliche Aufnahme
* kurze Check-ins
* Fehlerbehandlung bei fehlender Berechtigung oder fehlender Spracherkennung

Export:

* CSV-Export aller Check-ins
* optional JSON-Export später

---

## 17. Datenmodell

### EnergyCheckIn

Felder:

* id
* createdAt
* date
* time
* cycleDay
* cyclePhase
* phaseSource
* timeBlock
* energyLevel
* mood
* stressLevel
* sleepHours
* symptoms
* tags
* note
* inputMethod
* rawSpeechText
* recognitionConfidence

### CycleSettings

Felder:

* averageCycleLength
* averagePeriodLength
* lastPeriodStartDate
* phaseEstimationEnabled

### Enums

CyclePhase:

* MENSTRUATION
* FOLLICULAR
* OVULATION
* LUTEAL
* UNKNOWN

PhaseSource:

* ESTIMATED
* USER_CONFIRMED
* USER_CORRECTED

TimeBlock:

* MORNING
* LATE_MORNING
* MIDDAY
* AFTERNOON
* EVENING
* NIGHT

InputMethod:

* VOICE
* MANUAL

---

## 18. MVP-Prioritäten

Priorität 1:

* Onboarding
* Home-Screen
* Spracheingabe
* Parser
* Bestätigungs-Screen
* lokale Speicherung
* manuelle Eingabe

Priorität 2:

* Verlauf
* Einträge bearbeiten und löschen
* CSV-Export

Priorität 3:

* Heatmap
* Linienchart
* Musterkarten
* Reminder

Nicht im MVP:

* Account
* Cloud-Sync
* Abo
* Wearable-Integration
* medizinische Prognosen
* Fruchtbarkeitsvorhersage
* Community
* KI-Coaching
* Push-Marketing
* Social Features

---

## 19. Akzeptanzkriterien

Die MVP-Version ist erfolgreich, wenn:

1. Eine Nutzerin die App öffnen und per Mikrofon einen Energie-Check-in starten kann.
2. Die App einfache deutsche Spracheingaben erkennt.
3. Die App aus „Energie 3, Lutealphase, abends, müde, Stress 4“ strukturierte Daten erzeugt.
4. Die Nutzerin den Eintrag vor dem Speichern prüfen und korrigieren kann.
5. Die Daten lokal gespeichert werden.
6. Die Nutzerin Einträge manuell erstellen kann.
7. Die Nutzerin vergangene Einträge ansehen kann.
8. Die App Durchschnittswerte nach Phase und Tageszeit auswerten kann.
9. Die App eine einfache Heatmap anzeigen kann.
10. Die Nutzerin ihre Daten als CSV exportieren kann.
11. Die App ohne Account und ohne Cloud funktioniert.
12. Es werden keine Audiodateien gespeichert.
13. Mikrofonberechtigung wird erst beim aktiven Voice-Check-in angefragt.
14. Die App wirkt ruhig, minimalistisch und niedrigschwellig.

---

## 20. Beispiel für den idealen ersten Build

Der erste funktionierende Build muss nicht perfekt aussehen, aber dieser Flow soll funktionieren:

1. App startet.
2. Nutzerin gibt im Onboarding Zyklusdaten ein.
3. Home-Screen erscheint.
4. Nutzerin tippt auf Mikrofon.
5. Nutzerin sagt: „Energie 3, Lutealphase, abends, müde, Stress 4.“
6. App erkennt:

   * Energie: 3
   * Phase: Lutealphase
   * Tageszeit: Abend
   * Stimmung: müde
   * Stress: 4
7. Nutzerin tippt auf Speichern.
8. Eintrag erscheint im Verlauf.
9. Insight-Screen zeigt den neuen Wert in der passenden Phase-Tageszeit-Zelle.

---

## 21. Produktabgrenzung

Die App ist kein medizinisches Produkt.

Sie soll nicht:

* Krankheiten diagnostizieren
* Fruchtbarkeit sicher vorhersagen
* Verhütung unterstützen
* medizinische Empfehlungen geben
* Warnungen über Gesundheitszustände aussprechen

Sie soll:

* persönliche Selbstbeobachtung erleichtern
* Muster sichtbar machen
* Energie, Stimmung und Zyklusphase dokumentieren
* Export der eigenen Daten ermöglichen

---

## 22. Erfolgsgefühl der Nutzerin

Die Nutzerin soll nach dem ersten Benutzen denken:

„Das war leicht. Das kann ich wirklich regelmäßig machen.“

Nach einigen Wochen soll sie denken:

„Jetzt sehe ich zum ersten Mal, wann meine Energie wirklich da ist — und wann nicht.“

---

## 23. Kurzfassung für Claude

Baue eine minimalistische Android-App in Kotlin und Jetpack Compose, mit der Nutzerinnen ihr Energielevel im Zyklus per Spracheingabe erfassen können. Die App soll Spracheingaben wie „Energie 3, Lutealphase, abends, müde, Stress 4“ erkennen, in strukturierte lokale Daten umwandeln, vor dem Speichern bestätigen lassen und später nach Zyklusphase und Tageszeit auswerten. Die App ist local-first, ohne Account, ohne Cloud, ohne Analytics und ohne gespeicherte Audiodateien.
