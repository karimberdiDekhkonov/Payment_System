package uz.pdp.appjwttoken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjwttoken.entity.Output;
import uz.pdp.appjwttoken.payload.OutputDto;
import uz.pdp.appjwttoken.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/api/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @GetMapping
    public HttpEntity<?>getOutputs(){
        List<Output> outputs = outputService.getOutputs();
        return ResponseEntity.ok(outputs);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOutput(@PathVariable Integer id){
        Output output = outputService.getOutput(id);
        return ResponseEntity.ok(output);
    }

    @PostMapping
    public HttpEntity<?>addOutput(@RequestBody OutputDto dto){
        String output = outputService.addOutput(dto);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping
    public HttpEntity<?> deleteOutput(@PathVariable Integer id){
        String output = outputService.deleteOutput(id);
        return ResponseEntity.ok(output);
    }
}
