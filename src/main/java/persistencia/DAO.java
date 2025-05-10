
package persistencia;

import java.util.List;

public interface DAO<T, K> {
    
    public boolean insertar(T a);
    
    public int modificar(T a);
    
    public List listar();
    
    public List<T> buscar(K a);
    
    //Agregar dar de baja para todo
    
}
