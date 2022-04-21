package uz.pdp.appjwttoken.controller;

import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjwttoken.entity.Input;
import uz.pdp.appjwttoken.entity.Output;
import uz.pdp.appjwttoken.repository.InputRepository;
import uz.pdp.appjwttoken.repository.OutputRepository;
import uz.pdp.appjwttoken.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    InputService inputService;

    @GetMapping("/input")
    public HttpEntity<?>getInputs(){
        List<Input> inputs = inputService.getInputs();
        return ResponseEntity.ok(inputs);
    }


//    @GetMapping("/output/{cardId}")
//    public HttpEntity<?> getOutputs(@PathVariable Integer cardId){
//        List<Output> outputList = outputRepository.findAllByFromCardId(cardId);
//        if (!outputList.isEmpty()){
//            return ResponseEntity.ok(outputList);
//        }
//        return ResponseEntity.ok("Output doesn't found !");
//    }
}
