package main.java.miscellaneous;

import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractListModel;

public class RefreshableListModel<E> extends AbstractListModel<E> {

	private List<E> items;
	
	public RefreshableListModel(List<E> items) {
		this.items = items;
	}
	
	@Override
	public E getElementAt(int i) {
		return items.get(i);
	}

	@Override
	public int getSize() {
		return items.size();
	}

	public void refreshList(E[] items) {
		this.items = Arrays.asList(items);
	}
	
	public void addElement(E element) {
		items.add(element);
	}
	
	public void addAll(List<E> elements) {
		this.items.addAll(elements);
	}
	
	public void removeElement(E element) {
		items.remove(element);
	}
	
	public List<E> getList() {
		return items;
	}
}
