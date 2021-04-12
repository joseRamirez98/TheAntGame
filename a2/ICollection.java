/**
 * 
 */
package com.mycompany.a2;

public interface ICollection {
	public void add(GameObject newGameObject);
	public void remove(GameObject gameObject);
	public void clear();
    public IIterator getIterator();
}
