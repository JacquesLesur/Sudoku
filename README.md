# Sudoku
J'ai réalisé un sudoku, différent point réalisé:
-Main avec niveau 1 et niveau 2 qui transmettent le niveau dan un bundle
-la liste activity affiche une liste de 100 grilles plusieurs points négatifs
        -il faut envoyer des objets grilles dans l'adaptateur idéal avec une BDD mais moins intéressent sans, car l'on créé pleins d'objets pour utiliser une grille
        - au clic il faudrait pouvoir récupérer l'objet binder dans la liste (comme en C#) je ne sais pas si c'est possible, mais je n'ai pas trouvé
        -idéalement la grille devrait avoir un tableau[][] de Case mais vraiment difficile à envoyer dans le bundle
-lorsque l'on clic sur une grille de la liste, ça l'ouvre en fonction de son numéro l'objet grille est donc parsé et mis dans un bundle pour aller afficher la grille vers le loadGrid
-le loadGrid (à une vue GridGame) permet nettement de vérifier les nombres possibles à placer (les chiffres pas présents dans la ligne et la colonne et le groupe de case auquel elle appartient
-la GridGame permet de gérer l'affichage des cases et la réception du clic
