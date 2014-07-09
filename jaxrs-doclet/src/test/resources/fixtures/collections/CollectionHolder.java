package fixtures.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The CollectionHolder represents a holder of different collection fields
 * @version $Id$
 * @author conor.roche
 */
@XmlRootElement(name = "collectionHolder")
public class CollectionHolder implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> listStrings;
	private Collection<String> collectionStrings;
	private Set<String> setStrings;
	private List<CollectionItem> listItems;
	private Collection<CollectionItem> collectionItems;
	private Set<CollectionItem> setItems;

	// NOTE if you add in getter comments you need to modify the collections.json to have in the descriptions
	@SuppressWarnings("javadoc")
	public List<String> getListStrings() {
		return this.listStrings;
	}

	/**
	 * This sets the listStrings
	 * @param listStrings the listStrings to set
	 */
	public void setListStrings(List<String> listStrings) {
		this.listStrings = listStrings;
	}

	@SuppressWarnings("javadoc")
	public Collection<String> getCollectionStrings() {
		return this.collectionStrings;
	}

	/**
	 * This sets the collectionStrings
	 * @param collectionStrings the collectionStrings to set
	 */
	public void setCollectionStrings(Collection<String> collectionStrings) {
		this.collectionStrings = collectionStrings;
	}

	@SuppressWarnings("javadoc")
	public Set<String> getSetStrings() {
		return this.setStrings;
	}

	/**
	 * This sets the setStrings
	 * @param setStrings the setStrings to set
	 */
	public void setSetStrings(Set<String> setStrings) {
		this.setStrings = setStrings;
	}

	@SuppressWarnings("javadoc")
	public List<CollectionItem> getListItems() {
		return this.listItems;
	}

	/**
	 * This sets the listItems
	 * @param listItems the listItems to set
	 */
	public void setListItems(List<CollectionItem> listItems) {
		this.listItems = listItems;
	}

	@SuppressWarnings("javadoc")
	public Collection<CollectionItem> getCollectionItems() {
		return this.collectionItems;
	}

	/**
	 * This sets the collectionItems
	 * @param collectionItems the collectionItems to set
	 */
	public void setCollectionItems(Collection<CollectionItem> collectionItems) {
		this.collectionItems = collectionItems;
	}

	@SuppressWarnings("javadoc")
	public Set<CollectionItem> getSetItems() {
		return this.setItems;
	}

	/**
	 * This sets the setItems
	 * @param setItems the setItems to set
	 */
	public void setSetItems(Set<CollectionItem> setItems) {
		this.setItems = setItems;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CollectionHolder [collectionItems=" + this.collectionItems + ", collectionStrings=" + this.collectionStrings + ", listItems=" + this.listItems
				+ ", listStrings=" + this.listStrings + ", setItems=" + this.setItems + ", setStrings=" + this.setStrings + "]";
	}

}
