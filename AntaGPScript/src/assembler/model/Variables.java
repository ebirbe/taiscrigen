package assembler.model;

public class Variables {
	public static boolean B[] = new boolean[5];
	public static boolean DA[] = new boolean[10];
	public static boolean DPi[] = new boolean[10];
	public static boolean DPp[] = {
		true,true,true,true,true, // Los primeros 10
		true,true,true,true,true, // No van pal vaile
		false,false,false,false,false,
	};
	public static boolean ED[] = new boolean[50];
	public static boolean J[] = new boolean[5];
	public static boolean TD[] = new boolean[10];
	public static boolean S[] = new boolean[10];
	/**
	 * Ocupar una variable del sistema
	 * @param var
	 * @return
	 * @throws Exception
	 */
	public static Integer ocupar(boolean[] var) throws Exception{
		return ocupar(var, -1);
	}
	
	/**
	 * Liberar una variable del sistema
	 * @param var
	 * @param pos
	 * @return
	 * @throws Exception
	 */
	public static Integer ocupar(boolean[] var, int pos) throws Exception{
		// si se indica una posicion
		if(pos >= 0){
			if (pos >= var.length) throw new IndexOutOfBoundsException("El valor excede el maximo permitido.");
			// si la posicion esta ocupada ya lanza una excepcion
			if(var[pos]) throw new Exception("La posicion ya esta siendo utilizada");
			// sino la ocupamos
			else{
				var[pos] = true;
				return pos;
			}
			// si no se indica una posicion
		}else{
			// buscamos la proxima vacia
			for(pos=0;pos<var.length;pos++){
				// si esta vacia
				if(!var[pos]){
					// la ocupamos
					var[pos] = true;
					// dejamos de buscar y retornamos el valor
					return pos;
				}
			}
			// si se termina de buscar se indica que no hay espacio
			throw new Exception("No hay variables disponibles");
		}
	}

	public static void liberar(boolean[] var, Integer pos){
		var[pos] = false;
	}
}
