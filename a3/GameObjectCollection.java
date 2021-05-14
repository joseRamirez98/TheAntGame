package com.mycompany.a3;

import java.util.Vector;

public class GameObjectCollection implements ICollection{
	
	private Vector<GameObject> gameObjects;

	public GameObjectCollection() {
		// instantiate a new Vector
		gameObjects = new Vector<GameObject>();
	}

	@Override
	public void add(GameObject newGameObject) {
		// add a object to the end of the vector
		gameObjects.addElement(newGameObject);
	}
	
	@Override
	public void clear() {
		// clear all the objects in the vector
		gameObjects.clear();
	}
	
	@Override
	public void remove(GameObject gameObject) {
		// remove the Game Object parameter from the collection
		gameObjects.remove(gameObject);
	}

	@Override
	public IIterator getIterator() {
		return new GameObjectVectorIterator();
	}
	
	private class GameObjectVectorIterator implements IIterator {
		private int currElementIndex;
		
		public GameObjectVectorIterator() {
			currElementIndex = -1;
		}

		@Override
		// Check if the Game Objects Vector
		// is empty or if the currElementIndex is -1.
		// Returns true if Game Object Vector contains
		// at least 1 object.
		public boolean hasNext() {
			
			if(gameObjects.size() <= 0) 
				return false;
			if(currElementIndex == gameObjects.size() - 1)
				return false;
			return true;
		}

		@Override
		/*
		 * Since the currElementIndex is initialized to -1,
		 * to get the first element in the Vector you must
		 * increase the currElementIndex by 1.
		 * Any time you want the next element, the currElementIndex
		 * will be increased and will return the next object
		 * in the Vector.
		 */
		public Object getNext() {
			// TODO Auto-generated method stub
			currElementIndex ++;
			return gameObjects.elementAt(currElementIndex);
		}

	}

}
