package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@NoArgsConstructor
@AllArgsConstructor 
public class PlantResponse 
{
	private Integer id;
	private String plantName;
	private String plantType;
	private String room;
	private String watering;
	private String sunlight;
}
