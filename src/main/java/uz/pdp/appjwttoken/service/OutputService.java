package uz.pdp.appjwttoken.service;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.OutputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjwttoken.entity.Card;
import uz.pdp.appjwttoken.entity.Input;
import uz.pdp.appjwttoken.entity.Output;
import uz.pdp.appjwttoken.payload.InputDto;
import uz.pdp.appjwttoken.payload.OutputDto;
import uz.pdp.appjwttoken.repository.CardRepository;
import uz.pdp.appjwttoken.repository.OutputRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OutputRepository outputRepository;

    public List<Output> getOutputs() {
        return outputRepository.findAll();
    }

    public Output getOutput(Integer id) {
        Optional<Output> optional = outputRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return new Output();
    }

    public String addOutput(OutputDto dto) {
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
        if (balance < (amount+ dto.getCommissionAmount())) {
            return "Your Balance is not enough for this transfer !";
        }
        Card toCard = toCardOptional.get();
        if (!fromCard.isActive() || !toCard.isActive()) {
            return "The Car has expired";
        }
        balance -= (amount+ dto.getCommissionAmount());
        fromCard.setBalance(balance);
        toCard.setBalance(toCard.getBalance() + amount);
        Output output = new Output();
        output.setAmount(amount);
        output.setDate(new Date());
        output.setFromCardId(fromCard);
        output.setToCardId(toCard);
        output.setCommissionAmount(dto.getCommissionAmount());
        outputRepository.save(output);
        return "Successfully Transferred !";
    }

    public String deleteOutput(Integer id){
        Optional<Output> optional = outputRepository.findById(id);
        if (optional.isPresent()){
            outputRepository.delete(optional.get());
            return "Successfully Deleted !";
        }
        return "Id is not found";
    }
}


