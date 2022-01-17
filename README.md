
# Gestionale Spedizioni

Progetto per l'esame di **Programmazione a Oggetti** per l'anno accademico 2020/2021 - UniMoRe.

Rappresenta un gestionale per delle spedizioni (fittizie) con dashboard _utente_ e _amministratore_ ed esecuzione delle spedizioni su un **thread in background**.

## Start Project

Assicurati di aver installato il `JDK` per poter eseguire il programma. Se non lo hai, puoi installarlo [da qui.](https://www.oracle.com/java/technologies/downloads/)

Clona il progetto eseguendo sul terminale (o PowerShell) il seguente comando:
```console
git clone https://github.com/dariowskii/gestionale_spedizioni.git
```
Dopodiché entra nella cartella appena creata **gestionale_spedizioni** e nella sottocartella dei file eseguibili **out** ed esegui il programma con:
```console
java com.dariovarriale.Main
```

## Caratteristiche del programma

Al primo avvio, il programma lancia la `WelcomeScreen` per poter far accedere o registrare un nuovo utente oppure dare la possibilità all'**Admin** di entrare nella _dashboard_ e far avanzare le spedizioni - se presenti.

### Utente Standard

Cliccando su `Registrati` si entra nell'apposita schermata di registrazione. Una volta compilati i dati in modo corretto si ha un messaggio di benvenuto e si viene indirizzati verso la `Dashboard` dell'utente.

All'interno della schermata si possono aggiungere spedizioni **normali**, indicando `destinazione` e `peso`, o **assicurate** aggiungendo anche il `valore` del pacco. 
Il `codice` è l'unico campo non modificabile dall'utente in quanto rappresenta l'eventuale **futuro codice di spedizione** del pacco, necessario per poter essere processato dall'**Admin**.

Una volta aggiunta la spedizione, la si può osservare nella **tabella delle spedizioni** nella dashboard.

Se ci dovesse essere una spedizione **assicurata** non andata a buon fine, l'utente può chiedere il rimborso facendo **click destro** sulla spedizione e selezionando **Richiedi rimborso**.

### Utente Amministratore (Admin)

Per poter far avanzare le spedizioni, bisogna effettuare il login come **amministratore**.

Nella `WelcomeScreen` clicca su **Sei un Admin? Clicca qui.** per lanciare la finestra di login dedicata agli amministratori.

In questo caso, le credenziali sono _hardcoded_:

***username:*** `admin`
***password:*** `password`

Dopo aver eseguito il login si viene indirizzati verso la dashboard amministratore, nella quale si può osservare la tabella delle spedizioni di **tutti gli utenti**.

Cliccando sul bottone **Avvia Spedizioni**, si avvierà un _thread_ in background che aggiornerà gli stati delle spedizioni dei vari utenti, considerando una percentuale di fallimento del **33%**.

Si può osservare il cambiamento dello stato delle spedizioni grazie al cambio dei colori delle righe della tabella - giallo, verde o rosso in base allo stato.

Le spedizioni una volta terminate - in stato **RICEVUTA**, **FALLITA** (non assicurata) o **RIMBORSO EROGATO** - possono essere cancellate dal sistema facendo **click destro** sulla spedizione interessata e selezionando **Cancella dal sistema**.

## Features

 - **Login rapido**:
	 Se un utente normale (**non Admin**) loggato chiude l'applicazione senza eseguire correttamente il logout - da **Menù > Salva ed esci** - viene salvato il riferimento in memoria, consentendo al nuovo riavvio di avere un accesso facilitato per quell'utente. Al primo tentativo errato di immissione della password, si viene rimandati alla `WelcomeScreen` e il riferimento viene cancellato.
