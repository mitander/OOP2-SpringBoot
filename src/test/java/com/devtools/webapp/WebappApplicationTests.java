package com.devtools.webapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.devtools.webapp.controllers.MainController;

/**
 * JUnit test class
 * 
 * @author carl
 * 
 */

@SpringBootTest
class WebappApplicationTests {
	
	/**
	 * TEST_DATA :src/test/resources/testdata.json
	 */
	public final static String TEST_DATA = "src" + File.separator + "test" + File.separator + "resources"
			+ File.separator + "testdata.json";

	private static MockMvc mockMvc;

	@Autowired
	private static MainController mainController;

	/**
	 * Method that sets prefix and suffix for tests
	 */
	@BeforeAll
	public static void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/src/main/resources/templates/");
		viewResolver.setSuffix(".html");

		mainController = new MainController();
		mockMvc = MockMvcBuilders.standaloneSetup(mainController).setViewResolvers(viewResolver).build();

	}

	/**
	 * Test that checks that the controller is not null.
	 * @throws Exception
	 */
	@Test
	public void contexLoads() throws Exception {
		assertThat(mainController).isNotNull();
	}

	/**
	 * Test that checks that the index page is loaded properly
	 * @throws Exception
	 */
	@Test
	public void testIndex() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}

	/**
	 * Test that checks if the contact page is loaded properly
	 * @throws Exception
	 */
	@Test
	public void testContact() throws Exception {
		mockMvc.perform(get("/contact")).andExpect(status().isOk()).andExpect(view().name("contact"));
	}

	/**
	 * Test that checks if the tags page is loaded properly
	 * @throws Exception
	 */
	@Test
	public void testTags() throws Exception {
		mockMvc.perform(get("/tools")).andExpect(status().isOk()).andExpect(view().name("tools"));
	}

	/**
	 * Test that checks if the DATA_DIRECTORY_JSON path variable is correct
	 * @throws Exception
	 */
	@Test
	public void testJsonData() throws Exception {
		assertThat(DataHandler.JSON_DATA).isEqualTo("src" + File.separator + "main" + File.separator
				+ "resources" + File.separator + "static" + File.separator + "data" + File.separator + "resourcedata"
				+ File.separator + "resourcedata.json");
	}

	/**
	 * Test for getName() method
	 * @throws Exception
	 */
	@Test
	public void testObjectResourceGetName() throws Exception {
		Resource rs = new Resource("TeStNaMe123");

		assertThat(rs.getName()).isEqualTo("TeStNaMe123");
	}

	/**
	 * Test for getWebsite() method
	 * @throws Exception
	 */
	@Test
	public void testObjectResourceGetWebsiteWithHttps() throws Exception {
		Resource rs = new Resource("", "www.testsite.com");

		assertThat(rs.getWebsite()).isEqualTo("https://www.testsite.com");
	}

	/**
	 * Test for getDescription() method
	 * @throws Exception
	 */
	@Test
	public void testObjectResourceGetDescription() throws Exception {
		Resource rs = new Resource("", "", "Test Description");

		assertThat(rs.getDescription()).isEqualTo("Test Description");

	}

	/**
	 * Test for getImageLink() method
	 * @throws Exception
	 */
	@Test
	public void testObjectResourceGetImageLink() throws Exception {
		Resource rs = new Resource("", "", "", "www.imagelink.com/image.jpg");

		assertThat(rs.getImageLink()).isEqualTo("https://www.imagelink.com/image.jpg");

	}

	/**
	 * Test for getTags() method
	 * @throws Exception
	 */
	@Test
	public void testObjectResourceGetTags() throws Exception {
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("test1");
		tags.add("test2");

		Resource rs = new Resource("", "", "", "", tags);

		assertThat(rs.getTags()).isEqualTo(tags);

	}

	/**
	 * Test for overridden toString() method in Resource class
	 * @throws Exception
	 */
	@Test
	public void testObjectResourceToString() throws Exception {
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("test1");
		tags.add("test2");

		Resource rs = new Resource("TeStNaMe123", "www.testsite.com", "Test Description", "www.imagelink.com/image.jpg",
				tags);

		assertThat(rs.toString()).isEqualTo(
				"Resource [name=TeStNaMe123, website=https://www.testsite.com, description=Test Description, imagelink=https://www.imagelink.com/image.jpg, tags=[test1, test2]]");
	}

	/**
	 * Test for addResource() method
	 * @throws Exception
	 */
	@Test
	public void testAddResource() throws Exception {
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("test1");
		tags.add("test2");

		Resource testResource = new Resource("TeStNaMe123", "www.testsite.com", "Test Description",
				"www.imagelink.com/image.jpg", tags);
		DataHandler.addResource(testResource, TEST_DATA);

		ArrayList<Resource> resource = DataHandler.fetchJsonData(TEST_DATA);
		assertThat(resource.contains(testResource));
	}

	/**
	 * Test for deleteResource() method
	 * @throws Exception
	 */
	@Test
	public void testDeleteResource() throws Exception {
		ArrayList<Resource> resource = DataHandler.fetchJsonData(TEST_DATA);

		ArrayList<String> tags = new ArrayList<String>();
		tags.add("test1");
		tags.add("test2");

		Resource testResource = new Resource("TeStNaMe123", "www.testsite.com", "Test Description",
				"www.imagelink.com/image.jpg", tags);
		DataHandler.addResource(testResource, TEST_DATA);
		int index = testResource.getId();
		DataHandler.deleteResource(index, TEST_DATA);

		assertThat(!resource.contains(testResource));
	}

	/**
	 * Test for fetchData() method
	 * @throws Exception
	 */
	@Test
	public void testFetchData() throws Exception {
		ArrayList<Resource> resources = new ArrayList<Resource>();
		ObjectMapper mapper = new ObjectMapper();

		resources = (ArrayList<Resource>) mapper.readValue(Paths
				.get(TEST_DATA)
				.toFile(), new TypeReference<List<Resource>>() {
				});

		assertThat(resources.get(0).toString()).isEqualTo(
				"Resource [name=TeStNaMe123, website=https://www.testsite.com, description=Test Description, imagelink=https://www.imagelink.com/image.jpg, tags=[test1, test2]]");
	}

	/**
	 * Test for fetchTagData() method
	 * @throws Exception
	 */
	@Test
	public void testFetchTagData() throws Exception {
		ArrayList<String> testTags1 = new ArrayList<String>();
		testTags1.add("test1");

		ArrayList<String> testTags2 = new ArrayList<String>();
		testTags2.add("test1");

		Resource testResource1 = new Resource("TeStNaMe123", "www.testsite.com", "Test Description",
				"www.imagelink.com/image.jpg", testTags1);
		Resource testResource2 = new Resource("TeStNaMe123", "www.testsite.com", "Test Description",
				"www.imagelink.com/image.jpg", testTags2);

		DataHandler.addResource(testResource1, TEST_DATA);
		DataHandler.addResource(testResource2, TEST_DATA);

		ArrayList<Resource> tags = DataHandler.fetchTagData("test1", TEST_DATA);

		assertThat(tags.contains(testResource1));
		assertThat(!tags.contains(testResource2));
	}

	/**
	 * Test for fetchTags() method
	 * @throws Exception
	 */
	@Test
	public void testFetchTags() throws Exception {
		ArrayList<String> testTags = new ArrayList<String>();
		ArrayList<String> tags = DataHandler.fetchTags(TEST_DATA);

		testTags.add("test1");
		testTags.add("AaAaAaAa");

		Resource testResource = new Resource("TeStNaMe123", "www.testsite.com", "Test Description",
				"www.imagelink.com/image.jpg", testTags);
		DataHandler.addResource(testResource, TEST_DATA);

		assertThat(tags.contains("test1"));
		assertThat(tags.contains("AaAaAaAa"));
		assertThat(!tags.contains("ThisTagDoesntExist"));
	}
}