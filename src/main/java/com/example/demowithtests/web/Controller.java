package com.example.demowithtests.web;

import com.example.demowithtests.domain.EmailDetails;
import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.service.EmailService;
import com.example.demowithtests.service.Service;
import com.example.demowithtests.util.WrongTypeOfDataException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    private final Service service;
    private final EmailService emailService;

    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody Employee employee) {
        return service.create(employee);
    }

    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllUsers() {
        return service.getAll();
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable String id) throws WrongTypeOfDataException {

        Integer parsedId = Integer.parseInt(id);
        Employee employee = service.getById(parsedId);
        return employee;
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee refreshEmployee(@PathVariable("id") String id, @RequestBody Employee employee) throws WrongTypeOfDataException {
        Integer parseId = Integer.parseInt(id);
        return service.updateById(parseId, employee);
    }

    //Удаление по id
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable String id) {
        Integer parseId = Integer.parseInt(id);
        service.removeById(parseId);
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        service.removeAll();
    }


    //@PatchMapping("/replaceNull")
    @GetMapping("/replaceNull")
    @ResponseStatus(HttpStatus.OK)
    public void replaceNull() {
        service.processor();
    }

    @PostMapping("/sendEmailByCountry")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCountry(@RequestParam String country, @RequestParam String text) {
        service.sendEmailByCountry(country, text);
    }

    @PostMapping("/sendEmailByCity")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCity(@RequestParam String city, @RequestParam String text) {
        service.sendEmailByCountry(city, text);
    }

    @PostMapping("sendEmailByCitySQL")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailByCitySQL(@RequestParam String city, @RequestParam String text) {
        service.sendEmailByCitySQL(city, text);
    }

    @PostMapping("/sendMail")
    @ResponseStatus(HttpStatus.OK)
    public void sendMail(@RequestBody EmailDetails details,@RequestParam String city) {
        emailService.sendSimpleMail(details, city);

    }

    @PostMapping("/sendMailWithAttachment")
    @ResponseStatus(HttpStatus.OK)
    public void sendMailWithAttachment(@RequestBody EmailDetails details,@RequestParam String city) {
        System.out.println(details);
        emailService.sendMailWithAttachment(details, city);
    }
}
