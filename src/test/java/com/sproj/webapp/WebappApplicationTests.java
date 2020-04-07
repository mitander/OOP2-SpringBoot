package com.sproj.webapp;

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
import com.sproj.webapp.controllers.MainController;

@SpringBootTest
class WebappApplicationTests {
	
	// TODO: Comment this shit.

	private static MockMvc mockMvc;

	@Autowired
	private static MainController mainController;

	@BeforeAll
	public static void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/src/main/resources/templates/");
		viewResolver.setSuffix(".html");

		mainController = new MainController();
		mockMvc = MockMvcBuilders.standaloneSetup(mainController).setViewResolvers(viewResolver).build();

	}

	@Test
	public void contexLoads() throws Exception {
		assertThat(mainController).isNotNull();
	}

	@Test
	public void testIndex() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}

	@Test
	public void testContact() throws Exception {
		mockMvc.perform(get("/contact")).andExpect(status().isOk()).andExpect(view().name("contact"));
	}

	@Test
	public void testTags() throws Exception {
		mockMvc.perform(get("/tools")).andExpect(status().isOk()).andExpect(view().name("tools"));
	}

	@Test
	public void testDataDirectory() throws Exception {
		assertThat(DataHandler.DATA_DIRECTORY).isEqualTo("src" + File.separator + "main" + File.separator + "resources"
				+ File.separator + "static" + File.separator + "data" + File.separator);
	}

	@Test
	public void testJsonDirectory() throws Exception {
		assertThat(DataHandler.DATA_DIRECTORY_JSON)
				.isEqualTo("src" + File.separator + "main" + File.separator + "resources" + File.separator + "static"
						+ File.separator + "data" + File.separator + "resourcedata" + File.separator + "resourceData.json");
	}

	@Test
	public void testObjectResourceGetName() throws Exception {
		Resource rs = new Resource("TeStNaMe123");

		assertThat(rs.getName()).isEqualTo("TeStNaMe123");
	}

	@Test
	public void testObjectResourceGetWebsiteWithHttps() throws Exception {
		Resource rs = new Resource("", "www.testsite.com");

		assertThat(rs.getWebsite()).isEqualTo("https://www.testsite.com");
	}

	@Test
	public void testObjectResourceGetComment() throws Exception {
		Resource rs = new Resource("", "", "Test Comment");

		assertThat(rs.getComment()).isEqualTo("Test Comment");

	}
	
	@Test
	public void testObjectResourceGetImageLink() throws Exception {
		Resource rs = new Resource("", "", "", "www.imagelink.com/image.jpg");

		assertThat(rs.getImageLink()).isEqualTo("https://www.imagelink.com/image.jpg");

	}

	@Test
	public void testObjectResourceGetTags() throws Exception {
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("test1");
		tags.add("test2");
		
		Resource rs = new Resource("", "", "", "", tags);

		assertThat(rs.getTags()).isEqualTo(tags);

	}
	@Test
	public void testObjectResourceToString() throws Exception {
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("test1");
		tags.add("test2");
		
		Resource rs = new Resource("TeStNaMe123", "www.testsite.com", "Test Comment", "www.imagelink.com/image.jpg", tags);

		assertThat(rs.toString())
				.isEqualTo("Resource [name=TeStNaMe123, website=https://www.testsite.com, comment=Test Comment, imagelink=https://www.imagelink.com/image.jpg, tags=[test1, test2]]");
	}
	
	@Test
	public void testAddResource() throws Exception {
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("test1");
		tags.add("test2");

		Resource testResource = new Resource("TeStNaMe123", "www.testsite.com", "Test Comment", "www.imagelink.com/image.jpg", tags);
		DataHandler.addResource(testResource);
		
		ArrayList<Resource> resource = DataHandler.fetchData();
		assertThat(resource.contains(testResource));
	}
	
	@Test
	public void testDeleteResource() throws Exception {
		ArrayList<Resource> resource = DataHandler.fetchData();

		ArrayList<String> tags = new ArrayList<String>();
		tags.add("test1");
		tags.add("test2");

		Resource testResource = new Resource("TeStNaMe123", "www.testsite.com", "Test Comment", "www.imagelink.com/image.jpg", tags);
		DataHandler.addResource(testResource);
		int index = testResource.getId();
		DataHandler.deleteResource(index);

		assertThat(!resource.contains(testResource));
	}
	
	@Test
	public void testFetchData() throws Exception {
		ArrayList<Resource> resources = new ArrayList<Resource>();
		ObjectMapper mapper = new ObjectMapper();

		resources = (ArrayList<Resource>) mapper.readValue(Paths.get("src" + File.separator + "test" + 
				File.separator + "resources" + File.separator + "testdata.json").toFile(),
                new TypeReference<List<Resource>>() {
                });

		assertThat(resources.get(0).toString())
		.isEqualTo("Resource [name=TeStNaMe123, website=https://www.testsite.com, comment=Test Comment, imagelink=https://www.imagelink.com/image.jpg, tags=[test1, test2]]");
	}
	
	@Test
	public void testFetchTagData() throws Exception {
		ArrayList<String> testTags1 = new ArrayList<String>();
		testTags1.add("test1");

		ArrayList<String> testTags2 = new ArrayList<String>();
		testTags2.add("test1");

		Resource testResource1 = new Resource("TeStNaMe123", "www.testsite.com", "Test Comment", "www.imagelink.com/image.jpg", testTags1);
		Resource testResource2 = new Resource("TeStNaMe123", "www.testsite.com", "Test Comment", "www.imagelink.com/image.jpg", testTags2);

		DataHandler.addResource(testResource1);
		DataHandler.addResource(testResource2);
		
		ArrayList<Resource> tags = DataHandler.fetchTagData("test1");
		
		assertThat(tags.contains(testResource1));
		assertThat(!tags.contains(testResource2));
	}
	
	@Test
	public void testFetchTags() throws Exception {
		ArrayList<String> testTags = new ArrayList<String>();
		ArrayList<String> tags = DataHandler.fetchTags();

		testTags.add("test1");
		testTags.add("AaAaAaAa");

		Resource testResource = new Resource("TeStNaMe123", "www.testsite.com", "Test Comment", "www.imagelink.com/image.jpg", testTags);
		DataHandler.addResource(testResource);
		
		assertThat(tags.contains("test1"));
		assertThat(tags.contains("AaAaAaAa"));
		assertThat(!tags.contains("ThisTagDoesntExist"));
	}
}