package co.com.cajarcol.literjacol.model;

public enum Lenguaje {
    INGLES("EN", "Ingles", "English"),
    ESPANOL("ES", "Español", "Spanish"),
    FRANCIA("FR", "Francia", "France");

    private String lenguajeGutendex;
    private String lenguajeEspanol;
    private String lenguajeIngles;


    Lenguaje(String lenguajeGutendex, String lenguajeEspanol, String lenguajeIngles) {
        this.lenguajeGutendex = lenguajeGutendex;
        this.lenguajeEspanol = lenguajeEspanol;
        this.lenguajeIngles = lenguajeIngles;
    }

    public static Lenguaje fromApi(String text){
        for (Lenguaje lenguaje : Lenguaje.values()){
            if (lenguaje.lenguajeGutendex.equalsIgnoreCase(text)){
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Ningun lenguaje encontrado: " + text);
    }

    public static Lenguaje fromEspanol(String text){
        for (Lenguaje lenguaje : Lenguaje.values()){
            if (lenguaje.lenguajeEspanol.equalsIgnoreCase(text)){
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Ningun lenguaje encontrado: " + text);
    }

    public static Lenguaje fromIngles(String text){
        for (Lenguaje lenguaje : Lenguaje.values()){
            if (lenguaje.lenguajeIngles.equalsIgnoreCase(text)){
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Ningun lenguaje encontrado: " + text);
    }
}
