package com.example.demo.service;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.PlantEntity;
import com.example.demo.repository.PlantRepo;
import com.example.demo.request.PlantRequest;
import com.example.demo.response.PlantResponse;

@Service
public class PlantService {

	@Autowired
	PlantRepo pr;
	
	public ResponseEntity<String> savePlant(PlantRequest request) {
    	RestTemplate restTemplate = new RestTemplate();
    	return restTemplate.postForEntity("http://localhost:8090/api/plants/post", request, String.class);
    }
    public <Plant> List<PlantResponse> getAllPlants() {
    	
    	ResponseEntity<PlantEntity[]> response = new RestTemplate().getForEntity(
    			  "http://localhost:8090/api/plants/get",
    			  PlantEntity[].class);
    	List<PlantEntity> productList = Arrays.asList(response.getBody());
        return productList.stream()
                .map(this::mapPlantToResponse)
                .collect(Collectors.toList());
    }
//    public PlantResponse getPlant(int id) {
//        PlantEntity product = pr.findById(id);
//        return mapPlantToResponse(product);
//    }
    
    public List<PlantEntity> getByUserId(Long id) {
    	return pr.findByUserId(id);
    }

    public PlantResponse updatePlant(PlantRequest request, int id) {
    	
    	HashMap<String, Integer> uriVariables = new HashMap<>();
    	uriVariables.put("id", id);
    	HttpEntity<PlantRequest> request1 = new HttpEntity<PlantRequest>(request);
		return new RestTemplate().exchange("http://localhost:8090/api/plants/put", 
    			HttpMethod.PUT, request1, PlantResponse.class, uriVariables).getBody();
    }

    public ResponseEntity<String> deletePlant(int id) {


        HashMap<String, Integer> uriVariables = new HashMap<>();
    	uriVariables.put("id", id);
    	System.out.println("Product ID from the request in serviceImpl:::"+id);

    	//HttpEntity<Product> request1 = new HttpEntity<Product>(product);
		new RestTemplate().delete("http://localhost:8090/api/plants/del", uriVariables);
		return new ResponseEntity<String>("Deleted Successfully", HttpStatusCode.valueOf(200));
    }
    
    
    
//	public List<PlantEntity> getPlants()
//	{
//		return pr.findAll();
//	}
//	
//	public void SaveData(PlantEntity p)
//	{
//		pr.save(p);
//	}
//	public void updateData(PlantEntity obj) {
//		pr.save(obj);
//	}
//
//	public void deleteData(int id) {
//		pr.deleteById(id);
//		
//	}
	
	private PlantResponse mapPlantToResponse(PlantEntity plant) {
        return PlantResponse.builder()
                .id(plant.getId())
                .plantName(plant.getPlantName())
                .plantType(plant.getPlantType())
                .room(plant.getRoom())
                .sunlight(plant.getSunlight())
                .watering(plant.getWatering())
                .build();
    }
}