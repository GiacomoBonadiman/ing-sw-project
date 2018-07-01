package miscellaneous;

import javax.swing.AbstractListModel;

public class RefreshableListModel<E> extends AbstractListModel<E> {

	private E[] items;
	
	public RefreshableListModel(E[] items) {
		this.items = items;
	}
	
	@Override
	public E getElementAt(int i) {
		return items[i];
	}

	@Override
	public int getSize() {
		return items.length;
	}

	public void refreshList(E[] items) {
		this.items = items;
	}
}
