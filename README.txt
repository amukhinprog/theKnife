Per sincronizzare il mio codice con quello di un altro fare il pull

comando per clonare(posizionamoci sulla cartella in cui vogliamo salvare il progetto, per esempio "netBeansProjects"):
git clone https://github.com/amukhinprog/theKnife.git

comando push (per inviare le modifiche online):
git push origin main

comando pull (per scaricare le modifiche fatte dagli altri), prima di fare il pull, fare il commit:
git pull origin main


AGGIORNAMENTO:
entrare nelle impostazioni del profilo di github.com, scegliere l'opzione a sinistra impostazioni sviluppatore, generare un Tokens(classic), con nome arbitrario. Copiare il codice. Aprire netbeans. Fare il pull da Netbeans (git>remote>pull)