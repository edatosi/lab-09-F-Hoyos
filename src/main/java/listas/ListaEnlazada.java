
package listas;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Lista simplemente enlazada genérica.
 *
 * INSTRUCCIONES PARA EL ESTUDIANTE
 * --------------------------------
 * 1. Cada método marcado con "TODO" lanza UnsupportedOperationException.
 *    Reemplace esa línea por su implementación.
 * 2. NO modifique las pruebas (src/test).
 * 3. Resuelva en orden (Etapa 1, 2, 3...): cada etapa usa las anteriores.
 * 4. Solo se permite usar nodos propios (clase Nodo). No use
 *    java.util.LinkedList ni ArrayList dentro de esta clase.
 */
public class ListaEnlazada<T> {

    /** Primer nodo de la lista (null cuando la lista está vacía). */
    private Nodo<T> cabeza;

    /** Cantidad de elementos. Debe mantenerse actualizada en cada operación. */
    private int cantidad;

    public ListaEnlazada() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    // =====================================================================
    // ETAPA 1. Estado de la lista
    // =====================================================================

    /** @return true si la lista no tiene elementos. */
    public boolean estaVacia() {
        return cantidad == 0;
    }

    /** @return número de elementos de la lista. */
    public int tamanio() {
        return cantidad;
    }

    // =====================================================================
    // ETAPA 2. Agregar en los extremos
    // =====================================================================

    /** Inserta el dato como nuevo primer elemento. */
    public void agregarAlInicio(T dato) {
        // TODO Etapa 2: cree un Nodo, enlácelo a la cabeza actual
        //               y actualice la cabeza y la cantidad.

        // 1. CREAR EL NUEVO NODO
        Nodo<T> nuevoNodo = new Nodo<T>(dato);

        // 2. CONECTAR EL NUEVO NODO A LA CABEZA ACTUAL
        nuevoNodo.setSiguiente(this.cabeza);

        // 3. CAMBIAR LA CABEZA
        this.cabeza = nuevoNodo;

        // 4. AUMENTAR LA CANTIDAD
        this.cantidad++;
    }

    /** Inserta el dato como nuevo último elemento. */
    public void agregarAlFinal(T dato) {
        // TODO Etapa 2: si la lista está vacía es igual que agregarAlInicio;
        //               si no, recorra hasta el último nodo y enlace el nuevo.

        if (estaVacia()) {
            agregarAlInicio(dato);

        } else {
            // 1. CREAR EL NUEVO NODO
            Nodo<T> nuevoNodo = new Nodo<T>(dato);

            // 2. CREAR UN PUNTERO QUE EMPIECE EN LA CABEZA
            Nodo<T> puntero = this.cabeza;

            // 3. RECORRER HASTA LLEGAR AL ÚLTIMO NODO
            while (puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
            }

            // 4. CONECTAR EL ÚLTIMO NODO CON EL NUEVO NODO
            puntero.setSiguiente(nuevoNodo);

            // 5. AUMENTAR LA CANTIDAD
            this.cantidad++;
        }
    }

    // =====================================================================
    // ETAPA 3. Consultar elementos
    // =====================================================================

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerPrimero() {
        // TODO Etapa 3: escriba su código aquí

        // 1. VERIFICAR SI LA LISTA ESTÁ VACÍA
        if (estaVacia()) {
            throw new NoSuchElementException();
        } else {
            // 2. DEVOLVER EL DATO DE LA CABEZA
            return this.cabeza.getDato();
        }
    }

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerUltimo() {
        // TODO Etapa 3: escriba su código aquí

        // 1. VERIFICAR SI LA LISTA ESTÁ VACÍA
        if (estaVacia()) {
            throw new NoSuchElementException();
        } else {
            // 2. CREAR UN PUNTERO QUE EMPIECE EN LA CABEZA
            Nodo<T> puntero = this.cabeza;

            // 3. RECORRER HASTA ENCONTRAR EL ÚLTIMO NODO
            while (puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
            }

            // 4. DEVOLVER EL DATO DEL ÚLTIMO NODO
            return puntero.getDato();
        }
    }

    /**
     * @param indice posición (0 = primer elemento)
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T obtener(int indice) {
        // TODO Etapa 3: valide el índice y recorra la lista 'indice' veces.

        // 1. VERIFICAR QUE EL ÍNDICE SEA VÁLIDO
        if (indice < 0 || indice >= this.cantidad) {
            throw new IndexOutOfBoundsException();
        } else {
            // 2. CREAR UN PUNTERO QUE EMPIECE EN LA CABEZA
            Nodo<T> puntero = this.cabeza;

            // 3. RECORRER HASTA LLEGAR AL ÍNDICE
            for (int i = 0; i < indice; i++) {
                puntero = puntero.getSiguiente();
            }

            // 4. DEVOLVER EL DATO ENCONTRADO
            return puntero.getDato();
        }
    }

    // =====================================================================
    // ETAPA 4. Búsqueda
    // =====================================================================

    /** @return true si algún elemento es igual (equals) al dato. Admite null. */
    public boolean contiene(T dato) {
        // TODO Etapa 4: use indiceDe o recorra la lista.
        //               Cuidado: para comparar use Objects.equals(a, b).

        // 1. BUSCAR LA POSICIÓN DEL DATO
        int indice = indiceDe(dato);

        // 2. VERIFICAR SI EL DATO EXISTE
        if (indice != -1) {
            return true;
        } else {
            return false;
        }
    }

    /** @return posición de la primera aparición del dato, o -1 si no está. */
    public int indiceDe(T dato) {
        // TODO Etapa 4: escriba su código aquí

        // 1. CREAR UN PUNTERO QUE EMPIECE EN LA CABEZA
        Nodo<T> puntero = this.cabeza;

        // 2. INICIAR EL CONTADOR
        int indice = 0;

        // 3. RECORRER LA LISTA
        while (puntero != null) {
            // 4. COMPARAR EL DATO ACTUAL CON EL DATO BUSCADO
            if (Objects.equals(puntero.getDato(), dato)) {
                return indice;
            } else {
                // 5. AVANZAR AL SIGUIENTE NODO
                puntero = puntero.getSiguiente();

                // 6. AUMENTAR EL ÍNDICE
                indice++;
            }
        }

        // 7. DEVOLVER -1 SI NO SE ENCONTRÓ EL DATO
        return -1;
    }

    // =====================================================================
    // ETAPA 5. Insertar y reemplazar en cualquier posición
    // =====================================================================

    /**
     * Inserta el dato en la posición indicada, desplazando los siguientes.
     * Se permite indice == tamanio() (equivale a agregar al final).
     *
     * @throws IndexOutOfBoundsException si indice < 0 o indice > tamanio()
     */
    public void insertar(int indice, T dato) {
        // TODO Etapa 5: casos: indice 0 (inicio), o detenerse en el nodo
        //               ANTERIOR a la posición y enlazar allí el nuevo nodo.

        // 1. VERIFICAR QUE EL ÍNDICE SEA VÁLIDO
        if (indice < 0 || indice > this.cantidad) {
            throw new IndexOutOfBoundsException();
        } else {
            // 2. SI EL ÍNDICE ES 0, AGREGAR AL INICIO
            if (indice == 0) {
                agregarAlInicio(dato);
            } else {
                // 3. SI EL ÍNDICE ES IGUAL AL TAMAÑO, AGREGAR AL FINAL
                if (indice == this.cantidad) {
                    agregarAlFinal(dato);
                } else {
                    // 4. CREAR EL NUEVO NODO
                    Nodo<T> nuevoNodo = new Nodo<T>(dato);

                    // 5. CREAR UN PUNTERO QUE EMPIECE EN LA CABEZA
                    Nodo<T> puntero = this.cabeza;

                    // 6. RECORRER HASTA EL NODO ANTERIOR A LA POSICIÓN
                    for (int i = 0; i < indice - 1; i++) {
                        puntero = puntero.getSiguiente();
                    }

                    // 7. CONECTAR EL NUEVO NODO CON EL SIGUIENTE
                    nuevoNodo.setSiguiente(puntero.getSiguiente());

                    // 8. CONECTAR EL NODO ANTERIOR CON EL NUEVO NODO
                    puntero.setSiguiente(nuevoNodo);

                    // 9. AUMENTAR LA CANTIDAD
                    this.cantidad++;
                }
            }
        }
    }

    /**
     * Reemplaza el dato de la posición indicada.
     *
     * @return el dato que había antes
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T reemplazar(int indice, T dato) {
        // TODO Etapa 5: escriba su código aquí

        // 1. VERIFICAR QUE EL ÍNDICE SEA VÁLIDO
        if (indice < 0 || indice >= this.cantidad) {
            throw new IndexOutOfBoundsException();
        } else {
            // 2. CREAR UN PUNTERO QUE EMPIECE EN LA CABEZA
            Nodo<T> puntero = this.cabeza;

            // 3. RECORRER HASTA LLEGAR AL ÍNDICE INDICADO
            for (int i = 0; i < indice; i++) {
                puntero = puntero.getSiguiente();
            }

            // 4. GUARDAR EL DATO ANTERIOR
            T datoAnterior = puntero.getDato();

            // 5. REEMPLAZAR EL DATO
            puntero.setDato(dato);

            // 6. DEVOLVER EL DATO ANTERIOR
            return datoAnterior;
        }
    }

    // =====================================================================
    // ETAPA 6. Eliminar
    // =====================================================================

    /**
     * Elimina y retorna el primer elemento.
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarPrimero() {
        // TODO Etapa 6: mueva la cabeza al siguiente nodo.

        // 1. VERIFICAR SI LA LISTA ESTÁ VACÍA
        if (estaVacia()) {
            throw new NoSuchElementException();
        } else {
            // 2. GUARDAR EL DATO DEL PRIMER NODO
            T datoEliminado = this.cabeza.getDato();

            // 3. MOVER LA CABEZA AL SIGUIENTE NODO
            this.cabeza = this.cabeza.getSiguiente();

            // 4. DISMINUIR LA CANTIDAD
            this.cantidad--;

            // 5. DEVOLVER EL DATO ELIMINADO
            return datoEliminado;
        }
    }

    /**
     * Elimina y retorna el último elemento.
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarUltimo() {
        // TODO Etapa 6: hay que detenerse en el PENULTIMO nodo.
        //               Atención al caso de un solo elemento.

        // 1. VERIFICAR SI LA LISTA ESTÁ VACÍA
        if (estaVacia()) {
            throw new NoSuchElementException();
        } else {
            // 2. VERIFICAR SI SOLO HAY UN ELEMENTO
            if (this.cantidad == 1) {
                return eliminarPrimero();
            } else {
                // 3. CREAR UN PUNTERO QUE EMPIECE EN LA CABEZA
                Nodo<T> puntero = this.cabeza;

                // 4. RECORRER HASTA EL PENÚLTIMO NODO
                while (puntero.getSiguiente().getSiguiente() != null) {
                    puntero = puntero.getSiguiente();
                }

                // 5. GUARDAR EL DATO DEL ÚLTIMO NODO
                T datoEliminado = puntero.getSiguiente().getDato();

                // 6. DESENLAZAR EL ÚLTIMO NODO
                puntero.setSiguiente(null);

                // 7. DISMINUIR LA CANTIDAD
                this.cantidad--;

                // 8. DEVOLVER EL DATO ELIMINADO
                return datoEliminado;
            }
        }
    }

    /**
     * Elimina y retorna el elemento de la posición indicada.
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T eliminar(int indice) {
        // TODO Etapa 6: escriba su código aquí

        // 1. VERIFICAR QUE EL ÍNDICE SEA VÁLIDO
        if (indice < 0 || indice >= this.cantidad) {
            throw new IndexOutOfBoundsException();
        } else {
            // 2. SI EL ÍNDICE ES 0, ELIMINAR EL PRIMERO
            if (indice == 0) {
                return eliminarPrimero();
            } else {
                // 3. CREAR UN PUNTERO QUE EMPIECE EN LA CABEZA
                Nodo<T> puntero = this.cabeza;

                // 4. RECORRER HASTA EL NODO ANTERIOR AL ÍNDICE
                for (int i = 0; i < indice - 1; i++) {
                    puntero = puntero.getSiguiente();
                }

                // 5. GUARDAR EL NODO QUE SE VA A ELIMINAR
                Nodo<T> nodoEliminado = puntero.getSiguiente();

                // 6. GUARDAR EL DATO DEL NODO ELIMINADO
                T datoEliminado = nodoEliminado.getDato();

                // 7. CONECTAR EL NODO ANTERIOR CON EL SIGUIENTE
                puntero.setSiguiente(nodoEliminado.getSiguiente());

                // 8. DISMINUIR LA CANTIDAD
                this.cantidad--;

                // 9. DEVOLVER EL DATO ELIMINADO
                return datoEliminado;
            }
        }
    }

    /**
     * Elimina la primera aparición del dato.
     * @return true si se eliminó algo, false si el dato no estaba
     */
    public boolean eliminarValor(T dato) {
        // TODO Etapa 6: puede apoyarse en indiceDe y eliminar(int).

        // 1. BUSCAR LA POSICIÓN DEL DATO
        int indice = indiceDe(dato);

        // 2. VERIFICAR SI EL DATO EXISTE
        if (indice == -1) {
            return false;
        } else {
            // 3. ELIMINAR EL DATO ENCONTRADO
            eliminar(indice);

            // 4. INDICAR QUE SE ELIMINÓ
            return true;
        }
    }

    /** Deja la lista vacía. */
    public void vaciar() {
        // TODO Etapa 6: escriba su código aquí

        // 1. MOVER LA CABEZA A NULL
        this.cabeza = null;

        // 2. REINICIAR LA CANTIDAD
        this.cantidad = 0;
    }

    // =====================================================================
    // ETAPA 7. Representación en texto
    // =====================================================================

    /**
     * Formato: "[1, 2, 3]". Lista vacía: "[]".
     * Un elemento null se imprime como "null".
     */
    @Override
    public String toString() {
        // TODO Etapa 7: recorra la lista con un StringBuilder.

        // 1. CREAR EL TEXTO CON EL CORCHETE INICIAL
        StringBuilder resultado = new StringBuilder("[");

        // 2. CREAR UN PUNTERO QUE EMPIECE EN LA CABEZA
        Nodo<T> puntero = this.cabeza;

        // 3. RECORRER TODOS LOS NODOS
        while (puntero != null) {
            // 4. AGREGAR EL DATO AL TEXTO
            resultado.append(puntero.getDato());

            // 5. AVANZAR AL SIGUIENTE NODO
            puntero = puntero.getSiguiente();

            // 6. AGREGAR LA COMA SI HAY OTRO ELEMENTO
            if (puntero != null) {
                resultado.append(", ");
            }
        }

        // 7. AGREGAR EL CORCHETE FINAL
        resultado.append("]");

        // 8. DEVOLVER EL TEXTO
        return resultado.toString();
    }

    // =====================================================================
    // ETAPA 8. Retos
    // =====================================================================

    /**
     * Invierte la lista EN SITIO: no cree nodos nuevos, solo cambie
     * las referencias 'siguiente' de los nodos existentes.
     */
    public void invertir() {
        // TODO Etapa 8: use tres referencias: anterior, actual, siguiente.

        // 1. CREAR LAS TRES REFERENCIAS
        Nodo<T> anterior = null;
        Nodo<T> actual = this.cabeza;
        Nodo<T> siguiente = null;

        // 2. RECORRER LA LISTA
        while (actual != null) {
            // 3. GUARDAR LA REFERENCIA AL SIGUIENTE NODO
            siguiente = actual.getSiguiente();

            // 4. CAMBIAR LA DIRECCIÓN DEL ENLACE
            actual.setSiguiente(anterior);

            // 5. AVANZAR EL PUNTERO ANTERIOR
            anterior = actual;

            // 6. AVANZAR EL PUNTERO ACTUAL
            actual = siguiente;
        }

        // 7. ACTUALIZAR LA CABEZA
        this.cabeza = anterior;
    }

    /**
     * Retorna el elemento del medio. Si el tamaño es par, el segundo de los
     * dos centrales (ej.: [1,2,3,4] -> 3). Pista: dos punteros, uno avanza
     * de a 1 y otro de a 2.
     *
     * @throws NoSuchElementException si la lista está vacía
     */
    public T obtenerMedio() {
        // TODO Etapa 8: escriba su código aquí

        // 1. VERIFICAR SI LA LISTA ESTÁ VACÍA
        if (estaVacia()) {
            throw new NoSuchElementException();
        } else {
            // 2. CREAR DOS PUNTEROS QUE EMPIECEN EN LA CABEZA
            Nodo<T> lento = this.cabeza;
            Nodo<T> rapido = this.cabeza;

            // 3. AVANZAR LENTO UNA POSICIÓN Y RÁPIDO DOS POSICIONES
            while (rapido.getSiguiente() != null
                    && rapido.getSiguiente().getSiguiente() != null) {
                lento = lento.getSiguiente();
                rapido = rapido.getSiguiente().getSiguiente();
            }

            // 4. SI EL TAMAÑO ES PAR, AVANZAR AL SEGUNDO CENTRAL
            if (rapido.getSiguiente() != null) {
                lento = lento.getSiguiente();
            }

            // 5. DEVOLVER EL DATO CENTRAL
            return lento.getDato();
        }
    }

    /**
     * Elimina los elementos repetidos dejando solo la primera aparición
     * de cada uno, conservando el orden. Ej.: [1,2,1,3,2] -> [1,2,3]
     */
    public void eliminarDuplicados() {
        // TODO Etapa 8: escriba su código aquí

        // 1. CREAR UN PUNTERO QUE EMPIECE EN LA CABEZA
        Nodo<T> actual = this.cabeza;

        // 2. RECORRER LOS NODOS DE LA LISTA
        while (actual != null) {
            // 3. CREAR UN PUNTERO PARA REVISAR LOS SIGUIENTES NODOS
            Nodo<T> puntero = actual;

            // 4. RECORRER LOS NODOS QUE VIENEN DESPUÉS DEL ACTUAL
            while (puntero.getSiguiente() != null) {
                // 5. COMPARAR EL DATO ACTUAL CON EL SIGUIENTE
                if (Objects.equals(
                        actual.getDato(),
                        puntero.getSiguiente().getDato())) {

                    // 6. GUARDAR EL NODO REPETIDO
                    Nodo<T> repetido = puntero.getSiguiente();

                    // 7. SALTAR EL NODO REPETIDO
                    puntero.setSiguiente(repetido.getSiguiente());

                    // 8. DISMINUIR LA CANTIDAD
                    this.cantidad--;

                } else {
                    // 9. AVANZAR AL SIGUIENTE NODO
                    puntero = puntero.getSiguiente();
                }
            }

            // 10. AVANZAR AL SIGUIENTE NODO PRINCIPAL
            actual = actual.getSiguiente();
        }
    }
}
