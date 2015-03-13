package dutta.biswajyoti.jersey.client;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {
	
	private long id;
	private String name;
	private String author;
	private String description;
	private String price;
	private String isbnCode;
	
	public Book() {}

	public Book(long id, String name, String author, String description,
			String price, String isbnCode) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.description = description;
		this.price = price;
		this.isbnCode = isbnCode;
	}
	
	@XmlAttribute
	public long getId() {
		return id;
	}

	@XmlElement
	public String getName() {
		return name;
	}
	@XmlElement
	public String getAuthor() {
		return author;
	}
	@XmlElement
	public String getDescription() {
		return description;
	}

	@XmlElement
	public String getPrice() {
		return price;
	}

	@XmlElement
	public String getIsbnCode() {
		return isbnCode;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setIsbnCode(String isbnCode) {
		this.isbnCode = isbnCode;
	}

}
