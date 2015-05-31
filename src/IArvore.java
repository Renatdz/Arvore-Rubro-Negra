
/*
 *  template de ávore.
 * 
 */

public interface IArvore {
	/*
	 * Realiza rotação para esquerda.
	 */
	void left_rotate(No x);
	
	/*
	 * Realiza rotação para direita.
	 */
	void right_rotate(No x);
	
	/*
	 * Adiciona na árvore.
	 */
	void add(int k);
	
	/*
	 * Adiciona fix.
	 */
	void addFix(No z);
	
	/*
	 * Realiza transplante.
	 */
	void transplant(No u, No v);
	
	/*
	 * Realiza remoção.
	 */
	void delete(No z);
	
	/*
	 * Realiza remoção fix
	 */
	void delFix(No x);
	
	/*
	 * Encontra no;
	 */
	No encontra(int k);
}
