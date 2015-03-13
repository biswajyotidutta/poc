package dutta.biswajyoti.jersey.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class JerseyTestClient {

	public static void main(String[] args) {
		
		ClientConfig clientConfiguration = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfiguration);
		URI booksURI = getBaseURI();
		
		testPlainText(client.target(booksURI));
		testPlainTextWithParameter(client.target(booksURI).path("name").path("2"));		
		testPost(client.target(booksURI));
		
		Book responseBook = testGetBookById(client.target(booksURI).path("details").path("5"));	
		responseBook.setPrice("9.50");
		
		testPut(client.target(booksURI).path("modify"), responseBook);
		System.out.println("Number of books returned: " +testGetAllAsList(client.target(booksURI).path("details").path("all")).size());
		testDeleteById(client.target(booksURI).path("delete").path("5"));		
				
		List<Book> booksAfterDelete = testGetAllAsList(client.target(booksURI).path("details").path("all"));
		System.out.println("Number of books returned after deletion: " +booksAfterDelete.size());
		
	}

	private static List<Book> testGetAllAsList(WebTarget target) {
		System.out.println(target.request().accept(MediaType.APPLICATION_XML).get(Response.class).toString());
		System.out.println(target.request().accept(MediaType.APPLICATION_XML).get(String.class));
		return target.request().accept(MediaType.APPLICATION_XML).get(new GenericType<List<Book>>(){});
	}

	private static void testDeleteById(WebTarget target) {
		Response deleteResponse = target.request().accept(MediaType.APPLICATION_XML).delete();
		System.out.println("Response Status delete: "+deleteResponse.getStatus());
	}

	private static void testPut(WebTarget target, Book book) {
		Response putResponse = target.request().put(Entity.entity(book, MediaType.APPLICATION_XML));
		System.out.println("Response Status put: "+putResponse.getStatus());
	}

	private static Book testGetBookById(WebTarget target) {
		System.out.println(target.request().accept(MediaType.APPLICATION_XML).get(Response.class).toString());
		System.out.println(target.request().accept(MediaType.APPLICATION_XML).get(String.class));
		
		return target.request().accept(MediaType.APPLICATION_XML).get(Book.class);
	}

	private static void testPost(WebTarget target) {
		Book book = new Book(5, "Book 3", "Author 3", "Book description 3", "7.00 EUR", "ISBN 345-9876120");
		target = target.path("create");
		
		Response postResponse = target.request().post(Entity.entity(book, MediaType.APPLICATION_XML));
		System.out.println("Response Status post: "+postResponse.getStatus());
	}

	private static void testPlainTextWithParameter(WebTarget target) {
		System.out.println(target.request().accept(MediaType.TEXT_PLAIN).get(Response.class).toString());
		System.out.println(target.request().accept(MediaType.TEXT_PLAIN).get(String.class));
	}

	private static void testPlainText(WebTarget target) {
		System.out.println(target.request().accept(MediaType.TEXT_PLAIN).get(Response.class).toString());
		System.out.println(target.request().accept(MediaType.TEXT_PLAIN).get(String.class));
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8081/MyJerseyWebProject/rest/books").build();
	}
}
