#!/bin/bash

# Usage : ./inserer_ligne.sh <fichier> <NomClient>
# Exemple : ./inserer_ligne.sh HotelService.java Hilton

fichier="$1"
client="$2"

if [[ -z "$fichier" || -z "$client" ]]; then
  echo "Usage : $0 <fichier> <NomClient>"
  exit 1
fi

ligne="        this.agences.add(new ${client}Client());"

# Vérifie si la ligne existe déjà dans le fichier
if grep -qF "$ligne" "$fichier"; then
  #echo "⚠️  La ligne '$ligne' existe déjà dans $fichier — aucune modification."
  exit 0
fi

# Détection du type de sed (GNU vs BSD/macOS)
if sed --version >/dev/null 2>&1; then
  # GNU sed (Linux)
  sed -i "/ICI/i $ligne" "$fichier"
else
  # BSD sed (macOS)
  sed -i '' "/ICI/i\\
$ligne
" "$fichier"
fi

#echo "✅ Ligne ajoutée avant 'devenirPartenaire();' dans $fichier"
