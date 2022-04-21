package uz.pdp.appjwttoken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjwttoken.entity.Input;
import uz.pdp.appjwttoken.payload.InputDto;
import uz.pdp.appjwttoken.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/api/input")
public class InputController {
    @Autowired
    InputService inputService;

    @GetMapping
    public HttpEntity<?>getInputs(){
        List<Input> inputs = inputService.getInputs();
        return ResponseEntity.ok(inputs);
    }

    @GetMapping("/{id}")
    public HttpEntity<?>getInput(@PathVariable Integer id){
        Input input = inputService.getInput(id);
        return ResponseEntity.ok(input);
    }

    @PostMapping
    public HttpEntity<?>addInput(@RequestBody InputDto dto){
        String addInput = inputService.addInput(dto);
        return ResponseEntity.ok(addInput);
    }

    @DeleteMapping
    public HttpEntity<?> deleteInput(@PathVariable Integer id){
        String input = inputService.deleteInput(id);
        return ResponseEntity.ok(input);
    }
}
