public class Evenement {

    private String date;         // La date de l'événement (peut être un entier ou un type de date approprié)
    private String heureDebut; // Heure de début au format HH:MM
    private String heureFin;   // Heure de fin au format HH:MM
    private String description; // Description de l'événement

    public Evenement(String date, String heureDebut, String heureFin, String description) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "Date: " + date + ", Heure de début: " + heureDebut + ", Heure de fin: " + heureFin + ", Description: " + description;
    }
}
