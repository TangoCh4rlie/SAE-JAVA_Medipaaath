class Sommet:
    def __init__(self,nom,type):
        self.nom = nom
        self.type = type
        self.voisins = []
        self.aretes = []

    def ajout_voisin(self,sommet2):
        self.voisins.append(sommet2)
        sommet2.voisins.append(self)

    def ajout_arrete(self,sommet2,fiabilite,distance,temps):
        arete = Arete(self,sommet2,fiabilite,distance,temps)
        arete2 = Arete(sommet2,self,fiabilite,distance,temps)
        self.aretes.append(arete)
        sommet2.aretes.append(arete2)
    
    def affiche_voisins(self):
        i = 0
        longueur = len(self.voisins)
        while i < longueur:
            print(S1.voisins[i].nom)
            i += 1

    def sommet_plus_proche(self):
        min = self.aretes[0]
        i = 0
        longueur = len(self.voisins)
        while i < longueur:
            if self.aretes[i].distance < min.distance:
                min = self.aretes[i].arrivee
            i += 1
        return min.arrivee.nom
    
    def recherche_type(self,type):
        i = 0
        longueur = len(self.voisins)
        while i < longueur:
            if self.voisins[i].type == type:
                return self.voisins[i].nom
            i += 1
        return "Aucun sommet de ce type"
    
    def chemin_plus_fiable(self):
        fiabilite = self.aretes[0].fiabilite
        s_fiable = self.aretes[0]
        i = 0
        longueur = len(self.voisins)
        while i < longueur:
            if self.aretes[i].fiabilite > fiabilite:
                fiabilite = self.aretes[i].fiabilite
                s_fiable = self.aretes[i]
            i += 1
        return s_fiable.arrivee.nom
    
    def chemin_plus_rapide(self):
        temps = self.aretes[0].temps
        s_rapide = self.aretes[0]
        i = 0
        longueur = len(self.voisins)
        while i < longueur:
            if self.aretes[i].temps < temps:
                temps = self.aretes[i].temps
                s_rapide = self.aretes[i]
            i += 1
        return s_rapide.arrivee.nom

class Arete:
    def __init__(self,depart,arrivee,fiabilite,distance,temps):
        self.depart = depart
        self.arrivee = arrivee
        self.distance = distance
        self.fiabilite = fiabilite
        self.temps = temps

S1 = Sommet("S1","M")
S2 = Sommet("S2","M")
S3 = Sommet("S3","O")

S1.ajout_voisin(S2)
S1.ajout_voisin(S3)

S1.ajout_arrete(S2,4,25,50)
S1.ajout_arrete(S3,10,31,37)

print(S1.chemin_plus_rapide())


