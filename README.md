# Gestion-de-reservations-de-chambres-et-de-salles

Au lancement, définir la vartiable d'env **SPRING_DATASOURCE_PASSWORD** avec le MDP BDD

Pour faire des tests en local, commande docker:
`docker run --rm -e POSTGRES_PASSWORD=test -e POSTGRES_USER=test -e POSTGRES_DB=test -p 5432:5432 postgres:latest`

Variables d'env pour le test local: `SPRING_DATASOURCE_PASSWORD=test;SPRING_DATASOURCE_USERNAME=test;SPRING_DATASOURCE_URL=jdbc:postgresql://127.0.0.1:5432/test`

Une fois lancé, le panel de gestion est disponible à http://localhost:8080/web/reservations
Il n'y a pas de système de login pour le moment, l'équipe de sécurité devra s'en charger au déploiement.

Le JAR généré est disponible, `reservation-service-0.0.1-SNAPSHOT.jar`.