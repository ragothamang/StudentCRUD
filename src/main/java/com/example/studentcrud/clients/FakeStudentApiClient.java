package com.example.studentcrud.clients;

import com.example.studentcrud.dtos.FakeStoreStudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStudentApiClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreStudentDto addStudent(FakeStoreStudentDto newStudent) {
        ResponseEntity<FakeStoreStudentDto> fakeStoreStudentDtoResponseEntity = requestForEntity(
                "https://freetestapi.com/api/v1/students",
                HttpMethod.POST,
                newStudent,
                FakeStoreStudentDto.class
        );
        return validateResponse(fakeStoreStudentDtoResponseEntity);
    }

    public FakeStoreStudentDto getStudentById(int studentId) {
        ResponseEntity<FakeStoreStudentDto> fakeStoreStudentDtoResponseEntity = requestForEntity(
                "https://freetestapi.com/api/v1/students/{studentId}",
                HttpMethod.GET,
                null,
                FakeStoreStudentDto.class,
                studentId);
        return validateResponse(fakeStoreStudentDtoResponseEntity);
    }

    public <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public FakeStoreStudentDto validateResponse(ResponseEntity<FakeStoreStudentDto> fakeStoreStudentDtoResponseEntity) {
        return fakeStoreStudentDtoResponseEntity.getBody();
    }
}
