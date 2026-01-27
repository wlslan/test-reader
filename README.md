# test-reader
## Darba autors
Ilgvars Ročāns
## Projekta apraksts
Programmas mērķis ir ļaut skolotājiem nobildēt vai ieskenēt savu skolēnu rakstītos pārbaudes darbus, bildi ievadīt programmā un automātiski salīdzināt viņu atbildes ar pareizajām. Tāpēc tā skaitas datorredzes programma – tāda, kas nosaka kādi priekšmeti ir redzami attēlos. Es līdz idejai nonācu, iedvesmojoties no skolotāja dotajām programmas idejām. Man interesēja šī ideja, jo tā, manuprāt, varētu būt diezgan noderīga un viegli izveidojama. Turklāt, šī programma ir īpaša ar to, ka, kaut vai tā ir datorredzes programma, tā ir pietiekami vienkārša, lai to varētu izpildīt manuāli rakstot algoritmu objekta atpazīšanai, neizmantojot mašīnmācīšanos kā daudzas citas datorredzes programmas.

Šai programmai ir vairākas daļas. Pirmkārt, kad skolotājam ir gatavs pārbaudes darbs, tam ir jāspēj kaut kā programmā saglabāt, kur atrodas katrs skolēniem izvēlamais atbilžu variants, un kā tos ir jāvērtē. Tad skolotājam ir jānobildē vai jāskenē katrs pabeigtais pārbaudes darbs un tos jāievieto programmā. Programmai jāspēj fotogrāfijas pārveidot, lai darbam bildē būtu taisntūrveida forma un lai būtu vieglāk noteikt katra uzdevuma un atbildes novietojumu attēlā. Pēc tam no attēla programmai jānosaka kādas atbildes tika izvēlētas uz katru jautājumu. Tās tiks novērtētas atkarībā no dotajiem kritērijiem.

Programmas fotogrāfiju pārveidošana būtu diezgan ierobežota. Bildēs var būt tikai viens pārbaudes darbs uz plakanas virsmas. Vairāki pārbaudes darbi vai citi priekšmeti traucētu ar darba atrašanu bildē, un, ja tas nav uz plakanas virsmas, tas būs ielocīts un precīzi apgriezt transformāciju nebūs iespējami. Programma strādā, nosakot pārbaudes darba lapas četru virsotņu atrašanās vietas attēlā, aprēķinot lineārās transformācijas matricu, kas atbilst šīm virsotnēm, atrodot tās inverso matricu un reizinot to ar katru punkta koordinātām attēlā.

Programma nav paredzēta teksta atbilžu atpazīšanai. No tā nebūtu jēgas, jo brīvas teksta atbildes tāpat parasti nevar tieši salīdzināt ar iepriekš zināmām, tāpēc datorredze ar tiem nepalīdzētu. Tāpēc programma atpazīs tikai izvēles variantu uzdevumus: pārbaudes darbā būs atzīmēti konkrēti laukumi izvēlēm un pareizās atbildes tajos. Programma varēs noteikt izvēlētos laukumus, iespējams, aprēķinot vidējo krāsu katrā atbilžu laukā un salīdzinot to ar papīra krāsu citur. Tas nozīmē, ka fotogrāfijām nav nepieciešama ļoti laba kvalitāte.

Darbā atbildes būtu atzīmētas, iekrāsojot reģionus Word dokumentā, izvēloties reģionus attēlā vai kādā līdzīgā veidā. Ja būtu vienošanās par pārbaudes darba formātu, tad šo soli nevajadzētu atkārtoti veikt. Lietotājs arī izvēlētos, kā atbildes tiek vērtētas, lai varētu pieļaut uzdevumus ar vairākām atbildēm, laukumus kļūdu labojumiem u.t.t.

Programma būs datora lietotne. Kaut vai telefona lietotne atļautu vieglāk ievietot nofotogrāfētus pārbaudes darbus, datora lietotne ir piemērotāka skenētu attēlu ievietošanai ir pārbaudes darbu atbilžu un jautājumu izvietojumu mainīšanai.
## Risinājuma arhitektūra
Risinājuma arhitektūra būs redzama failā docs/model.png (pagaidām nepabeigts)
## Darba plāns
(nepabeigts)