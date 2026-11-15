# TP 3 : Du Servlet au MVC — Réponses

## Question 1 : Pourquoi utiliser `forward` si le login échoue et `sendRedirect` s'il réussit ?

**Échec → forward :**

On utilise `forward` parce qu'on reste dans la même requête. Donc, les données comme le login et le message d'erreur restent disponibles dans la JSP.

**Succès → sendRedirect :**

On utilise `sendRedirect` pour envoyer l'utilisateur vers le dashboard.

Cela permet aussi de changer l'URL vers `/dashboard`. Si l'utilisateur fait F5, le navigateur recharge la page sans renvoyer le formulaire de connexion.

C'est le principe **Post/Redirect/Get (PRG)**.

## Question 2 : Que se passe-t-il si on fait F5 après l'ajout d'un produit ?

**(a) Avec forward :**

Après l'ajout, le serveur affiche la liste avec `forward`. Mais le navigateur reste sur la requête POST.

Si on fait F5, le navigateur peut renvoyer le formulaire et ajouter le même produit une deuxième fois.

**(b) Avec redirect :**

Après l'ajout, le serveur redirige vers `/produits` avec `sendRedirect`.

Le navigateur fait alors une nouvelle requête GET. Si on fait F5, il recharge seulement la liste sans ajouter un autre produit.

Ce principe s'appelle **PRG (Post/Redirect/Get)**.

## Question 3 : XSS avec `<script>alert('XSS')</script>` comme nom de produit

- **Avec `${produit.nom}` :** le contenu est affiché directement dans la page HTML. Le navigateur peut donc exécuter le script. C'est une attaque XSS stockée, car le script reste dans la base de données.
- **Avec `<c:out value="${produit.nom}"/>` :** les caractères spéciaux sont transformés. Le navigateur affiche le script comme du texte sans l'exécuter.

**Conclusion :** il faut toujours échapper les données saisies par les utilisateurs avant de les afficher dans une page HTML.

## Question 4 : Pourquoi utiliser un filtre au lieu de vérifier dans chaque Servlet ?

Le filtre présente plusieurs avantages :

- On écrit la vérification une seule fois au lieu de la répéter dans chaque Servlet.
- On évite d'oublier de protéger une nouvelle Servlet.
- Le code est plus facile à modifier et à maintenir.
- Les Servlets restent concentrées sur leur travail principal.

**Autres utilisations des filtres :**

1. Gérer l'encodage UTF-8.
2. Enregistrer les requêtes dans les logs.
3. Ajouter des en-têtes de sécurité.
4. Gérer le cache et la compression des réponses.