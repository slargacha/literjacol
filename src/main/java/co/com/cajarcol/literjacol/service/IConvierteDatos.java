package co.com.cajarcol.literjacol.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
