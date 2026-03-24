## Programmas palaišana:
* Klonē programmas Git repozitoriju
* Kompilē Java kodu no direktorija src, vai palaid ar IDE, piemēram, IntelliJ
## Programmas testēšana:
* Programmu palaižot, redz tikai pogas "Rediģēt pārbaudes darba formātu" un "Pārbaudīt pārbaudes darbus"
* Katra darbība, kas atver dialoglodziņu, tiek atcelta, ja dialoglodziņš ir aizvērts manuāli vai tiek nospiesta "Cancel" poga .
* Kad tiek prasīts izvēlēties attēla failu, cita vieda failus nav iespējami izvēlēties
* Nospiežot pogu "Rediģēt pārbaudes darba formātu", var izvēlēties vai izveidot jaunu formātu, vai rediģēt pastāvošu, ja ir iepriekš izveidots, kā arī izdzēst izvēlēto formātu ar izdzēšanas apstiprināšanas dialoglodziņu. Pēc izvēles programmas galvenā sadaļa pazūd un parādās pārbaudes darba reģidēšanas sadaļa
  * Ja tiek izveidots jauns formāts, tiek piedāvāts izvēlēties tieši vienu attēla failu. Vairākus failus nevar izvēlēties.
* Pārbaudes darba reģidēšanas sadaļā:
  * Ja tiek atvērts pastāvošs formāts, attēls un visi jautājumu un atbilžu dati sakrīt ar saglabātajiem, kas atbilst formātam ar izvēlēto nosaukumu
  * Kad ir izvēlēts pārbaudes darba formāts rediģēšanai, rādās:
    * augšējā sadaļa ar pogu Fails, zem kā ir pogas Saglabāt un Aizvērt
    * pārbaudes darba attēls,
    * Atbilžu sadaļa, kas sākotnēji ir tukša
    * Jautājumu sadaļa, kas sākotnēji ir tukša un satur pogas "Reģidēt", "Pievienot", "Izdzēst"
  * Jautājumu sadaļā:
    * Spiežot "Pievienot", jautājumu saraksta beigām tiek pievienots jautājuma apraksts (numurs un veids)
    * Spiežot "Rediģēt", ja ir izvēlēts jautājums, parādās dialoglodziņš, kas ļauj mainīt jautājuma veidu (vienas atbildes vai vairāku izvēļu)
    * Spiežot "Izdzēst", ja ir izvēlēts jautājums, tas tiek izdzēsts no saraksta, nemainot pārējo jautājumu relatīvo secību
  * Atbilžu sadaļā:
    * Kamēr ir izvēlēts jautājums Jautājumu sadaļā, rādās Jautājumu sadaļai līdzīgs saraksts, arī ar pogām "Reģidēt", "Pievienot", "Izdzēst"
    * Katram jautājumam ir savs atbilžu saraksts, kas mainās, izvēloties dažādus jautājumus
    * Katra izvēlētā jautājuma atbilde tiek attēlota uz darba attēla: ar sarkaniem vai zaļiem taisnstūriem, atkarībā no tā, vai atbilde ir pareiza, un atbilstoši izvēlētajam reģionām
    * Spiežot "Pievienot", kļūst iespējami uz darba attēla atzīmēt taisnstūrveida reģionu, pēc kā atbilžu saraksta beigām tiek pievienots atbildes apraksts (numurs un vai tas ir pareizs, vai nē)
    * Spiežot "Rediģēt", ja ir izvēlēta atbilde, parādās dialoglodziņš, kas ļauj mainīt atbildes pareizību, kā arī mainīt izvēlēto reģionu.
    * Spiežot "Izdzēst", ja ir izvēlēta atbilde, tā tiek izdzēsta no saraksta, nemainot pārējo atbilžu relatīvo secību
  * Pēc katras darbības šajās sadaļās uzreiz rādās nepieciešamās sarakstu un attēla maiņas, piemēram atbilžu taisnstūra krāsa mainās, mainot tā pareizību
  * Pārbaudes darbu ir iespējams saglabāt ar pogu Fails -> Saglabāt. Ja tas nav iepriekš saglabāts, tiek piedāvāts izvēlēties tā nosaukumu.
  * Ir iespējams atgriesties uz galveno sadaļu, spiežot pogu Fails -> Aizvērt. Ja kāda darbība ir tikusi veikta pēc iepriekšējās saglabāšanas, tiek piedāvāts atcelt aizvēršanu
* Nospiežot pogu "Pārbaudīt pārbaudes darbus", var izvēlēties pastāvošu pārbaudes darba formātu vai izdzēst izvēlēto. Var izvēlēties skolēnu izpildīto pārbaudes darbu attēlus. Pēc izvēles, tiek atvērts jauns .csv fails, kas satur katra skolēna punktu skaitu katrā jautājumā un kopā, izmantojot failu nosaukumus, lai identificētu pārbaudes darbus.
  * Vērtēšanas kritēriji: Katram jautājumam, punktu iegūst ja un tikai ja pārbaudes darbā ir atzīmeta vienīgā (vai visas, ja ir vairāku izvēļu jautājums) atbilde, un neviena nepareizā atbilde.
* **Pagaidām nav atbalstīta pārbaudes darbu fotogrāfiju vērtēšana. Attēli ir jāizveido, pārbaudes darbus skenējot.**
* **Paredzētais pārbaudes darbu formāts: katra jautājuma izvēlamās atbildes ir tukši, taisnstūrveida laukumi. Skolēns, izvēloties atbildi, to iekrāso ar tumšu krāsu. Programmai nav paredzēts atpazīt jebkādu tekstu un simbolus, tikai izvēļu jautājumus.**