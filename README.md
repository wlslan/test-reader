# test-reader
## Darba autors
Ilgvars Ročāns
## Projekta apraksts
Programmas mērķis ir ļaut skolotājiem nobildēt vai ieskenēt savu skolēnu rakstītos pārbaudes darbus, bildi ievadīt programmā un automātiski salīdzināt viņu atbildes ar pareizajām. Tāpēc tā skaitas datorredzes programma – tāda, kas nosaka kādi priekšmeti ir redzami attēlos. Es līdz idejai nonācu, iedvesmojoties no skolotāja dotajām programmas idejām. Man interesēja šī ideja, jo tā, manuprāt, varētu būt diezgan noderīga un viegli izveidojama. Turklāt, šī programma ir īpaša ar to, ka, kaut vai tā ir datorredzes programma, tā ir pietiekami vienkārša, lai to varētu izpildīt manuāli rakstot algoritmu objekta atpazīšanai, neizmantojot mašīnmācīšanos kā daudzas citas datorredzes programmas.

Šai programmai ir vairākas daļas. Pirmkārt, kad skolotājam ir gatavs pārbaudes darbs, tam ir jāspēj kaut kā programmā saglabāt, kur atrodas katrs skolēniem izvēlamais atbilžu variants, un kā tos ir jāvērtē. Tad skolotājam ir jānobildē vai jāskenē katrs pabeigtais pārbaudes darbs un tos jāievieto programmā. Programmai jāspēj fotogrāfijas pārveidot, lai darbam bildē būtu taisntūrveida forma un lai būtu vieglāk noteikt katra uzdevuma un atbildes novietojumu attēlā. Pēc tam no attēla programmai jānosaka kādas atbildes tika izvēlētas uz katru jautājumu. Tās tiks novērtētas atkarībā no dotajiem kritērijiem.

Programmas fotogrāfiju pārveidošana būtu diezgan ierobežota. Bildēs var būt tikai viens pārbaudes darbs uz plakanas virsmas. Vairāki pārbaudes darbi vai citi priekšmeti traucētu ar darba atrašanu bildē, un, ja tas nav uz plakanas virsmas, tas būs ielocīts un precīzi apgriezt transformāciju nebūs iespējami. Programma strādā, nosakot pārbaudes darba lapas četru virsotņu atrašanās vietas attēlā, aprēķinot lineārās transformācijas matricu, kas atbilst šīm virsotnēm, atrodot tās inverso matricu un reizinot to ar katru punkta koordinātām attēlā.

Programma nav paredzēta teksta atbilžu atpazīšanai. No tā nebūtu jēgas, jo brīvas teksta atbildes tāpat parasti nevar tieši salīdzināt ar iepriekš zināmām, tāpēc datorredze ar tiem nepalīdzētu. Tāpēc programma atpazīs tikai izvēles variantu uzdevumus: pārbaudes darbā būs atzīmēti konkrēti laukumi izvēlēm un pareizās atbildes tajos. Programma varēs noteikt izvēlētos laukumus, iespējams, aprēķinot vidējo krāsu katrā atbilžu laukā un salīdzinot to ar papīra krāsu citur. Tas nozīmē, ka fotogrāfijām nav nepieciešama ļoti laba kvalitāte.

Darbā atbildes būtu atzīmētas, iekrāsojot reģionus Word dokumentā, izvēloties reģionus attēlā vai kādā līdzīgā veidā. Ja būtu vienošanās par pārbaudes darba formātu, tad šo soli nevajadzētu atkārtoti veikt. Lietotājs arī izvēlētos, kā atbildes tiek vērtētas, lai varētu pieļaut uzdevumus ar vairākām atbildēm, laukumus kļūdu labojumiem u.t.t.

Galvenās tehnoloģijas programmā ir lineārās transformācijas attēliem un objektu malu noteikšana attēlos. 

Programma būs datora lietotne. Kaut vai telefona lietotne atļautu vieglāk ievietot nofotogrāfētus pārbaudes darbus, datora lietotne ir piemērotāka skenētu attēlu ievietošanai un pārbaudes darbu atbilžu un jautājumu izvietojumu mainīšanai.
## Risinājuma arhitektūra
Risinājuma arhitektūra redzama failā docs/model.png.
## Darba plāns
| Nedēļa | Darbība | Darītājs |
| ------ | ------- | -------- |
| 1 | Izveidot grafiskās lietotāja saskarnes sistēmu | Ilgvars Ročāns |
| 2 | Izveidot failu augšuplādēšanas sistēmu | Ilgvars Ročāns |
| 3 | Izveidot pārbaudas darba izkārtojuma reģidēšanas sistēmu | Ilgvars Ročāns |
| 4 | Izveidot sistēmu, ar ko var vērtēt pārbaudes darbus, izmantojot to pareizo atbilžu izkārtojumus un noskenētos pārbaudes darbus | Ilgvars Ročāns |
| 5 | Izveidot sistēmu, kas var pārveidot attēlus, izmantojot matricas | Ilgvars Ročāns |
| 6 | Izveidot sistēmu, kas attēlos var noteikt papīra lapas stūru novietojumu | Ilgvars Ročāns |
| 7 | Izveidot attēlu pārveidošanas sistēmu, kas papīra lapu fotogrāfijas pārveido, lai lapas būtu taisnstūrveida un vertikālas attēlos | Ilgvars Ročāns |
| 8 | Izveidot sistēmu, kas ļauj fotogrāfijās no krāsām noteikt, kur uz lapas ir tukšums un kur - izdrukāti vai uzrakstīti laukumi | Ilgvars Ročāns |
| 9 | Izveidot izmēģinājuma pārbaudes darbus testēšanai, prasīt skolotājiem atzīmēt pareizās atbildes atbilstoši prasībām, prasīt skolēniem tos aizpildīt un noskenēt un nobildēt tos dažādos apstākļos, ievietot tos programmā un noteikt, vai noteiktie punktu skaiti atbilst īstajiem. | Ilgvars Ročāns |
| 10 | Sagatavot programmu prezentēšanai, kompilējot to kā palaižamu programmu, lai to varētu rādīt prezentējot, un izveidot prezentāciju par programmu | Ilgvars Ročāns |