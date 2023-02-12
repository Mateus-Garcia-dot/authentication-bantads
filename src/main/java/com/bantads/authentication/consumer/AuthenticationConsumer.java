package com.bantads.authentication.consumer;

import com.bantads.authentication.model.AuthenticationModel;
import com.bantads.authentication.repository.AuthenticationRepository;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@Data
public class AuthenticationConsumer {

    @Autowired private AuthenticationRepository authenticationRepository;

    @RabbitListener(queues = "auth.create")
    public void createAuthentication(AuthenticationModel authModel) {
        AuthenticationModel addedAuthModel = this.authenticationRepository.save(authModel);
    }

    @RabbitListener(queues = "auth.update")
    public void updateAuthentication(AuthenticationModel authModel) {
        AuthenticationModel auth = this.authenticationRepository.findById(authModel.getUuid()).orElse(null);
        if(auth == null) {
            return;
        }
        auth.setAccount(authModel.getAccount());
        auth.setLogin(authModel.getLogin());
        auth.setPassword(authModel.getPassword());
        auth.setType(authModel.getType());
        auth.setIsApproved(authModel.getIsApproved());
        auth.setIsPending(authModel.getIsPending());
    }

    @RabbitListener(queues = "auth.delete")
    public void deleteAuthentication(AuthenticationModel authenticationModel) {
        this.authenticationRepository.deleteById(authenticationModel.getUuid());
    }

}
