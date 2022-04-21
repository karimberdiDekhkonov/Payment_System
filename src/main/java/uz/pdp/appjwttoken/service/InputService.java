package uz.pdp.appjwttoken.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appjwttoken.entity.Card;
import uz.pdp.appjwttoken.entity.Input;
import uz.pdp.appjwttoken.payload.InputDto;
import uz.pdp.appjwttoken.repository.CardRepository;
import uz.pdp.appjwttoken.repository.InputRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    InputRepository inputRepository;


    public List<Input> getInputs(){
        return inputRepository.findAll();
    }

    public Input getInput(Integer id){
        Optional<Input> optional = inputRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        else return new Input();
    }

    public String addInput(InputDto dto) {
        Optional<Card> fromCardOptional = cardRepository.findById(dto.getFromCardId());
        if (!fromCardOptional.isPresent()) {
            return "Card id is not found !";
        }
        Optional<Card> toCardOptional = cardRepository.findById(dto.getToCardId());
        if (!toCardOptional.isPresent()) {
            return "Card id is not found !";
        }
        Card fromCard = fromCardOptional.get();
        long amount = dto.getAmount();
        long balance = fromCard.getBalance();
        if (amount <= 0) {
            return "The amount can not be less or equal to 0";
        }
        if (balance < amount) {
            return "Your Balance is not enough for this transfer !";
        }
        Card toCard = toCardOptional.get();
        if (!fromCard.isActive() || !toCard.isActive()) {
            return "The Car has expired";
        }
        balance -= amount;
        fromCard.setBalance(balance);
        toCard.setBalance(toCard.getBalance() + amount);
        Input input = new Input();
        input.setAmount(amount);
        input.setDate(new Date());
        input.setFromCardId(fromCard);
        input.setToCardId(toCard);
        inputRepository.save(input);
        return "Successfully Transferred !";
    }

    public String deleteInput(Integer id){
        Optional<Input> optional = inputRepository.findById(id);
        if (optional.isPresent()){
            inputRepository.delete(optional.get());
            return "Successfully deleted !";
        }
        return "Id is not found !";
    }
}
