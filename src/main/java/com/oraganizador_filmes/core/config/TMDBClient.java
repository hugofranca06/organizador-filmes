package com.oraganizador_filmes.core.config;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.oraganizador_filmes.api.dto.input.TMDBFilmeResponse;

@Component
public class TMDBClient {

	@Value("${tmdb.api.key}")
	private String apiKey;	
	
	private final String BASE_URL = "https://api.themoviedb.org/3";
	
	private final RestTemplate restTemplate;
	
    public TMDBClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	
	public Optional<TMDBFilmeResponse> buscarFilmePorId(Long tmdbId) {
		try {
			String url = String.format("%s/movie/%d?api_key=%s", BASE_URL, tmdbId, apiKey);
			System.out.println(url);
			TMDBFilmeResponse response = restTemplate.getForObject(url, TMDBFilmeResponse.class);
            
            String creditsUrl = String.format("%s/movie/%d/credits?api_key=%s", BASE_URL, tmdbId, apiKey);
            System.out.println(creditsUrl);
            Map<String, Object> creditsResponse = restTemplate.getForObject(creditsUrl, Map.class);
            
            if(response != null) {
            	response.setDiretor(extrairDiretor(creditsResponse));
            }
            System.out.println(response);
            return Optional.ofNullable(response);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	
	private String extrairDiretor(Map<String, Object> creditsData) {
        List<Map<String, Object>> crew = (List<Map<String, Object>>) creditsData.get("crew");
        return crew.stream()
            .filter(member -> "Director".equals(member.get("job")))
            .findFirst()
            .map(director -> (String) director.get("name"))
            .orElse(null);
    }
    
}
